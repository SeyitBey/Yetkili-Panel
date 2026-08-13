package com.example.adminpanel.model;

import com.example.adminpanel.punishment.PunishmentType;
import java.util.UUID;

public record Punishment(
        String id,
        UUID targetUuid,
        String targetName,
        UUID staffUuid,
        String staffName,
        PunishmentType type,
        String reason,
        long createdAt,
        Long expiresAt,
        boolean active
) {
    public boolean isExpired() {
        if (expiresAt == null) return false;
        return System.currentTimeMillis() >= expiresAt;
    }
}
