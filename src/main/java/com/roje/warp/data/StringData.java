package com.roje.warp.data;

import com.github.nicklib.data.Config;

import java.util.List;

import static com.roje.warp.WarpMain.plugin;

public class StringData {

    private Config config = new Config("config", plugin);
    private String prefix = config.getString("prefix");


    public List<String> usage() {
        return List.of((prefix + config.getString("messages.warp.usage")));
    }


    public String createWarp() {
        return prefix + config.getString("messages.warp.create");
    }


    public String deleteWarp() {
        return prefix + config.getString("messages.warp.delete");
    }


    public String teleportWarp() {
        return prefix + config.getString("messages.warp.teleport");
    }


    public String listWarp() {
        return prefix + config.getString("messages.warp.list");
    }


    public String nonexistentWarp() {
        return prefix + config.getString("messages.error.nonexistent");
    }


    public String alreadyExistWarp() {
        return prefix + config.getString("messages.error.alreadyexist");
    }


    public String noPermission() {
        return prefix + config.getString("messages.error.nopermission");
    }


    public String createPermission() {
        return config.getString("messages.permission.create");
    }

}
