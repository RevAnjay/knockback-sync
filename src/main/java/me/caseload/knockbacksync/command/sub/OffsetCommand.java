package me.caseload.knockbacksync.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.command.CommandSender;

public class OffsetCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register(KnockbackSync plugin) {
        return Commands.literal("offset")
                .requires(source -> source.getSender().hasPermission("knockbacksync.offset"))
                .then(Commands.argument("value", IntegerArgumentType.integer())
                .executes(context -> {
                    final CommandSender sender = context.getSource().getSender();
                    final var config = plugin.getConfig();

                    final Integer offset = IntegerArgumentType.getInteger(context, "value");
                    config.set("ping_offset", offset);
                    plugin.saveConfig();

                    String message = config.getString("configure_offset_message", "<green>Successfully configured the ping offset.");
                    sender.sendRichMessage(message);
                    return Command.SINGLE_SUCCESS;
                }));
    }
}
