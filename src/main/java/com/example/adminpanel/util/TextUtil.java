package com.example.adminpanel.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import java.util.List;
import java.util.stream.Collectors;

public final class TextUtil {
    private static final MiniMessage MM = MiniMessage.miniMessage();
    public static Component parse(String input) {
        if (input == null || input.isEmpty()) return Component.empty();
        return MM.deserialize(input);
    }
    public static List<Component> parseList(List<String> lines) {
        if (lines == null) return List.of();
        return lines.stream().map(TextUtil::parse).collect(Collectors.toList());
    }
}
