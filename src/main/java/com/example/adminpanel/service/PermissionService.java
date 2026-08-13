package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.command.CommandSender;

public class PermissionService {
    private final AdminPanelPlugin plugin;
    private final HierarchyService hierarchyService;

    public PermissionService(AdminPanelPlugin plugin, HierarchyService hierarchyService) {
        this.plugin = plugin;
        this.hierarchyService = hierarchyService;
    }

    public boolean hasPermission(CommandSender sender, String node) {
        if (sender == null) return false;
        if (sender.isOp() || sender.hasPermission("adminpanel.*")) return true;
        return sender.hasPermission(node);
    }
}
