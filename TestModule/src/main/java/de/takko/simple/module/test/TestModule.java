package de.takko.simple.module.test;

import de.takko.simple.manager.base.ModuleInfo;
import de.takko.simple.manager.base.SimpleManager;
import de.takko.simple.manager.base.SimpleModule;
import de.takko.simple.manager.base.util.file.FileManager;
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
