package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.gui.framework.PaginatedGUI;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AuditLogMenu extends PaginatedGUI {
    private final AdminPanelPlugin plugin;
    private final Player viewer;

    public AuditLogMenu(AdminPanelPlugin plugin, Player viewer) {
        super(54, TextUtil.parse("<blue><bold>DENETIM KAYITLARI</bold></blue>"));
        this.plugin = plugin;
        this.viewer = viewer;
    }

    @Override
    public void openPage(int pageNumber) {
        GUIHolder holder = new GUIHolder(54, title);
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(TextUtil.parse("<yellow>Denetim Kayitlari</yellow>"));
            item.setItemMeta(meta);
        }
        holder.setButton(22, new GUIButton(item, null));
        viewer.openInventory(holder.getInventory());
    }
}
