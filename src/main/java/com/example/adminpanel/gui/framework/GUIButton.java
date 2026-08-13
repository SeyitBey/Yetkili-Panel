package com.example.adminpanel.gui.framework;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import java.util.function.Consumer;

public class GUIButton {
    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> action;

    public GUIButton(ItemStack itemStack, Consumer<InventoryClickEvent> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public ItemStack getItemStack() { return itemStack; }
    public void onClick(InventoryClickEvent event) { if (action != null) action.accept(event); }
}
