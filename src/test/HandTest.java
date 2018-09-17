package test;

import main.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Hand;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    Hand SUT;

    @BeforeEach
    public void setup() {
        SUT = null;
    }

    @Test
    public void test_hand_creation_from_string() {
        SUT = new Hand("2H 4S 4C 2D 4H");
        assertTrue(SUT != null);
    }

    @Test
    public void test_high_card_in_hand() {
        SUT = new Hand("2H 2D 5C 2S AS");
        assertEquals(SUT.getHighCard(), new Card("AS"));
    }
}