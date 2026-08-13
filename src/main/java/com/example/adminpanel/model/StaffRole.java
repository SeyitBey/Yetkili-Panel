package com.example.adminpanel.model;

import java.util.List;
import java.util.Map;

public record StaffRole(
        String key,
        String displayName,
        int priority,
        String luckpermsGroup,
        List<String> inherits,
        Map<String, Boolean> permissions
) {}
