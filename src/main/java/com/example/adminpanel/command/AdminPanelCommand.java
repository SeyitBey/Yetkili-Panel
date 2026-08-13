package com.example.adminpanel.command;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.menu.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminPanelCommand implements CommandExecutor {
    private final AdminPanelPlugin plugin;

    public AdminPanelCommand(AdminPanelPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            new MainMenu(plugin, player).open();
        }
        return true;
    }
}
