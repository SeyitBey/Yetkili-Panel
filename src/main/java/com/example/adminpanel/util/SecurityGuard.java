package com.example.adminpanel.util;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public final class SecurityGuard {
    public record SecurityResult(boolean allowed, String failureReason) {
        public static SecurityResult allow() { return new SecurityResult(true, null); }
        public static SecurityResult deny(String reason) { return new SecurityResult(false, reason); }
    }

    public static SecurityResult validate(AdminPanelPlugin plugin, CommandSender actor, Player target, String permissionNode) {
        if (actor == null) return SecurityResult.deny("Gecersiz eylem sahibi!");
        if (actor instanceof ConsoleCommandSender) return SecurityResult.allow();
        Player actorPlayer = (Player) actor;
        if (permissionNode != null && !permissionNode.isEmpty()) {
            if (!plugin.getPermissionService().hasPermission(actorPlayer, permissionNode)) {
                return SecurityResult.deny("<red>Yetkiniz yetersiz!</red>");
            }
        }
        if (target == null) return SecurityResult.allow();
        if (actorPlayer.getUniqueId().equals(target.getUniqueId())) {
            return SecurityResult.deny("<red>Kendi uzerinizde islem yapamazsiniz!</red>");
        }
        if (target.hasPermission("adminpanel.punishment.exempt")) {
            return SecurityResult.deny("<red>Bu oyuncu ceza muafiyetine sahip!</red>");
        }
        if (!plugin.getHierarchyService().canActOn(actorPlayer, target)) {
            return SecurityResult.deny("<red>Hiyerarşi engeli!</red>");
        }
        return SecurityResult.allow();
    }
}
