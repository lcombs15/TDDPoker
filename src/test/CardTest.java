package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Card;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    Card SUT;

    @BeforeEach
    void setUp() {
        this.SUT = new Card("AH");
    }

    @Test
    void givenAce_whenGetValueCalled_returnsAce() {
        assertEquals(SUT.getValue(), Card.CardValue.Ace);
    }

    @Test
    void givenHeartsSuit_whenGetSuitCalled_returnsHearts() {
        assertEquals(SUT.getSuit(), Card.Suit.H);
    }

    @Test
    void givenInvalidCardStringFormat_whenPassedToConstructor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Card("Ace of Spades");
        });
    }

    @Test
    void givenValidCardStringFormatWithInvalidCardValue_WhenPassedToConstructor_throwsIllegalArgumentException() {
        // The "One of hearts" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Card("1H");
        });
    }

    @Test
    void givenValidCardStringFormatWithInvalidSuit_WhenPassedToConstructor_throwsIllegalArgumentException() {
        // The "King of L's" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUT = new Card("KL");
        });
    }

    @Test
    void givenAce_whenComparedToKing_thenAceGreaterThanKing() {
        SUT = new Card("AS");
        assertEquals(SUT.compareTo(new Card("KD")), 1);
    }

}