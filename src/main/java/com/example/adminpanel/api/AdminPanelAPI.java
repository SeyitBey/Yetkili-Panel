package com.example.adminpanel.api;

import com.example.adminpanel.AdminPanelPlugin;
import com.example.adminpanel.analytics.AnalyticsService;
import com.example.adminpanel.service.*;

public final class AdminPanelAPI {
    private AdminPanelAPI() {}
    public static AdminPanelPlugin getPlugin() { return AdminPanelPlugin.getInstance(); }
    public static PlayerService getPlayerService() { return getPlugin().getPlayerService(); }
    public static PunishmentService getPunishmentService() { return getPlugin().getPunishmentService(); }
    public static ReportService getReportService() { return getPlugin().getReportService(); }
    public static StaffService getStaffService() { return getPlugin().getStaffService(); }
    public static NotificationService getNotificationService() { return getPlugin().getNotificationService(); }
    public static AuditService getAuditService() { return getPlugin().getAuditService(); }
    public static AnalyticsService getAnalyticsService() { return getPlugin().getAnalyticsService(); }
}
