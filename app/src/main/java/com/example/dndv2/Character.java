package com.example.dndv2;

import android.util.Log;

import java.util.ArrayList;

public class Character
{
    static Dice d6 = new Dice(6);
    private String name;
    private int level, str, dex, con, intel, wis, cha;

    public Character(String name, int level)
    {
        this.name = name;
        this.level = level;
        setUp();
    }

    private void setUp()
    {
        rollStat();
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
}
