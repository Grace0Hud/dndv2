package com.example.dndv2;

import org.junit.Test;
import static org.junit.Assert.*;

public class StoryMessageTests
{
    @Test
    public void constr()
    {
        StoryMessage test = new StoryMessage("Tester", "Test!");
        String expected = "Tester: Test!";
        assertEquals(expected, test.toString());
    }

    @Test
    public void speakerGetNSet()
    {
        StoryMessage test = new StoryMessage("Tester", "Test!");
        test.setSpeaker("Yes");
        String expected = "Yes";
        assertEquals(expected, test.getSpeaker());
    }

    @Test
    public void messageGetNSet()
    {
        StoryMessage test = new StoryMessage("Tester", "Test!");
        test.setMessage("Yes!");
        String expected = "Yes!";
        assertEquals(expected, test.getMessage());
    }
}
