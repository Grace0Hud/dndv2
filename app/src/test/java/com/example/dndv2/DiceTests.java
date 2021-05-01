package com.example.dndv2;

import org.junit.Test;
import static org.junit.Assert.*;

public class DiceTests
{
    @Test
    public void diceConst()
    {
        Dice die = new Dice(20);
        String expected = "Die with 20 sides";
        assertEquals(expected, die.toString());
    }
    @Test
    public void defaultConst()
    {
        Dice die = new Dice();
        String expected = "Die with 2 sides";
        assertEquals(expected, die.toString());
    }
    @Test
    public void sidesGetNSet()
    {
        Dice die = new Dice(20);
        die.setSides(2);
        int expected = 2;
        assertEquals(expected, die.getSides());
    }
    @Test
    public void getRoll()
    {
        Dice die = new Dice();
        int expected = die.roll(1);
        assertEquals(expected, die.getRoll());
    }
    @Test
    public void rollRand()
    {
        Dice die = new Dice();
        die.roll(1);
        assertTrue(die.getRoll() > 0 && die.getRoll() < 3);
    }
    @Test
    public void checkSides()
    {
        Dice die = new Dice(20);
        die.setSides(0);
        int expected = 2;
        assertEquals(expected, die.getSides());
    }
    @Test
    public void setRollUnder1()
    {
        Dice die = new Dice(20);
        die.setRoll(0);
        int expected = 20;
        assertEquals(expected, die.getRoll());
    }
    @Test
    public void setRollOverSides()
    {
        Dice die = new Dice(20);
        die.setRoll(21);
        int expected = 20;
        assertEquals(expected, die.getRoll());
    }
}
