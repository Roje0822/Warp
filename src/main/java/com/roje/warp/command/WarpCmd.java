package com.roje.warp.command;

import com.github.nicklib.data.Config;
import com.roje.warp.data.StringData;
import com.roje.warp.data.WarpData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.roje.warp.WarpMain.*;

public class WarpCmd implements CommandExecutor {


    @Override
    @SuppressWarnings("all")
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        if (!(sender instanceof Player)) return false;

        if (args.length == 0) {
            player.sendMessage();
            return true;
        }

        StringData stringData = new StringData();
        WarpData data;
        String name;

        switch (args[0]) {

            case "생성" -> {
                name = args[1];

                if(!player.hasPermission("warp.create")) {
                    player.sendMessage("권한이 없습니다!");
                    return true;
                }

                if (new WarpData(name).isFileExist(name).equals(true)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.alreadyExistWarp()
                            .replace("{name}", name)));
                    return true;
                }

                new WarpData(player, name).create();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.createWarp()
                        .replace("{name}", name)));
                return true;
            }

            case "삭제" -> {
                name = args[1];

                if(!player.hasPermission("warp.remove")) {
                    player.sendMessage("권한이 없습니다!");
                    return true;
                }

                if (new WarpData(name).isFileExist(name).equals(false)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.nonexistentWarp()
                            .replace("{name}", name)));
                    return true;
                }

                new WarpData(player, name).delete();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.deleteWarp()
                        .replace("{name}", name)));
                return true;
            }

            case "이동" -> {
                name = args[1];

                if(!player.hasPermission("warp.teleport")) {
                    player.sendMessage("권한이 없습니다!");
                    return true;
                }

                if (new WarpData(name).isFileExist(name).equals(false)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.nonexistentWarp()
                            .replace("{name}", name)));
                    return true;
                }

                new WarpData(player, name).teleport();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.teleportWarp()
                        .replace("{name}", name)));
                return true;
            }

            case "목록" -> {
                List<String> list = new WarpData().list();

                if(!player.hasPermission("warp.list")) {
                    player.sendMessage("권한이 없습니다!");
                    return true;
                }

                list.forEach(warpList -> {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', stringData.listWarp()
                            .replace("{name}", warpList)
                            .replace("{location}", new WarpData(warpList).getLocation(warpList))));
                });
                return true;
            }

            default -> {
                Config config = new Config("config", plugin);
                for (String usage : config.getStringList("messages.warp.usage")) {
                    player.sendMessage(usage);
                }
                return true;
            }

        }
    }
}

