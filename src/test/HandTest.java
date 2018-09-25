package test;

import jdk.nashorn.internal.ir.annotations.Ignore;
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

    /**
     * <Invalid input testing>
     */
    @Test
    public void givenValidHandString_whenPassedToConstructor_HandObjectIsNotNull() {
        SUT = new Hand("2H 4S 4C 2D 4H");
        assertTrue(SUT != null);
    }

    @Test
    public void givenHandStringOfIncorrectLength_whenPassedToConstructor_throwIllegalArgumentException() {
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

    /**
     * </Invalid input testing>
     */

    @Test
    public void givenValidHandWithHighCardAceSpades_whenGetHighCardCalled_returnAceSpades() {
        SUT = new Hand("2H 2D 5C 2S AS");
        assertEquals(new Card("AS"), SUT.getHighCard());
    }

    @Test
    public void givenHandWithTwoEqualValueCards_whenHasTwoOfKindCalled_thenTrue() {
        SUT = new Hand("4C 5H 2D AS AH");
        assertTrue(SUT.isTwoOfKind());
    }

    @Test
    public void givenHandWithUniqueValueCards_whenIsTwoOfKindCalled_thenFalse() {
        SUT = new Hand("AS KH 4C 5H 2D");
        assertFalse(SUT.isTwoOfKind());
    }

    @Test
    void givenStraightHandWithoutAce_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("QD KC JH 10D 9S");
        assertTrue(SUT.isStraight());
    }

    @Test
    void givenStraightHandWithAceHigh_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("QD KC AS JH 10D");
        assertTrue(SUT.isStraight());
    }

    @Test
    void givenStraightHandWithAceLow_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("4H 3H 2D AS 5C");
        assertTrue(SUT.isStraight());
    }

    @Test
    void givenStraightHandWithAceHighAndLow_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("QD KD AS 2C 3H");
        assertFalse(SUT.isStraight());
    }

    @Test
    void givenHandWithAllTenAndJack_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("10H 10C 10D JS JC");
        assertFalse(SUT.isStraight());
    }

    @Test
    void givenHandWithAllAcesAndATwo_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("AS 2H AC AD AH");
        assertFalse(SUT.isStraight());
    }

    @Test
    void givenHandWithATwoAThreeAndThreeAces_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("AS 2H AC AD 3H");
        assertFalse(SUT.isStraight());
    }
}