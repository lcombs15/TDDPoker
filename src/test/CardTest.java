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
    void test_card_value_from_string_constructor() {
        assertEquals(SUD.getValue(), Card.CardValue.ACE);
    }

    @Test
    void test_card_suit_from_string_constructor() {
        assertEquals(SUD.getSuit(), Card.Suit.H);
    }

    @Test
    void test_invalid_suit_and_value_card_string_to_constructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("INVALID");
        });
    }

    @Test
    void test_invalid_value_card_string_to_constructor() {
        // The "One of hearts" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("1H");
        });
    }

    @Test
    void test_invalid_suit_card_string_to_constructor() {
        // The "King of L's" doesn't exist
        assertThrows(IllegalArgumentException.class, () -> {
            SUD = new Card("KL");
        });
    }
}