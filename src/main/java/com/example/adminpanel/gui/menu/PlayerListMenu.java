package com.example.adminpanel.gui.menu;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.gui.framework.GUIButton;
import com.example.adminpanel.gui.framework.GUIHolder;
import com.example.adminpanel.gui.framework.PaginatedGUI;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.ArrayList;
import java.util.List;

public class PlayerListMenu extends PaginatedGUI {
    private final AdminPanelPlugin plugin;
    private final Player viewer;

    public PlayerListMenu(AdminPanelPlugin plugin, Player viewer) {
        super(54, TextUtil.parse("<dark_aqua><bold>CEVRIMICI OYUNCULAR</bold></dark_aqua>"));
        this.plugin = plugin;
        this.viewer = viewer;
    }

    @Override
    public void openPage(int pageNumber) {
        this.page = pageNumber;
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        this.maxPages = (int) Math.ceil((double) players.size() / 36);
        if (maxPages == 0) maxPages = 1;

        GUIHolder holder = new GUIHolder(54, title);
        int startIndex = (page - 1) * 36;
        int endIndex = Math.min(startIndex + 36, players.size());

        for (int i = startIndex; i < endIndex; i++) {
            Player target = players.get(i);
            int slot = i - startIndex;
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            if (meta != null) {
                meta.setOwningPlayer(target);
                meta.displayName(TextUtil.parse("<green><bold>" + target.getName() + "</bold></green>"));
                head.setItemMeta(meta);
            }
            holder.setButton(slot, new GUIButton(head, e -> new PlayerControlMenu(plugin, viewer, target).open()));
        }

        viewer.openInventory(holder.getInventory());
    }
}
