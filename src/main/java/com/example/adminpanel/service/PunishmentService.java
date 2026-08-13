package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.model.Punishment;
import com.example.adminpanel.repository.AuditRepository;
import com.example.adminpanel.repository.PunishmentRepository;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class PunishmentService {
    private final AdminPanelPlugin plugin;
    private final PunishmentRepository repository;
    private final AuditRepository auditRepository;
    private final Map<UUID, Punishment> activeMuteCache = new ConcurrentHashMap<>();

    public PunishmentService(AdminPanelPlugin plugin, PunishmentRepository repository, AuditRepository auditRepository) {
        this.plugin = plugin;
        this.repository = repository;
        this.auditRepository = auditRepository;
    }

    public CompletableFuture<Boolean> issueBan(Player staff, UUID targetUuid, String targetName, String reason, Long durationSeconds) {
        Bukkit.broadcast(TextUtil.parse("<red>[CEZA] " + targetName + " yasaklandi. Sebep: " + reason + "</red>"));
        return CompletableFuture.completedFuture(true);
    }

    public CompletableFuture<Boolean> issueMute(Player staff, UUID targetUuid, String targetName, String reason, Long durationSeconds) {
        Bukkit.broadcast(TextUtil.parse("<yellow>[CEZA] " + targetName + " susturuldu. Sebep: " + reason + "</yellow>"));
        return CompletableFuture.completedFuture(true);
    }

    public Optional<Punishment> getActiveMute(UUID targetUuid) {
        return Optional.ofNullable(activeMuteCache.get(targetUuid));
    }
}
