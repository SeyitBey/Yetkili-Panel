package com.example.adminpanel.listener;

import com.example.adminpanel.moderation.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Listens to player move events and prevents movement if player is frozen.
 */
public class FreezeListener implements Listener {

    private final FreezeManager manager;

    public FreezeListener(FreezeManager manager) {
        this.manager = manager;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent e) {
        if (manager.isFrozen(e.getPlayer().getUniqueId())) {
            // Check if player actually moved to a different block coordinate
            if (e.getFrom().getBlockX() != e.getTo().getBlockX() ||
                e.getFrom().getBlockY() != e.getTo().getBlockY() ||
                e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                e.setCancelled(true);
            }
        }
    }
}
