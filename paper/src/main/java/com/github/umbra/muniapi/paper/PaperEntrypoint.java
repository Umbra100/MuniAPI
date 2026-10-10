package com.github.umbra.muniapi.paper;

import org.bukkit.plugin.java.JavaPlugin;

public class PaperEntrypoint extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello Paper!");
    }
}
