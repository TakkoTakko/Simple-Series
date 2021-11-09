package de.takko.simple.manager;

import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Getter
public abstract class SimpleModule {

    private final JavaPlugin holder;
    private final Server server;
    private final ModuleInfo moduleInfo;

    @Getter
    private final Set<Listener> listenerSet = new HashSet<>();

    public SimpleModule(JavaPlugin holder, Server server, ModuleInfo moduleInfo) {
        this.holder = holder;
        this.server = server;
        this.moduleInfo = moduleInfo;
    }

    public abstract void init();

    public abstract void terminate();

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, getHolder());
        this.listenerSet.add(listener);
    }

    public PluginCommand registerCommand(String name, String... aliases) {
        try {
            name = name.trim().toLowerCase(Locale.ROOT).replace(' ', '_');

            Method getCommandMap = server.getClass().getMethod("getCommandMap");
            CommandMap commandMap = (CommandMap) getCommandMap.invoke(server);

            Constructor<PluginCommand> declaredConstructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            declaredConstructor.setAccessible(true);
            PluginCommand command = declaredConstructor.newInstance(name, holder);
            command.setAliases(Arrays.asList(aliases));

            boolean register = commandMap.register(holder.getDescription().getName(), command);
            return register ? command : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getModuleDir(SimpleModule module) {
        return getHolder().getDataFolder() + "/modules/" + module.getModuleInfo().getName() + "/";
    }
}
