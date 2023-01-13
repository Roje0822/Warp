package com.roje.warp;

import com.roje.warp.command.WarpCmd;
import com.roje.warp.command.WarpTabComplete;
import org.bukkit.plugin.java.JavaPlugin;

public class WarpMain extends JavaPlugin {

    public static WarpMain plugin;

    @Override
    public void onEnable() {
        init();
    }


    /**
     * 플러그인 정보를 가져옵니다.
     */
    public void init() {


        /** COMMAND */
        getCommand("warp").setExecutor(new WarpCmd());
        getCommand("warp").setTabCompleter(new WarpTabComplete());

        /** CONFIG */
        plugin = this;
        saveConfig();
    }
}
