package com.example.adminpanel.analytics;

import com.example.adminpanel.AdminPanelPlugin;
import org.bukkit.Bukkit;

public class AnalyticsService {
    private final AdminPanelPlugin plugin;

    public AnalyticsService(AdminPanelPlugin plugin) {
        this.plugin = plugin;
    }

    public double getTPS() {
        double[] tps = Bukkit.getTPS();
        return tps.length > 0 ? Math.min(20.0, Math.round(tps[0] * 100.0) / 100.0) : 20.0;
    }

    public double getMSPT() {
        return Math.round(Bukkit.getAverageTickTime() * 100.0) / 100.0;
    }

    public long getUsedMemoryMB() {
        Runtime r = Runtime.getRuntime();
        return (r.totalMemory() - r.freeMemory()) / (1024 * 1024);
    }

    public long getMaxMemoryMB() {
        return Runtime.getRuntime().maxMemory() / (1024 * 1024);
    }

    public int getOnlinePlayersCount() {
        return Bukkit.getOnlinePlayers().size();
    }

    public int getOnlineStaffCount() {
        return (int) Bukkit.getOnlinePlayers().stream().filter(p -> plugin.getStaffService().isStaff(p)).count();
    }

    public long getServerUptimeMs() { return 1000L; }
}
