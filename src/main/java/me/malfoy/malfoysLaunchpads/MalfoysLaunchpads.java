package me.malfoy.malfoysLaunchpads;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.malfoy.malfoysLaunchpads.commands.LaunchpadCommand;
import me.malfoy.malfoysLaunchpads.listeners.LaunchListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MalfoysLaunchpads extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Malfoys Launchpads enabled");

        // Register Events
        getServer().getPluginManager().registerEvents(new LaunchListener(this), this);

        // Register Tree Command
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(LaunchpadCommand.create(this));
        });
        // Config
        saveDefaultConfig(); // adds config if dosnt exist
        getConfig().options().copyDefaults(true); // puts new values into config
        saveConfig(); // well i think i can remember what this does... its in the name.
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
