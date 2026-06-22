package me.caseload.knockbacksync.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.command.CommandSender;

public class ToggleCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register(KnockbackSync plugin) {
        return Commands.literal("toggle")
                .requires(source -> source.getSender().hasPermission("knockbacksync.toggle"))
                .executes(context -> {
                    final CommandSender sender = context.getSource().getSender();
                    final var config = plugin.getConfig();

                    boolean toggledState = !config.getBoolean("enabled");
                    config.set("enabled", toggledState);
                    plugin.saveConfig();

                    String message = config.getString(toggledState ? "enable_message" : "disable_message");
                    if (message == null) {
                        message = toggledState ? "<green>Successfully enabled KnockbackSync." : "<red>Successfully disabled KnockbackSync.";
                    }

                    sender.sendRichMessage(message);
                    return Command.SINGLE_SUCCESS;
                });
    }
}
