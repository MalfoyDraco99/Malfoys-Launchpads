package me.malfoy.malfoysLaunchpads.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.malfoy.malfoysLaunchpads.MalfoysLaunchpads;

public class LaunchpadCommand {
    public static LiteralCommandNode<CommandSourceStack> create(MalfoysLaunchpads plugin){
        return Commands.literal("launchpad")
                .then(Commands.literal("reload")
                        .requires(sourceStack -> {
                            return sourceStack.getSender().hasPermission("launchpads.reload");
                        })
                        .executes(context -> {
                            plugin.reloadConfig();
                            context.getSource().getSender().sendRichMessage("<green>Config has been reloaded!");
                            return 1;
                        }))
                .build();
    }
}
