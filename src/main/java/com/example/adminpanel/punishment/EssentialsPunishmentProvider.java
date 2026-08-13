package com.example.adminpanel.punishment;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.model.Punishment;
import java.util.concurrent.CompletableFuture;

public class EssentialsPunishmentProvider implements PunishmentProvider {
    public EssentialsPunishmentProvider(AdminPanelPlugin plugin) {}
    public CompletableFuture<Boolean> applyBan(Punishment p) { return CompletableFuture.completedFuture(true); }
    public CompletableFuture<Boolean> applyMute(Punishment p) { return CompletableFuture.completedFuture(true); }
    public CompletableFuture<Boolean> applyKick(String t, String r, String s) { return CompletableFuture.completedFuture(true); }
    public CompletableFuture<Boolean> revokeBan(String t, String s) { return CompletableFuture.completedFuture(true); }
    public CompletableFuture<Boolean> revokeMute(String t, String s) { return CompletableFuture.completedFuture(true); }
}
