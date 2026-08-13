package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerControlMenu {
    private final AdminPanelPlugin plugin;
    private final Player viewer;
    private final Player target;

    public PlayerControlMenu(AdminPanelPlugin plugin, Player viewer, Player target) {
        this.plugin = plugin;
        this.viewer = viewer;
        this.target = target;
    }

    public void open() {
        GUIHolder holder = new GUIHolder(27, TextUtil.parse("<red><bold>KONTROL:</bold> " + target.getName() + "</red>"));

        holder.setButton(11, createBtn(Material.REDSTONE_BLOCK, "<red>Ban</red>", e -> {
            if (!plugin.getHierarchyService().canActOn(viewer, target)) {
                viewer.sendMessage(TextUtil.parse("<red>Hiyerarşi engeli!</red>"));
                return;
            }
            plugin.getPunishmentService().issueBan(viewer, target.getUniqueId(), target.getName(), "AdminPanel GUI Ban", -1L);
            viewer.closeInventory();
        }));

        holder.setButton(13, createBtn(Material.JUKEBOX, "<gold>Mute</gold>", e -> {
            if (!plugin.getHierarchyService().canActOn(viewer, target)) {
                viewer.sendMessage(TextUtil.parse("<red>Hiyerarşi engeli!</red>"));
                return;
            }
            plugin.getPunishmentService().issueMute(viewer, target.getUniqueId(), target.getName(), "AdminPanel GUI Mute", 3600L);
            viewer.closeInventory();
        }));

        holder.setButton(15, createBtn(Material.ICE, "<aqua>Freeze</aqua>", e -> {
            boolean state = plugin.getFreezeManager().toggleFreeze(target.getUniqueId());
            viewer.sendMessage(TextUtil.parse("<yellow>" + target.getName() + " dondurma durumu: " + state + "</yellow>"));
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
