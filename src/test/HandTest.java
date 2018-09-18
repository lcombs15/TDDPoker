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
    public void givenHandStringOfIncorrectLength_whenPassedToConstructor_throwIllegalArgumentException(){
        // Hand string too long
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand("2H 4S AS AD AC AH");
        });

        // Hand string too short
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand("2H 4S");
        });
    }

    @Test
    public void whenHandStringNull_givenHandConstructor_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand(null);
        });
    }

    @Test
    public void whenCorrectLengthHandStringContainsInvalidCard_givenHandConstructor_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand("2H 8R 2S AS AD");
        });
    }

    @Test
    public void test_high_card_in_hand() {
        SUT = new Hand("2H 2D 5C 2S AS");
        assertEquals(SUT.getHighCard(), new Card("AS"));
    }
}