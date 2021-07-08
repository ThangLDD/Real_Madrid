package com.example.realmadrid.model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private String birthday;
    private int kit;
    private String position;

    public Player() {
    }

    public Player(String name, String birthday, int kit, String position) {
        this.name = name;
        this.birthday = birthday;
        this.kit = kit;
        this.position = position;
    }

    public Player(int id, String name, String birthday, int kit, String position) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.kit = kit;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getKit() {
        return kit;
    }

    public void setKit(int kit) {
        this.kit = kit;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
