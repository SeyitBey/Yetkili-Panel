package com.example.adminpanel.listener;

import com.example.adminpanel.moderation.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener {
    private final FreezeManager manager;
    public FreezeListener(FreezeManager manager) { this.manager = manager; }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (manager.isFrozen(e.getPlayer().getUniqueId())) {
            if (e.hasChangedBlockCoordinates()) e.setCancelled(true);
        }
    }
}
