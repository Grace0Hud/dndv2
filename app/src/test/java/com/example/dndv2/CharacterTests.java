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
    //constructors
    @Test
    public void defaultConstr()
    {
        Character character = new Character();
        String output = "";
        output += "-----Sam Smorkle----";
        output += "\nlevel: " + 1;
        output += "\nhp: " + character.getTemphp() + "/" + character.getHp() + " | ac: " + character.getAc();
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
        output += "\nhp: " + character.getTemphp() + "/" + character.getHp() + " | ac: " + character.getAc();
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
        output += "\nhp: " + character.getTemphp() + "/" + character.getHp() + " | ac: " + character.getAc();
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
        output += "\nhp: " + character.getTemphp() + "/" + character.getHp() + " | ac: " + character.getAc();
        output += "\nstr: " + stats[0] + "\tdex: " + stats[1] + "\tcon: " + stats[2];
        output += "\nint: " + stats[3] + "\twis: " + stats[4] + "\tcha: " + stats[5];
        assertEquals(output, character.toString());
    }
    //getters and setters
    @Test
    public void nameGetNSet()
    {
        Character character = new Character();
        character.setName("Cedar");
        String expected = "Cedar";
        assertEquals(expected, character.getName());
    }
    @Test
    public void levelGetNSet()
    {
        Character character = new Character();
        character.setLevel(10);
        int expected = 10;
        assertEquals(expected, character.getLevel());
    }
    @Test
    public void hpGetNSet()
    {
        Character character = new Character();
        character.setHp(43);
        int expected = 43;
        assertEquals(expected, character.getHp());
    }
    @Test
    public void tempHpGetNSet()
    {
        Character character = new Character();
        character.setTemphp(43);
        int expected = 43;
        assertEquals(expected, character.getTemphp());
    }
    @Test
    public void acGetNSet()
    {
        Character character = new Character();
        character.setAc(12);
        int expected = 12;
        assertEquals(expected, character.getAc());
    }
    @Test
    public void strGetNSet()
    {
        Character character = new Character();
        character.setStr(10);
        int expected = 10;
        assertEquals(expected, character.getStr());
    }
    @Test
    public void dexGetNSet()
    {
        Character character = new Character();
        character.setDex(10);
        int expected = 10;
        assertEquals(expected, character.getDex());
    }
    @Test
    public void conGetNSet()
    {
        Character character = new Character();
        character.setCon(10);
        int expected = 10;
        assertEquals(expected, character.getCon());
    }
    @Test
    public void intelGetNSet()
    {
        Character character = new Character();
        character.setIntel(10);
        int expected = 10;
        assertEquals(expected, character.getIntel());
    }
    @Test
    public void wisGetNSet()
    {
        Character character = new Character();
        character.setWis(10);
        int expected = 10;
        assertEquals(expected, character.getWis());
    }
    @Test
    public void charGetNSet()
    {
        Character character = new Character();
        character.setCha(10);
        int expected = 10;
        assertEquals(expected, character.getCha());
    }
//    @Test
//    public void statsStrUpdate()
//    {
//        Character character = new Character();
//        character.setStr(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[0]);
//    }
//    @Test
//    public void statsDexUpdate()
//    {
//        Character character = new Character();
//        character.setDex(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[1]);
//    }
//    @Test
//    public void statsConUpdate()
//    {
//        Character character = new Character();
//        character.setCon(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[2]);
//    }
//    @Test
//    public void statsIntUpdate()
//    {
//        Character character = new Character();
//        character.setIntel(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[3]);
//    }
//    @Test
//    public void statsWisUpdate()
//    {
//        Character character = new Character();
//        character.setWis(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[4]);
//    }
//    @Test
//    public void statsChaUpdate()
//    {
//        Character character = new Character();
//        character.setCha(10);
//        int expected = 10;
//        assertEquals(expected, character.getStats()[5]);
//    }
//    @Test
//    public void statsUpdate()
//    {
//        Character character = new Character();
//        character.setStats(stats);
//        assertArrayEquals(stats, character.getStats());
//    }
    @Test
    public void statsUpdateIndivdualStats()
    {
        Character character = new Character();
        character.setStats(stats);
        int[] actualStats = {character.getStr(), character.getDex(), character.getCon(),
                character.getIntel(), character.getWis(), character.getCha()};
        assertArrayEquals(stats, actualStats);
    }
    @Test
    public void findModLessThan10()
    {
        int expected = -1;
        assertEquals(expected, Character.findMod(9));
    }
    @Test
    public void findModMoreThan10()
    {
        int expected = 2;
        assertEquals(expected, Character.findMod(14));
    }
    @Test
    public void findModEquals10()
    {
        int expected = 0;
        assertEquals(expected, Character.findMod(10));
    }
    @Test
    public void nameRegexFalse()
    {
        assertFalse(Character.checkName("56dsaf"));
    }
    @Test
    public void nameRegexTrue()
    {
        assertTrue(Character.checkName("name"));
    }
    @Test
    public void emailRegexFalse()
    {
        assertFalse(Character.checkEmail("name"));
    }
    @Test
    public void emailRegexTrue()
    {
        assertTrue(Character.checkEmail("name@email.com"));
    }

}
