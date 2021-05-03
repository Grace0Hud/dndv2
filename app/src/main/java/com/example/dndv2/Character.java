package com.example.dndv2;

import android.util.Log;

import java.util.ArrayList;

public class Character
{
    static Dice d6 = new Dice(6);
    private String name;
    private int level, str, dex, con, intel, wis, cha;
    private int[] stats = {str, dex, con, intel, wis, cha};

    public  Character()
    {
        this.name = "Sam Smorkle";
        this.level = 1;
        setUp();
    }
    public Character(String name)
    {
        this.name = name;
        this.level = 1;
        setUp();
    }
    public Character(String name, int level)
    {
        this.name = name;
        this.level = level;
        setUp();
    }
    public Character(String name, int level, int[] stats)
    {
        this.name = name;
        this.level = level;
        setStats(stats);
    }

    private void setUp()
    {
        for(int i = 0; i < stats.length; i++)
        {
            stats[i] = rollStat();
        }
    }

    //getters


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getIntel() {
        return intel;
    }

    public int getWis() {
        return wis;
    }

    public int getCha() {
        return cha;
    }

    public int[] getStats() {
        return stats;
    }

    public void setName(String name) {
        this.name = name;
    }

    //setters
    public void setLevel(int level) {
        this.level = level;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setIntel(int intel) {
        this.intel = intel;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public void setCha(int cha) {
        this.cha = cha;
    }

    public void setStats(int[] stats) {
        this.str = stats[0];
        this.dex = stats[1];
        this.con = stats[2];
        this.intel = stats[3];
        this.wis = stats[4];
        this.cha = stats[5];
    }

    public static int rollStat()
    {
        int stat = 0;

        int[] rolls = new int[4];
        for(int i = 0; i < rolls.length; i++)
        {
            rolls[i] = d6.roll(1);
            stat += rolls[i];
        }
        int small = rolls[0];
        for(int i = 0; i < rolls.length; i++)
        {
            if(small > rolls[i])
            {
                small = rolls[i];
            }
        }
        stat = stat - small;

        Log.i("final stat:", String.valueOf(stat));
        return stat;

    }
    public String toString()
    {
        String output = "";
        output += "-----" + name + "----";
        output += "\nlevel: " + level;
        output += "\nstr: " + str + "\tdex: " + dex + "\tcon: " + con;
        output += "\nint: " + intel + "\twis: " + wis + "\tcha: " + cha;
        return output;
    }
}
