package com.example.paintsplat;

public class Player {

    public String name;
    public Integer count;

    public Player() {
        // Default constructor required for calls to DataSnapshot.getValue(Player.class)
    }

    public Player(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

}
