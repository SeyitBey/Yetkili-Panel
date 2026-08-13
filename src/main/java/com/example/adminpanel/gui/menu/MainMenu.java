package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainMenu {
    private final AdminPanelPlugin plugin;
    private final Player viewer;

    public MainMenu(AdminPanelPlugin plugin, Player viewer) {
        this.plugin = plugin;
        this.viewer = viewer;
    }

    public void open() {
        GUIHolder holder = new GUIHolder(45, TextUtil.parse("<gradient:#0088FF:#00FFAA><bold>ADMIN CONTROL PANEL</bold></gradient>"));

        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        if (glassMeta != null) { glassMeta.displayName(TextUtil.parse(" ")); glass.setItemMeta(glassMeta); }
        GUIButton filler = new GUIButton(glass, null);
        for (int i = 0; i < 45; i++) holder.setButton(i, filler);

        holder.setButton(10, createBtn(Material.PLAYER_HEAD, "<green><bold>Oyuncu Yönetimi</bold></green>", e -> new PlayerListMenu(plugin, viewer).openPage(1)));
        holder.setButton(12, createBtn(Material.NETHER_STAR, "<aqua><bold>Yetkili Yönetimi</bold></aqua>", e -> viewer.sendMessage(TextUtil.parse("<yellow>Yetkili menusu yukleniyor...</yellow>"))));
        holder.setButton(14, createBtn(Material.ANVIL, "<red><bold>Hızlı Moderasyon</bold></red>", e -> viewer.sendMessage(TextUtil.parse("<yellow>Hizli moderasyon yakinda...</yellow>"))));
        holder.setButton(16, createBtn(Material.WRITABLE_BOOK, "<gold><bold>Rapor Sistemi</bold></gold>", e -> viewer.sendMessage(TextUtil.parse("<yellow>Raporlar yukleniyor...</yellow>"))));
        holder.setButton(28, createBtn(Material.BEACON, "<light_purple><bold>Duyuru Paneli</bold></light_purple>", e -> new BroadcastMenu(plugin, viewer).open()));
        holder.setButton(30, createBtn(Material.REDSTONE_BLOCK, "<dark_green><bold>Sunucu Durumu (Dashboard)</bold></dark_green>", e -> new ServerDashboardMenu(plugin, viewer).open()));
        holder.setButton(32, createBtn(Material.BOOKSHELF, "<blue><bold>Denetim Kayıtları (Audit)</bold></blue>", e -> new AuditLogMenu(plugin, viewer).openPage(1)));

        viewer.openInventory(holder.getInventory());
    }

    private GUIButton createBtn(Material mat, String title, java.util.function.Consumer<org.bukkit.event.inventory.InventoryClickEvent> act) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) { meta.displayName(TextUtil.parse(title)); item.setItemMeta(meta); }
        return new GUIButton(item, act);
    }
}
