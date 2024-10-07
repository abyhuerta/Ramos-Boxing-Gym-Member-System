package edu.utsa.cs3443.rowdy_rumble.model;

public class Stats {
    private String name;
    private int ProWins;
    private int AmWins;
    private int checkins;
    private int dayCreated;

    private String memberSince;

    public Stats(String name, int ProWins, int AmWins, int checkins, int dayCreated, String memberSince){
    this.name = name;
    this.ProWins = ProWins;
    this.AmWins = AmWins;
    this.checkins = checkins;
    this.dayCreated = dayCreated;
    this.memberSince = memberSince;
    }

    public String getName() {
        return name;
    }

    public int getProWins() {
        return ProWins;
    }

    public int getAmWins() {
        return AmWins;
    }

    public int getCheckins() {
        return checkins;
    }

    public int getDayCreated() {
        return dayCreated;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProWins(int proWins) {
        ProWins = proWins;
    }

    public void setAmWins(int amWins) {
        AmWins = amWins;
    }

    public void setCheckins(int checkins) {
        this.checkins = checkins;
    }

    public void setDayCreated(int dayCreated) {
        this.dayCreated = dayCreated;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }

}

