package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.repository.PlayerRepository;
import org.bukkit.entity.Player;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerService {
    private final AdminPanelPlugin plugin;
    private final PlayerRepository repository;
    private final Map<UUID, Long> sessionStartMap = new ConcurrentHashMap<>();

    public PlayerService(AdminPanelPlugin plugin, PlayerRepository repository) {
        this.plugin = plugin;
        this.repository = repository;
    }

    public void handlePlayerJoin(Player player) {
        sessionStartMap.put(player.getUniqueId(), System.currentTimeMillis());
    }

    public void handlePlayerQuit(Player player) {
        sessionStartMap.remove(player.getUniqueId());
    }

    public long getTotalPlaytimeSeconds(Player player) {
        Long start = sessionStartMap.get(player.getUniqueId());
        long current = start != null ? (System.currentTimeMillis() - start) / 1000 : 0;
        return current;
    }

    public void saveAllActiveSessions() {
        sessionStartMap.clear();
    }
}
