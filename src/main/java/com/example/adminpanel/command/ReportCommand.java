package com.example.adminpanel.command;

import com.example.adminpanel.service.ReportService;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ReportCommand implements CommandExecutor {
    private final ReportService service;

    public ReportCommand(ReportService service) { this.service = service; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player reporter)) return true;
        if (args.length < 2) {
            reporter.sendMessage(TextUtil.parse("<red>Kullanim: /report <oyuncu> <sebep></red>"));
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null && target.isOnline()) {
            String reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            service.createReport(reporter, target, reason);
        }
        return true;
    }
}
