package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BroadcastMenu {
    private final AdminPanelPlugin plugin;
    private final Player viewer;

    public BroadcastMenu(AdminPanelPlugin plugin, Player viewer) {
        this.plugin = plugin;
        this.viewer = viewer;
    }

    public void open() {
        GUIHolder holder = new GUIHolder(27, TextUtil.parse("<light_purple><bold>DUYURU PANELi</bold></light_purple>"));
        holder.setButton(11, createBtn(Material.BEACON, "<gold>Yetkili Duyurusu</gold>", e -> {
            plugin.getNotificationService().broadcastStaff("Toplanti zamani!");
            viewer.closeInventory();
        }));
        holder.setButton(15, createBtn(Material.NETHER_STAR, "<aqua>Sunucu Duyurusu</aqua>", e -> {
            plugin.getNotificationService().broadcastServer("Sunucu bakimi yakinda!", "DIKKAT");
            viewer.closeInventory();
        }));
        viewer.openInventory(holder.getInventory());
    }

    private GUIButton createBtn(Material mat, String title, java.util.function.Consumer<org.bukkit.event.inventory.InventoryClickEvent> act) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) { meta.displayName(TextUtil.parse(title)); item.setItemMeta(meta); }
        return new GUIButton(item, act);
    }
}
