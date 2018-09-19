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
    public void givenValidHandString_whenPassedToConstructor_HandObjectIsNotNull() {
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
    public void givenHandStringNull_whenPassedToConstructor_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand(null);
        });
    }

    @Test
    public void givenCorrectLengthHandStringContainsInvalidCard_whenPassedToHandConstructor_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Hand("2H 8R 2S AS AD");
        });
    }

    @Test
    public void givenValidHandWithHighCardAceSpades_whenGetHighCardCalled_returnAceSpades() {
        SUT = new Hand("2H 2D 5C 2S AS");
        assertEquals(SUT.getHighCard(), new Card("AS"));
    }

    @Test
    public void givenHandWithTwoEqualValueCards_whenHasTwoOfKindCalled_thenTrue(){
        SUT = new Hand("4C 5H 2D AS AH");
        assertTrue(SUT.isTwoOfKind());
    }

    @Test
    public void givenHandWithUniqueValueCards_whenIsTwoOfKindCalled_thenFalse(){
        SUT = new Hand("AS KH 4C 5H 2D");
        assertFalse(SUT.isTwoOfKind());
    }
}