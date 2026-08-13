package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerDashboardMenu {
    private final AdminPanelPlugin plugin;
    private final Player viewer;

    public ServerDashboardMenu(AdminPanelPlugin plugin, Player viewer) {
        this.plugin = plugin;
        this.viewer = viewer;
    }

    public void open() {
        GUIHolder holder = new GUIHolder(27, TextUtil.parse("<dark_green><bold>SUNUCU DASHBOARD</bold></dark_green>"));
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(TextUtil.parse("<red>TPS: " + plugin.getAnalyticsService().getTPS() + "</red>"));
            item.setItemMeta(meta);
        }
        holder.setButton(13, new GUIButton(item, null));
        viewer.openInventory(holder.getInventory());
    }
}
