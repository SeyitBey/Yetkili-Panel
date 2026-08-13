package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.repository.StaffRepository;
import org.bukkit.entity.Player;

public class StaffService {
    private final AdminPanelPlugin plugin;
    private final StaffRepository repository;
    private final HierarchyService hierarchyService;

    public StaffService(AdminPanelPlugin plugin, StaffRepository repository, HierarchyService hierarchyService) {
        this.plugin = plugin;
        this.repository = repository;
        this.hierarchyService = hierarchyService;
    }

    public boolean isStaff(Player player) {
        return hierarchyService.getRoleOfPlayer(player) != null;
    }
}
