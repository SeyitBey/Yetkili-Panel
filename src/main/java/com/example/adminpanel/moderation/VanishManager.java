package com.example.adminpanel.moderation;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.entity.Player;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class VanishManager {
    private final AdminPanelPlugin plugin;
    private final Set<UUID> vanished = ConcurrentHashMap.newKeySet();

    public VanishManager(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public boolean isVanished(UUID uuid) { return vanished.contains(uuid); }
    public void updateVisibilityForJoinedPlayer(Player player) {}
    public void cleanup() { vanished.clear(); }
}
