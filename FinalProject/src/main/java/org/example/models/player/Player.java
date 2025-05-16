package org.example.models.player;

public class Player {
    private String name;
    private int gold;
    private int foodSupply;
    public Player(String name) {
        this.name = name;
        this.gold = 0;
        this.foodSupply = 0;
    }
    public String getName() {
        return name;
    }
}
