package de.oliver.listeners;

import de.oliver.FancyHolograms;
import de.oliver.Hologram;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        CraftPlayer craftPlayer = (CraftPlayer) event.getPlayer();
        ServerPlayer serverPlayer = craftPlayer.getHandle();

        for (Hologram hologram : FancyHolograms.getInstance().getHologramManager().getAllHolograms()) {
            hologram.spawn(serverPlayer);
        }
    }

}
