package com.roje.warp.data;

import com.github.nicklib.data.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

import static com.roje.warp.WarpMain.plugin;

public class WarpData implements WarpImpl {


    private Config config;

    private Player player;
    private String name;


    public WarpData(Player player, String name) {
        this.player = player;
        this.name = name;
        config = new Config("warp/" + name, plugin);
    }


    public WarpData(String name) {
        this.name = name;
        config = new Config("warp/" + name, plugin);
    }


    public WarpData() {
        config = new Config("warp/", plugin);
    }


    /**
     * 워프를 생성합니다.
     */
    @Override
    public void create() {
        config.saveLocation(name, player.getLocation());
    }

    /**
     * 워프를 삭제합니다.
     */
    @Override
    public void delete() {
        config.deleteFile();
    }

    /**
     * 워프로 이동합니다.
     */
    @Override
    public void teleport() {
        player.teleport(config.getLocation(name));
    }

    /**
     * 워프 목록을 반환합니다.
     */
    @Override
    public List<String> list() {
        return config.fileListName();
    }

    /**
     * 워프의 위치를 반환합니다.
     */
    @Override
    public String getLocation(String name) {
        return getX(name) + ", " + getY(name) + ", " + getZ(name);
    }

    /**
     * 워프의 X좌표를 반환합니다.
     */
    public Integer getX(String name) {
        return config.getInt(name + ".x");
    }


    /**
     * 워프의 Y좌표를 반환합니다.
     */
    public double getY(String name) {
        return config.getInt(name + ".y");
    }


    /**
     * 워프의 Z좌표를 반환합니다.
     */
    public double getZ(String name) {
        return config.getInt(name + ".z");
    }


    /**
     * 워프가 존재하는지 확인합니다.
     */
    public Boolean isFileExist(String name) {
        return config.isFileExist();
    }


}
