package com.example.adminpanel.listener;

import com.example.adminpanel.service.PlayerService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {
    private final PlayerService service;
    public PlayerJoinQuitListener(PlayerService service) { this.service = service; }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) { service.handlePlayerJoin(e.getPlayer()); }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) { service.handlePlayerQuit(e.getPlayer()); }
}
