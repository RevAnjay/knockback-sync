package me.caseload.knockbacksync;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.caseload.knockbacksync.command.RootCommand;
import me.caseload.knockbacksync.listener.PlayerDamageListener;
import me.caseload.knockbacksync.listener.PlayerVelocityListener;
import me.caseload.knockbacksync.listener.QuitListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class KnockbackSync extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        registerListeners(
                new PlayerDamageListener(),
                new PlayerVelocityListener(),
                new QuitListener()
        );
        registerCommands();

        this.getLogger().info("KnockbackSync has been successfully enabled.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("KnockbackSync has been disabled.");
    }

    public static KnockbackSync getInstance() {
        return getPlugin(KnockbackSync.class);
    }

    private void registerListeners(Listener... listeners) {
        PluginManager pluginManager = getServer().getPluginManager();
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, this);
    }

    private void registerCommands() {
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> event.registrar().register(RootCommand.createCommand("knockbacksync", this), "KnockbackSync Command",
                List.of("kbsync")
        ));
    }

}