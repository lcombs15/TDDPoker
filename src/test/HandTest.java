package test;

import org.junit.jupiter.api.Test;
import main.Hand;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    @Test
    public void test_hand_creation_from_string(){
        Hand SUT = new Hand("2H 4S 4C 2D 4H");
        assertTrue(SUT != null);
    }
}