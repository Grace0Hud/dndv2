package com.example.dndv2;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTests
{
    int[] stats = {3,12,13,15,6,8};
    @Test
    public void rollStat()
    {
        int stat = Character.rollStat();
        assertTrue(stat < 20 && stat > 2);
    }
    @Test
    public void defaultConstr()
    {
        Character character = new Character();
        String output = "";
        output += "-----Sam Smorkle----";
        output += "\nlevel: " + 1;
        output += "\nstr: " + character.getStr() + "\tdex: " + character.getDex() + "\tcon: " + character.getCon();
        output += "\nint: " + character.getIntel() + "\twis: " + character.getWis() + "\tcha: " + character.getCha();
        assertEquals(output, character.toString());
    }
    @Test
    public void nameConstr()
    {
        Character character = new Character("Cedar");
        String output = "";
        output += "-----Cedar----";
        output += "\nlevel: " + 1;
        output += "\nstr: " + character.getStr() + "\tdex: " + character.getDex() + "\tcon: " + character.getCon();
        output += "\nint: " + character.getIntel() + "\twis: " + character.getWis() + "\tcha: " + character.getCha();
        assertEquals(output, character.toString());
    }
    @Test
    public void nameLevelContr()
    {
        Character character = new Character("Cedar", 5);
        String output = "";
        output += "-----Cedar----";
        output += "\nlevel: " + 5;
        output += "\nstr: " + character.getStr() + "\tdex: " + character.getDex() + "\tcon: " + character.getCon();
        output += "\nint: " + character.getIntel() + "\twis: " + character.getWis() + "\tcha: " + character.getCha();
        assertEquals(output, character.toString());
    }
    @Test
    public void nameLevelStatsContr()
    {
        Character character = new Character("Cedar", 5, stats);
        String output = "";
        output += "-----Cedar----";
        output += "\nlevel: " + 5;
        output += "\nstr: " + stats[0] + "\tdex: " + stats[1] + "\tcon: " + stats[2];
        output += "\nint: " + stats[3] + "\twis: " + stats[4] + "\tcha: " + stats[5];
        assertEquals(output, character.toString());
    }
}
