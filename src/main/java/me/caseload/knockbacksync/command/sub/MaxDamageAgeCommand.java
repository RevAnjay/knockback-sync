package me.caseload.knockbacksync.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.command.CommandSender;

public class MaxDamageAgeCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register(KnockbackSync plugin) {
        return Commands.literal("maxdamageage")
                .requires(source -> source.getSender().hasPermission("knockbacksync.maxdamageage"))
                .then(Commands.argument("value", IntegerArgumentType.integer())
                .executes(context -> {
                    final CommandSender sender = context.getSource().getSender();
                    final var config = plugin.getConfig();

                    final Integer ms = IntegerArgumentType.getInteger(context, "value");
                    config.set("max_damage_age_milliseconds", ms);
                    plugin.saveConfig();

                    String message = config.getString("configure_max_damage_age_message", "<green>Successfully configured the maximum damage age.");
                    sender.sendRichMessage(message);
                    return Command.SINGLE_SUCCESS;
                }));
    }
}
