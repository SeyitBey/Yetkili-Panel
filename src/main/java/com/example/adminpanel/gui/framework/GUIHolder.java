package com.example.adminpanel.gui.framework;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;

public class GUIHolder implements InventoryHolder {
    private final Inventory inventory;
    private final Map<Integer, GUIButton> buttons = new HashMap<>();

    public GUIHolder(int size, Component title) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void setButton(int slot, GUIButton button) {
        buttons.put(slot, button);
        if (button != null) inventory.setItem(slot, button.getItemStack());
    }

    public GUIButton getButton(int slot) { return buttons.get(slot); }

    @Override
    public @NotNull Inventory getInventory() { return inventory; }
}
