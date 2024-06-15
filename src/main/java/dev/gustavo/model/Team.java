package dev.gustavo.model;

import java.util.HashMap;
import java.util.Map;

public class Team {
    private final int id;
    private String name;
    private String baseLocation;
    private Player captain;
    private String coachName;
    private final Map<String,Player> playersCache;

    public Team(int id, String name, String baseLocation,Player captain, String coachName) {
        this.id = id;
        this.name = name;
        this.baseLocation = baseLocation;
        this.captain = captain;
        this.coachName = coachName;
        this.playersCache = new HashMap<>();
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

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
