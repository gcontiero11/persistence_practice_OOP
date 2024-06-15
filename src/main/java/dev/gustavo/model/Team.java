package dev.gustavo.model;

import java.util.Map;

public class Team {
    private final int id;
    private String name;
    private String baseLocation;
    private String coachName;
    private final Map<String,Player> playersCache;

    public Team(int id, String name, String baseLocation, String coachName, Map<String, Player> playersCache) {
        this.id = id;
        this.name = name;
        this.baseLocation = baseLocation;
        this.coachName = coachName;
        this.playersCache = playersCache;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
