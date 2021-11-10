package de.takko.simple.module.test;

import de.takko.simple.manager.ModuleInfo;
import de.takko.simple.manager.SimpleManager;
import de.takko.simple.manager.SimpleModule;
import de.takko.simple.manager.util.FileManager;
import org.bukkit.Server;

public class TestModule extends SimpleModule {

    public TestModule(SimpleManager holder, Server server, ModuleInfo moduleInfo) {
        super(holder, server, moduleInfo);
    }

    private FileManager fileManager;

    @Override
    public void init() {
        fileManager = new FileManager(this, "Prefix", null);

        fileManager.addDefault("Test", "COOOL");

        fileManager.saveDefaults();
    }

    @Override
    public void terminate() {

    }
}
