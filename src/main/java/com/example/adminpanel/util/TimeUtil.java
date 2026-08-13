package com.example.adminpanel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TimeUtil {
    private static final Pattern DURATION_PATTERN = Pattern.compile("(?i)^(\\d+)\\s*([smhdwy])?$");

    public static long parseDurationSeconds(String input) {
        if (input == null || input.trim().isEmpty() || input.equalsIgnoreCase("PERMANENT")) return -1;
        Matcher matcher = DURATION_PATTERN.matcher(input.trim());
        if (!matcher.matches()) return -1;
        long amount = Long.parseLong(matcher.group(1));
        String unit = matcher.group(2);
        if (unit == null) return amount;
        return switch (unit.toLowerCase()) {
            case "s" -> amount;
            case "m" -> amount * 60;
            case "h" -> amount * 3600;
            case "d" -> amount * 86400;
            case "w" -> amount * 604800;
            default -> amount;
        };
    }

    public static String formatDuration(long seconds) {
        if (seconds < 0) return "Kalici";
        if (seconds == 0) return "0 Saniye";
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;
        long minutes = (seconds % 3600) / 60;
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append(" Gun ");
        if (hours > 0) sb.append(hours).append(" Saat ");
        if (minutes > 0) sb.append(minutes).append(" Dakika ");
        return sb.toString().trim();
    }
}
