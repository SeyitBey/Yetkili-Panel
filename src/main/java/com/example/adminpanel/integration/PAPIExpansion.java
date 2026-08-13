package com.example.adminpanel.integration.placeholder;

import com.example.adminpanel.AdminPanelPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.jetbrains.annotations.NotNull;

public class PAPIExpansion extends PlaceholderExpansion {
    private final AdminPanelPlugin plugin;

    public PAPIExpansion(AdminPanelPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "adminpanel";
    }

    @Override
    public @NotNull String getAuthor() {
        return "AdminPanel Team";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
}