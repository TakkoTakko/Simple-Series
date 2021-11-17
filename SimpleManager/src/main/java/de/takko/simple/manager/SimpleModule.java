package de.takko.simple.manager;

import de.takko.simple.manager.util.ManagerConfig;
import lombok.Getter;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Getter
public abstract class SimpleModule {

    private final SimpleManager holder;
    private final Server server;
    private final ModuleInfo moduleInfo;
    private final ManagerConfig managerConfig;

    @Getter
    private final Set<Listener> listenerSet = new HashSet<>();

    public SimpleModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        this.holder = holder;
        this.server = server;
        this.moduleInfo = moduleInfo;
        this.managerConfig = new ManagerConfig();
    }

    public abstract void init();

    public abstract void terminate();

    public void registerListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, getHolder());
        this.listenerSet.add(listener);
    }

    public <T> void registerService(Class<T> serviceClass, T instance) {
        getServer().getServicesManager().register(serviceClass, instance, getHolder(), ServicePriority.Normal);
    }

    public <T> @Nullable T getService(Class<T> serviceClass) {
        return getServer().getServicesManager().load(serviceClass);
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

    public File getDataFolder() {
        return new File(getHolder().getModulesFolder(), getModuleInfo().getName());
    }

    public String getModuleDir(SimpleModule module) {
        return getHolder().getDataFolder() + "/modules/" + module.getModuleInfo().getName() + "/";
    }
}
