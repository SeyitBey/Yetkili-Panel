package com.example.adminpanel;

import com.example.adminpanel.analytics.AnalyticsService;
import com.example.adminpanel.command.AdminPanelCommand;
import com.example.adminpanel.command.ReportCommand;
import com.example.adminpanel.config.ConfigManager;
import com.example.adminpanel.database.DatabaseManager;
import com.example.adminpanel.gui.framework.GUIListener;
import com.example.adminpanel.integration.discord.DiscordWebhookClient;
import com.example.adminpanel.integration.essentials.EssentialsHook;
import com.example.adminpanel.integration.luckperms.LuckPermsHook;
import com.example.adminpanel.integration.placeholder.PAPIExpansion;
import com.example.adminpanel.listener.ChatListener;
import com.example.adminpanel.listener.FreezeListener;
import com.example.adminpanel.listener.PlayerJoinQuitListener;
import com.example.adminpanel.listener.VanishListener;
import com.example.adminpanel.moderation.FreezeManager;
import com.example.adminpanel.moderation.StaffModeManager;
import com.example.adminpanel.moderation.VanishManager;
import com.example.adminpanel.repository.*;
import com.example.adminpanel.service.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;

/**
 * Main bootstrap class for AdminPanel.
 * Manages plugin lifecycle, service loading, commands, and listeners.
 */
public final class AdminPanelPlugin extends JavaPlugin {

    private static AdminPanelPlugin instance;
    private ConfigManager configManager;
    private DatabaseManager databaseManager;

    private PlayerRepository playerRepository;
    private PunishmentRepository punishmentRepository;
    private AuditRepository auditRepository;
    private StaffRepository staffRepository;
    private ReportRepository reportRepository;

    private HierarchyService hierarchyService;
    private PermissionService permissionService;
    private PlayerService playerService;
    private PunishmentService punishmentService;
    private StaffService staffService;
    private ReportService reportService;
    private NotificationService notificationService;
    private AuditService auditService;
    private AnalyticsService analyticsService;
    private DiscordWebhookClient discordWebhookClient;
    private EssentialsHook essentialsHook;
    private LuckPermsHook luckPermsHook;
    private FreezeManager freezeManager;
    private VanishManager vanishManager;
    private StaffModeManager staffModeManager;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        getLogger().info("==================================================");
        getLogger().info(" AdminPanel Platform - Baslatiliyor...");
        getLogger().info(" Version: " + getDescription().getVersion());
        getLogger().info("==================================================");

        try {
            // 1. DataFolder olustur
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }

            // 2. Configurations yukle
            this.configManager = new ConfigManager(this);
            this.configManager.loadConfigurations();

            // 3. Database yukle
            this.databaseManager = new DatabaseManager(this);
            this.databaseManager.initialize();

            // 4. Integrations yukle
            this.discordWebhookClient = new DiscordWebhookClient(this);

            this.essentialsHook = new EssentialsHook(this);
            this.essentialsHook.initialize();

            this.luckPermsHook = new LuckPermsHook(this);
            this.luckPermsHook.initialize();

            // 5. Repositories yukle
            this.playerRepository = new PlayerRepository(databaseManager);
            this.punishmentRepository = new PunishmentRepository(databaseManager);
            this.auditRepository = new AuditRepository(databaseManager);
            this.staffRepository = new StaffRepository(databaseManager);
            this.reportRepository = new ReportRepository(databaseManager);

            // 6. Services & Managers yukle
            this.hierarchyService = new HierarchyService(this);
            this.permissionService = new PermissionService(this, hierarchyService);
            this.playerService = new PlayerService(this, playerRepository);
            this.punishmentService = new PunishmentService(this, punishmentRepository, auditRepository);
            this.staffService = new StaffService(this, staffRepository, hierarchyService);
            this.reportService = new ReportService(this, reportRepository, staffService);
            this.notificationService = new NotificationService(this);
            this.auditService = new AuditService(this, auditRepository);
            this.analyticsService = new AnalyticsService(this);
            this.freezeManager = new FreezeManager(this);
            this.vanishManager = new VanishManager(this);
            this.staffModeManager = new StaffModeManager(this);

            // 7. PlaceholderAPI kaydi
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new PAPIExpansion(this).register();
            }

            // 8. Komutlari kaydet
            Objects.requireNonNull(getCommand("adminpanel")).setExecutor(new AdminPanelCommand(this));
            Objects.requireNonNull(getCommand("report")).setExecutor(new ReportCommand(reportService));

            // 9. Dinleyicileri (Listeners) kaydet
            getServer().getPluginManager().registerEvents(new GUIListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerJoinQuitListener(playerService), this);
            getServer().getPluginManager().registerEvents(new ChatListener(punishmentService), this);
            getServer().getPluginManager().registerEvents(new FreezeListener(freezeManager), this);
            getServer().getPluginManager().registerEvents(new VanishListener(vanishManager, staffModeManager), this);

            getLogger().info("[AdminPanel] Tum servisler ve dinleyiciler basariyla aktif edildi!");

        } catch (Throwable throwable) {
            getLogger().log(Level.SEVERE, "[AdminPanel] Kritik baslatma hatasi!", throwable);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        getLogger().info("[AdminPanel] Yukleme " + elapsedTime + "ms icinde tamamlandi.");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        if (this.staffModeManager != null) this.staffModeManager.cleanup();
        if (this.vanishManager != null) this.vanishManager.cleanup();
        if (this.freezeManager != null) this.freezeManager.shutdown();
        if (this.playerService != null) this.playerService.saveAllActiveSessions();
        if (this.databaseManager != null) this.databaseManager.shutdown();

        this.discordWebhookClient = null;
        this.luckPermsHook = null;
        this.essentialsHook = null;
        this.analyticsService = null;
        this.auditService = null;
        this.notificationService = null;
        this.reportService = null;
        this.staffService = null;
        this.punishmentService = null;
        this.playerRepository = null;
        this.punishmentRepository = null;
        this.auditRepository = null;
        this.staffRepository = null;
        this.reportRepository = null;
        this.permissionService = null;
        this.hierarchyService = null;
        this.configManager = null;
        instance = null;
    }

    public static AdminPanelPlugin getInstance() { return instance; }
    public ConfigManager getConfigManager() { return configManager; }
    public DatabaseManager getDatabaseManager() { return databaseManager; }
    public PlayerRepository getPlayerRepository() { return playerRepository; }
    public PunishmentRepository getPunishmentRepository() { return punishmentRepository; }
    public AuditRepository getAuditRepository() { return auditRepository; }
    public StaffRepository getStaffRepository() { return staffRepository; }
    public ReportRepository getReportRepository() { return reportRepository; }
    public HierarchyService getHierarchyService() { return hierarchyService; }
    public PermissionService getPermissionService() { return permissionService; }
    public PlayerService getPlayerService() { return playerService; }
    public PunishmentService getPunishmentService() { return punishmentService; }
    public StaffService getStaffService() { return staffService; }
    public ReportService getReportService() { return reportService; }
    public NotificationService getNotificationService() { return notificationService; }
    public AuditService getAuditService() { return auditService; }
    public AnalyticsService getAnalyticsService() { return analyticsService; }
    public DiscordWebhookClient getDiscordWebhookClient() { return discordWebhookClient; }
    public EssentialsHook getEssentialsHook() { return essentialsHook; }
    public LuckPermsHook getLuckPermsHook() { return luckPermsHook; }
    public FreezeManager getFreezeManager() { return freezeManager; }
    public VanishManager getVanishManager() { return vanishManager; }
    public StaffModeManager getStaffModeManager() { return staffModeManager; }
}
