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
}