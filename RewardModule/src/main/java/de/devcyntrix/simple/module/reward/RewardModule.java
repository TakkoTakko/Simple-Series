package de.devcyntrix.simple.module.reward;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import de.devcyntrix.bukkit.BukkitSerialization;
import de.devcyntrix.simple.module.reward.command.SpawnRewardEntityCommand;
import de.devcyntrix.simple.module.reward.listener.RewardEntityInteractListener;
import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.ipvp.canvas.MenuFunctionListener;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RewardModule extends SimpleModule {

    public static ObjectMapper MAPPER = new YAMLMapper();

    static {
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.registerModule(BukkitSerialization.bukkit());
    }

    @Getter
    private RewardConfiguration configuration = new RewardConfiguration();

    @Getter
    private LoadingCache<UUID, UserDataFile> playerRewardsCache;

    public RewardModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    @Override
    public void init() {
        if (!getDataFolder().isDirectory()) {
            getDataFolder().mkdirs();
        }

        reloadConfig();

        File userData = new File(getDataFolder(), "users");
        if (!userData.isDirectory())
            userData.mkdirs();

        this.playerRewardsCache = CacheBuilder.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .maximumSize(25)
                .build(new CacheLoader<>() {
                    @Override
                    public UserDataFile load(UUID key) {
                        File file = new File(userData, key.toString() + ".yml");
                        if (!file.exists()) {
                            return new UserDataFile(file, new PlayerRewards());
                        }
                        UserDataFile userDataFile = new UserDataFile(file);
                        userDataFile.reload();
                        return userDataFile;
                    }
                });

        RewardEntity entity = this.configuration.getEntity();
        if (entity != null)
            entity.respawn();

        PluginCommand command = registerCommand("spawnRewardEntity");
        command.setExecutor(new SpawnRewardEntityCommand(this));
        command.setPermission("reward.spawn");
        command.setDescription("Spawns a reward entity");
        command.setUsage("§c/<command> <entity type> <name>");

        registerListener(new RewardEntityInteractListener(this));
        registerListener(new MenuFunctionListener());
    }

    @Override
    public void terminate() {
        RewardEntity entity = this.configuration.getEntity();
        if (entity != null)
            entity.remove();
    }

    public void reloadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            if (!configFile.isFile()) {
                MAPPER.writeValue(configFile, configuration);
            } else {
                this.configuration = MAPPER.readValue(configFile, RewardConfiguration.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            MAPPER.writeValue(configFile, this.configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
