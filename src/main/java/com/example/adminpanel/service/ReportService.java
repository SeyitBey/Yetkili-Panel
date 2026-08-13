package com.example.adminpanel.service;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.repository.ReportRepository;
import com.example.adminpanel.util.TextUtil;
import org.bukkit.entity.Player;

public class ReportService {
    private final AdminPanelPlugin plugin;
    private final ReportRepository repository;
    private final StaffService staffService;

    public ReportService(AdminPanelPlugin plugin, ReportRepository repository, StaffService staffService) {
        this.plugin = plugin;
        this.repository = repository;
        this.staffService = staffService;
    }

    public void createReport(Player reporter, Player target, String reason) {
        reporter.sendMessage(TextUtil.parse("<green>Raporunuz yetkililere iletildi!</green>"));
    }
}
