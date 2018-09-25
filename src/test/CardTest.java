package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Card;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    Card SUD;

    @BeforeEach
    void setUp() {
        this.SUD = new Card("AH");
    }

    @Test
    void givenAce_whenGetValueCalled_returnsAce() {
        assertEquals(SUD.getValue(), Card.CardValue.ACE);
    }

    @Test
    void givenHeartsSuit_whenGetSuitCalled_returnsHearts() {
        assertEquals(SUD.getSuit(), Card.Suit.H);
    }

    @Test
    void givenInvalidCardStringFormat_whenPassedToConstructor_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("Ace of Spades");
        });
    }

    @Test
    void givenValidCardStringFormatWithInvalidCardValue_WhenPassedToConstructor_throwsIllegalArgumentException() {
        // The "One of hearts" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("1H");
        });
    }

    @Test
    void givenValidCardStringFormatWithInvalidSuit_WhenPassedToConstructor_throwsIllegalArgumentException() {
        // The "King of L's" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("KL");
        });
    }

    @Test
    void givenAce_whenComparedToKing_thenAceGreaterThanKing(){
        SUD = new Card("AS");
        assertEquals(SUD.compareTo(new Card("KD")), 1);
    }

}