package com.example.adminpanel.gui.framework;

import net.kyori.adventure.text.Component;

public abstract class PaginatedGUI {
    protected final int size;
    protected final Component title;
    protected int page = 1;
    protected int maxPages = 1;

    public PaginatedGUI(int size, Component title) {
        this.size = size;
        this.title = title;
    }

    public abstract void openPage(int pageNumber);
}
