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
    public void givenStraightHandWithoutAce_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("QD KC JH 10D 9S");
        assertTrue(SUT.isStraight());
    }

    @Test
    public void givenStraightHandWithAceHigh_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("QD KC AS JH 10D");
        assertTrue(SUT.isStraight());
    }

    @Test
    public void givenStraightHandWithAceLow_whenIsStraightCalled_thenTrue() {
        SUT = new Hand("4H 3H 2D AS 5C");
        assertTrue(SUT.isStraight());
    }

    @Test
    public void givenStraightHandWithAceHighAndLow_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("QD KD AS 2C 3H");
        assertFalse(SUT.isStraight());
    }

    @Test
    public void givenHandWithAllTenAndJack_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("10H 10C 10D JS JC");
        assertFalse(SUT.isStraight());
    }

    @Test
    public void givenHandWithAllAcesAndATwo_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("AS 2H AC AD AH");
        assertFalse(SUT.isStraight());
    }

    @Test
    public void givenHandWithATwoAThreeAndThreeAces_whenIsStraightCalled_thenFalse() {
        SUT = new Hand("AS 2H AC AD 3H");
        assertFalse(SUT.isStraight());
    }

    @Test
    public void givenHandWithAllHearts_whenIsFlushCalled_thenTrue() {
        SUT = new Hand("AH 2H KH 4H JH");
        assertTrue(SUT.isFlush());
    }

    @Test
    public void givenHandWithAllHeartsWithOneClub_whenIsFlushCalled_thenFalse() {
        SUT = new Hand("AH 2H KH 4H JC");
        assertFalse(SUT.isFlush());
    }

    @Test
    public void givenHandWithHighCardOnly_whenGetScoreCalled_thenReturnsHighCard() {
        SUT = new Hand("2D 3H 5D 6S KH");
        assertEquals(Hand.Score.HighCard, SUT.getScore());
    }

    @Test
    public void givenHandWithPairOnly_whenGetScoreCalled_thenReturnsPair() {
        SUT = new Hand("2D 2H 5D 6S KH");
        assertEquals(Hand.Score.Pair, SUT.getScore());
    }

    @Test
    public void givenHandWithTwoPairsOnly_whenGetScoreCalled_thenReturnsTwoPair() {
        SUT = new Hand("2D 2H 5D 5S KH");
        assertEquals(Hand.Score.TwoPair, SUT.getScore());
    }

    @Test
    public void givenHandWithThreeOfKind_whenGetScoreCalled_thenReturnsThreeOfKind() {
        SUT = new Hand("2D 2H 2C 5S KH");
        assertEquals(Hand.Score.ThreeOfKind, SUT.getScore());
    }

    @Test
    public void givenHandWithStraight_whenGetScoreCalled_thenReturnsStraight() {
        SUT = new Hand("AS 2D 3C 4H 5D");
        assertEquals(Hand.Score.Straight, SUT.getScore());
    }

    @Test
    public void givenHandWithFlush_whenGetScoreCalled_thenReturnsFlush() {
        SUT = new Hand("JH KH QH 5H 2H");
        assertEquals(Hand.Score.Flush, SUT.getScore());
    }

    @Test
    public void givenHandWithFullHouse_whenGetScoreCalled_thenReturnsFullHouse() {
        SUT = new Hand("2C 2D KH KS KC");
        assertEquals(Hand.Score.FullHouse, SUT.getScore());
    }

    @Test
    public void givenHandWithFourOfKind_whenGetScoreCalled_thenReturnsFourOfKind() {
        SUT = new Hand("2C 3D 2H 2S 2D");
        assertEquals(Hand.Score.FourOfKind, SUT.getScore());
    }

    @Test
    public void givenHandWithStraightFlush_whenGetScoreCalled_thenReturnsStraightFlush(){
        SUT = new Hand("AS KS QS JS 10S");
        assertEquals(Hand.Score.StraightFlush, SUT.getScore());
    }

    @Test
    public void givenFlush_whenComparedToPair_thenOne(){
        SUT = new Hand("5H 2H 7H 9H JH");
        Hand that = new Hand("4H 4D 3C 7S 8C");
        assertEquals(1, SUT.compareTo(that));
    }

    @Test
    public void givenX_whenComparedToX_thenZero(){
        SUT = new Hand("5H 2H 7H 9H JH");
        assertEquals(0, SUT.compareTo(SUT));
    }

    @Test
    public void givenTwoStraightsXY_whenXHighCompareToYLow_thenOne(){
        SUT = new Hand("AS KH QD JS 10H");
        Hand other = new Hand("KH QD JS 10H 9S");
        assertEquals(1, SUT.compareTo(other));
    }
}