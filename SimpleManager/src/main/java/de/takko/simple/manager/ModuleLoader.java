package de.takko.simple.manager;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.takko.simple.manager.utils.Logger;
import lombok.Getter;
import org.bukkit.Server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleLoader extends URLClassLoader {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static {
        registerAsParallelCapable();
    }

    @Getter
    private final SimpleManager manager;
    @Getter
    private ModuleInfo moduleInfo;

    public ModuleLoader(URL[] urls, ClassLoader parent, SimpleManager manager) {
        super(urls, parent);
        this.manager = manager;
        InputStream resourceAsStream = getResourceAsStream("module.json");
        //Preconditions.checkNotNull(resourceAsStream, "Missing module.json");
        if (resourceAsStream == null) {
            new Logger().log(Logger.LogType.ERROR, "missing module.json");
            return;
        }
        this.moduleInfo = GSON.fromJson(new InputStreamReader(resourceAsStream), ModuleInfo.class);
    }

    public SimpleModule newModule() throws Exception {
        String main = moduleInfo.getMainClass();
        Class<?> mainClass = loadClass(main);
        Preconditions.checkArgument(SimpleModule.class.isAssignableFrom(mainClass));

        Constructor<?> constructor = mainClass.getConstructor(SimpleManager.class, Server.class, ModuleInfo.class);
        return (SimpleModule) constructor.newInstance(manager, manager.getServer(), moduleInfo);
    }
}
