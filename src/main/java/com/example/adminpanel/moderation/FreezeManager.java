package com.example.adminpanel.moderation;

import com.example.adminpanel.AdminPanelPlugin;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class FreezeManager {
    private final AdminPanelPlugin plugin;
    private final Set<UUID> frozen = ConcurrentHashMap.newKeySet();

    public FreezeManager(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public boolean toggleFreeze(UUID uuid) {
        if (frozen.contains(uuid)) { frozen.remove(uuid); return false; }
        else { frozen.add(uuid); return true; }
    }

    public boolean isFrozen(UUID uuid) { return frozen.contains(uuid); }
    public void shutdown() { frozen.clear(); }
}
