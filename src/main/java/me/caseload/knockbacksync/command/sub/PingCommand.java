package me.caseload.knockbacksync.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import me.caseload.knockbacksync.KnockbackSync;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand {

    public static LiteralArgumentBuilder<CommandSourceStack> register(KnockbackSync plugin) {
        return Commands.literal("ping")
                .requires(source -> source.getSender().hasPermission("knockbacksync.ping"))
                .then(Commands.argument("target", ArgumentTypes.player())
                .executes(context -> {
                    final PlayerSelectorArgumentResolver targetResolver = context.getArgument("target", PlayerSelectorArgumentResolver.class);
                    final Player target = targetResolver.resolve(context.getSource()).getFirst();
                    final CommandSender sender = context.getSource().getSender();

                    int ping = target.getPing();
                    sender.sendRichMessage("<gold>" + target.getName() + "<yellow>'s last ping packet took <gold>" + ping + "ms<yellow>.");
                    return Command.SINGLE_SUCCESS;
                }));
    }
}
