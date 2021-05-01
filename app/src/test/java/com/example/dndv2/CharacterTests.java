package com.example.dndv2;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTests
{
    @Test
    public void rollStat()
    {
        int stat = Character.rollStat();
        assertTrue(stat < 20);
    }
}
