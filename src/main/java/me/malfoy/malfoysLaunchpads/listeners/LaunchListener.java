package me.malfoy.malfoysLaunchpads.listeners;

import me.malfoy.malfoysLaunchpads.MalfoysLaunchpads;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import static java.lang.Math.sqrt;

public class LaunchListener implements Listener {
    private final MalfoysLaunchpads plugin;

    public LaunchListener(MalfoysLaunchpads plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerInteractEvent event){
        Block block = event.getClickedBlock();
        // exits if not good
        if (event.getAction() != Action.PHYSICAL || block == null || block.getType() == Material.AIR) {
            return;
        }

        Player player = event.getPlayer();
        String configgeworld = plugin.getConfig().getString("world","world");
        // exits if world is wrong
        if (!(player.getWorld().getName().equalsIgnoreCase(configgeworld))){
            return;
        }

        Material configgedPlate = Material.matchMaterial(plugin.getConfig().getString("pressure-plate","STONE_PRESSURE_PLATE"));
        // exits if plate is wrong
        if (!(block.getType() == configgedPlate)){
            return;
        }

        Block blockUnderneath = block.getLocation().add(0,-1,0).getBlock();
        Material configgedBase = Material.matchMaterial(plugin.getConfig().getString("bottom-block","STONE"));

        // exits if base is wrong
        if (!(blockUnderneath.getType() == configgedBase)){
            return;
        }
        // get important crap from config
        double multi = plugin.getConfig().getDouble("velocity-multiplier",2);
        String message = plugin.getConfig().getString("launch-message", "<gold>Whoooosh!");
        // applies velocity, sound, ect.
        Vector velocity = player.getLocation().getDirection().multiply(multi).setY(sqrt(multi));
        player.setVelocity(velocity);
        player.sendRichMessage(message);
        player.getWorld().playSound(player.getLocation(),Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,3,1);
    }
}
