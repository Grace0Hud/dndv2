package com.example.dndv2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Character implements Serializable
{
    static Dice d6 = new Dice(6);
    static Dice d8 = new Dice(8);
    private String name;
    private int level, hp, temphp, ac, str, dex, con, intel, wis, cha;
   // private int[] stats = {str, dex, con, intel, wis, cha};

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
        setStr(rollStat());
        setDex(rollStat());
        setCon(rollStat());
        setIntel(rollStat());
        setWis(rollStat());
        setCha(rollStat());
        updateACHP();
    }

    private void updateACHP() {
        if(level ==1)
        {
            hp = 8 + findMod(con);
        }
        else
        {
            hp = 8 + d8.roll(level) + findMod(con);
        }
        temphp = hp;
        ac = 10 + findMod(dex);
    }


    //getters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getTemphp() {
        return temphp;
    }

    public int getAc() {
        return ac;
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


    //public int[] getStats() { return stats; }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setTemphp(int temphp) {
        this.temphp = temphp;
    }

    public void setStr(int str) {
        this.str = str;
        //this.stats[0] = str;
    }

    public void setDex(int dex) {
        this.dex = dex;
        //this.stats[1] = dex;
    }

    public void setCon(int con) {
        this.con = con;
       // this.stats[2] = con;
    }

    public void setIntel(int intel) {
        this.intel = intel;
        //this.stats[3] = intel;
    }

    public void setWis(int wis) {
        this.wis = wis;
        //this.stats[4] = wis;
    }

    public void setCha(int cha) {
        this.cha = cha;
       // this.stats[5] = cha;
    }

    public void setStats(int[] stats) {
        this.str = stats[0];
        this.dex = stats[1];
        this.con = stats[2];
        this.intel = stats[3];
        this.wis = stats[4];
        this.cha = stats[5];
        /*
        for(int i = 0; i < stats.length; i++)
        {
            this.stats[i] = stats[i];
        }

         */
        updateACHP();
    }

    public static boolean checkName(String name)
    {
        boolean output = false;
        if(name.matches("[A-Za-z]+"))
        {
            output = true;
        }
       return output;
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
    public static int findMod(int stat)
    {
        int mod = 0;
        if(stat < 10)
        {
            for(int i = 10; i > stat; i-=2)
            {
                mod--;
            }
        }else
        {
            for(int i = 10; i < stat; i+=2)
            {
                mod++;
            }
        }
        return mod;
    }

    public static boolean checkEmail(String email)
    {
        return email.matches("[A-Za-z0-9]+@[a-z]+\\.(com|net|edu)");
    }
    public String toString()
    {
        String output = "";
        output += "-----" + name + "----";
        output += "\nlevel: " + level;
        output += "\nhp: " + temphp + "/" + hp + " | ac: " + ac;
        output += "\nstr: " + str + "\tdex: " + dex + "\tcon: " + con;
        output += "\nint: " + intel + "\twis: " + wis + "\tcha: " + cha;
        return output;
    }
}
