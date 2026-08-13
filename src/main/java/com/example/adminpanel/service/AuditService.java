package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.model.AuditLog;
import com.example.adminpanel.repository.AuditRepository;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AuditService {
    private final AdminPanelPlugin plugin;
    private final AuditRepository repository;

    public AuditService(AdminPanelPlugin plugin, AuditRepository repository) {
        this.plugin = plugin;
        this.repository = repository;
    }

    public CompletableFuture<List<AuditLog>> getRecentLogs(int limit) {
        return CompletableFuture.completedFuture(List.of());
    }
}
