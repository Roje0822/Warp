package com.roje.warp.command;

import com.roje.warp.data.WarpData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarpTabComplete implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    return List.of("생성", "삭제", "목록", "이동");
                }
                if (args.length == 2){
                    if (args[0].equals("생성")) return List.of("<Name>");
                    if (args[0].equals("이동") || args[0].equals("삭제")) {
                        return new WarpData().list();
                    }
                }
            } else {
                if (args.length == 1) {
                    return List.of("이동");
                }
                if (args.length == 2){
                    if (args[0].equals("이동")) {
                        for (String warpList : new WarpData().list()) {
                            return List.of(warpList);
                        }
                    }
                }
            }
        }

        return null;
    }
}
