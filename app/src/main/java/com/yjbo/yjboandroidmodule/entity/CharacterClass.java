package com.yjbo.yjboandroidmodule.entity;

/**
 * Created by Aspsine on 2015/9/2.
 */
public class CharacterClass {
    private String name;
    private int avatar;

    public CharacterClass() {
    }

    public CharacterClass(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
