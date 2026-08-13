package com.example.adminpanel.punishment;

public enum PunishmentType {
    BAN, TEMPBAN, UNBAN, MUTE, TEMPMUTE, UNMUTE, KICK, WARN, FREEZE, UNFREEZE;
    public boolean isMute() { return this == MUTE || this == TEMPMUTE; }
}
