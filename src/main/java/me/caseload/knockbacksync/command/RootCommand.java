package me.caseload.knockbacksync.command;

import me.caseload.knockbacksync.KnockbackSync;
import me.caseload.knockbacksync.command.sub.*;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class RootCommand {

    public static LiteralCommandNode<CommandSourceStack> createCommand(String root, KnockbackSync plugin) {
        return Commands.literal(root)
                .requires(ctx -> ctx.getSender().hasPermission("knockbacksync.command"))
                .executes(ctx -> {
                    ctx.getSource().getSender().sendRichMessage(
                        """
                        
                        <white>Running <gold>KnockbackSync</gold> <gray>v1.2.3</gray></white>
                        <white>Developed & Maintained by <gold>Voltraz Development Team</gold> (<gold>RevAnjay</gold>)</white>
                        <gray>A modernized fork of KnockbackSync by CASELOAD7000.</gray>
                        
                        """
                    );
                    return Command.SINGLE_SUCCESS;
                })
                .then(PingCommand.register(plugin))
                .then(ToggleCommand.register(plugin))
                .then(OffGroundCommand.register(plugin))
                .then(OffsetCommand.register(plugin))
                .then(MaxDamageAgeCommand.register(plugin))
                .build();
    }
}
