package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.model.StaffRole;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.*;

public class HierarchyService {
    private final AdminPanelPlugin plugin;
    private final Map<String, StaffRole> rolesMap = new LinkedHashMap<>();
    private StaffRole defaultRole;

    public HierarchyService(AdminPanelPlugin plugin) {
        this.plugin = plugin;
        loadRoles();
    }

    public void loadRoles() {
        rolesMap.clear();
        ConfigurationSection section = plugin.getConfigManager().getStaff().getConfigurationSection("staff-roles");
        if (section == null) return;

        for (String key : section.getKeys(false)) {
            ConfigurationSection sec = section.getConfigurationSection(key);
            if (sec == null) continue;
            String name = sec.getString("display-name", key);
            int priority = sec.getInt("priority", 0);
            StaffRole role = new StaffRole(key, name, priority, key, sec.getStringList("inherits"), new HashMap<>());
            rolesMap.put(key.toLowerCase(), role);
            if (defaultRole == null || priority < defaultRole.priority()) defaultRole = role;
        }
    }

    public StaffRole getRoleOfPlayer(Player player) {
        if (player == null) return defaultRole;
        StaffRole highest = defaultRole;
        for (StaffRole role : rolesMap.values()) {
            if (player.hasPermission("adminpanel.role." + role.key()) || player.hasPermission("group." + role.luckpermsGroup())) {
                if (highest == null || role.priority() > highest.priority()) highest = role;
            }
        }
        return highest;
    }

    public boolean canActOn(Player actor, Player target) {
        if (actor == null || target == null) return false;
        if (actor.getUniqueId().equals(target.getUniqueId())) return false;
        if (actor.isOp()) return true;
        if (target.hasPermission("adminpanel.punishment.exempt")) return false;

        StaffRole actorRole = getRoleOfPlayer(actor);
        StaffRole targetRole = getRoleOfPlayer(target);
        return actorRole != null && targetRole != null && actorRole.priority() > targetRole.priority();
    }
}
