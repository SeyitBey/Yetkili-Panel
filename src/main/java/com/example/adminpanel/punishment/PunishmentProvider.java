package com.example.adminpanel.punishment;

import com.example.adminpanel.model.Punishment;
import java.util.concurrent.CompletableFuture;

public interface PunishmentProvider {
    CompletableFuture<Boolean> applyBan(Punishment punishment);
    CompletableFuture<Boolean> applyMute(Punishment punishment);
    CompletableFuture<Boolean> applyKick(String targetName, String reason, String staffName);
    CompletableFuture<Boolean> revokeBan(String targetName, String staffName);
    CompletableFuture<Boolean> revokeMute(String targetName, String staffName);
}
