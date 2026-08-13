package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NotificationService {
    private final AdminPanelPlugin plugin;

    public NotificationService(AdminPanelPlugin plugin) { this.plugin = plugin; }

    public void broadcastStaff(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("adminpanel.broadcast.staff")) {
                p.sendMessage(TextUtil.parse("<gold>[STAFF]</gold> " + message));
            }
        }
    }

    public void broadcastServer(String message, String title) {
        Bukkit.broadcast(TextUtil.parse("<gradient:#00FFAA:#0088FF>[DUYURU]</gradient> " + message));
    }
}
