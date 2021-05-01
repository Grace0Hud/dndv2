package com.example.dndv2;

import java.util.Random;
public class Dice
{
    private int sides;
    private Random generator = new Random();
    private int roll = 0;

    //constructors
    public Dice()
    {
        sides = 2;
    }//end of 0-arg
    public Dice(int newSides)
    {
        sides = checkSides(newSides);
    }//end of single -arg
    //rolls a random number within the max and min
    public int roll(int num)
    {
        roll = 0;
        int tempRoll;
        for(int i = 0; i < num; i++)
        {
            tempRoll = generator.nextInt(sides) + 1;
            if(sides == 20 && tempRoll == sides)
            {
                tempRoll = tempRoll * 2;
            }
            else if(sides == 20 && tempRoll == 1)
            {
                tempRoll = 0;
            }
            roll += tempRoll;
        }
        return roll;
    }//end of roll
    //getters
    //does not reroll, simply returns the current value of the roll.
    public int getRoll()
    {
        return roll;
    }
    public void setRoll(int newRoll)
    {
        this.roll = checkRoll(newRoll);
    }
    public int getSides()
    {
        return sides;
    }//end of get sides getter
    //setters
    public void setSides(int newSides)
    {
        sides = checkSides(newSides);
    }//end of setSides setter
    //brain methods
    private int checkRoll(int newRoll)
    {
        int tempRoll = newRoll;
        if(tempRoll > sides)
        {
            return sides;
        }
        else if(tempRoll < 2)
        {
            return 1;
        }
        return tempRoll;
    }
    private int checkSides(int newSides)
    {
        int tempSides = newSides;
        if(tempSides < 1)
        {
            tempSides = 2;
        }//end of if

        return tempSides;
    }//end of check sides
    //to string
    public String toString()
    {
        String output;
        output = "Die with " + sides + " sides";
        return output;
    }//end of toString
}