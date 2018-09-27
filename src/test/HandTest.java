package test;

import main.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Hand;

import java.util.HashMap;

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
        assertTrue(SUT.isPair());
    }

    @Test
    public void givenHandWithUniqueValueCards_whenIsTwoOfKindCalled_thenFalse() {
        SUT = new Hand("AS KH 4C 5H 2D");
        assertFalse(SUT.isPair());
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

    @Test
    void givenHandWithAllHearts_whenIsFlushCalled_thenTrue() {
        SUT = new Hand("AH 2H KH 4H JH");
        assertTrue(SUT.isFlush());
    }

    @Test
    void givenHandWithAllHeartsWithOneClub_whenIsFlushCalled_thenFalse() {
        SUT = new Hand("AH 2H KH 4H JC");
        assertFalse(SUT.isFlush());
    }

    @Test
    void givenHandWithHighCardOnly_whenGetScoreCalled_thenReturnsHighCard() {
        SUT = new Hand("2D 3H 5D 6S KH");
        assertEquals(Hand.Score.HighCard, SUT.getScore());
    }

    @Test
    void givenHandWithPairOnly_whenGetScoreCalled_thenReturnsPair() {
        SUT = new Hand("2D 2H 5D 6S KH");
        assertEquals(Hand.Score.Pair, SUT.getScore());
    }

    @Test
    void givenHandWithTwoPairsOnly_whenGetScoreCalled_thenReturnsTwoPair() {
        SUT = new Hand("2D 2H 5D 5S KH");
        assertEquals(Hand.Score.TwoPair, SUT.getScore());
    }

    @Test
    void givenHandWithThreeOfKind_whenGetScoreCalled_thenReturnsThreeOfKind() {
        SUT = new Hand("2D 2H 2C 5S KH");
        assertEquals(Hand.Score.ThreeOfKind, SUT.getScore());
    }

    @Test
    void givenHandWithStraight_whenGetScoreCalled_thenReturnsStraight() {
        SUT = new Hand("AS 2D 3C 4H 5D");
        assertEquals(Hand.Score.Straight, SUT.getScore());
    }

    @Test
    void givenHandWithFlush_whenGetScoreCalled_thenReturnsFlush() {
        SUT = new Hand("JH KH QH 5H 2H");
        assertEquals(Hand.Score.Flush, SUT.getScore());
    }

    @Test
    void givenHandWithFullHouse_whenGetCardMap_thenReturnsCorrectTwoKeyMap() {
        SUT = new Hand("2C 2D KH KS KC");

        HashMap<Card.CardValue, Integer> cardMap = SUT.getCardMap();

        assertEquals(2, cardMap.get(Card.CardValue.TWO).intValue());
        assertEquals(3, cardMap.get(Card.CardValue.KING).intValue());
    }

}