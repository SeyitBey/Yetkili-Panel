package com.example.adminpanel.moderation;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.entity.Player;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StaffModeManager {
    private final AdminPanelPlugin plugin;
    private final Set<UUID> active = ConcurrentHashMap.newKeySet();

    public StaffModeManager(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public boolean isStaffModeActive(UUID uuid) { return active.contains(uuid); }
    public void disableStaffMode(Player player) { active.remove(player.getUniqueId()); }
    public void cleanup() { active.clear(); }
}
