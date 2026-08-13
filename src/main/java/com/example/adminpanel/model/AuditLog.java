package com.example.adminpanel.model;

import java.util.UUID;

public record AuditLog(
        String id,
        String action,
        UUID actorUuid,
        String actorName,
        UUID targetUuid,
        String targetName,
        String reason,
        long timestamp,
        String metadata
) {}
