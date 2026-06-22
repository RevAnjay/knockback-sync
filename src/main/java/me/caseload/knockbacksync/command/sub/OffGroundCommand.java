package me.caseload.knockbacksync.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.command.CommandSender;

public class OffGroundCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register(KnockbackSync plugin) {
        return Commands.literal("offground")
                .requires(source -> source.getSender().hasPermission("knockbacksync.offground"))
                .executes(context -> {
                    final CommandSender sender = context.getSource().getSender();
                    final var config = plugin.getConfig();

                    boolean toggledState = !config.getBoolean("toggle_offground");
                    config.set("toggle_offground", toggledState);
                    plugin.saveConfig();

                    String message = config.getString(toggledState ? "offground_enable_message" : "offground_disable_message");
                    if (message == null) {
                        message = toggledState ? "<green>Successfully enabled KnockbackSync offground." : "<red>Successfully disabled KnockbackSync offground.";
                    }

                    sender.sendRichMessage(message);
                    return Command.SINGLE_SUCCESS;
                });
    }
}
