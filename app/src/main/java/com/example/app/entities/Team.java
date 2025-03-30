package com.example.app.entities;

public class Team {
    String name;
    String imagePath;

    int wins;

    int podiums;

    int championships;

    int foundedYear;

    String teamPrincipal;

    String country;

    Driver driver1;

    Driver driver2;

    String url;

    public Team(String name, String imagePath, int wins, int podiums, int championships, int foundedYear,
                String teamPrincipal, Driver driver1, Driver driver2, String url, String country) {
        this.name = name;
        this.imagePath = imagePath;
        this.wins = wins;
        this.podiums = podiums;
        this.championships = championships;
        this.foundedYear = foundedYear;
        this.teamPrincipal = teamPrincipal;
        this.driver1 = driver1;
        this.driver2 = driver2;
        this.url = url;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPodiums() {
        return podiums;
    }

    public void setPodiums(int podiums) {
        this.podiums = podiums;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public int getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(int foundedYear) {
        this.foundedYear = foundedYear;
    }

    public String getTeamPrincipal() {
        return teamPrincipal;
    }

    public void setTeamPrincipal(String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    public Driver getDriver1() {
        return driver1;
    }

    public void setDriver1(Driver driver1) {
        this.driver1 = driver1;
    }

    public Driver getDriver2() {
        return driver2;
    }

    public void setDriver2(Driver driver2) {
        this.driver2 = driver2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
