package test;

import org.junit.jupiter.api.Test;
import main.Card;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test void test_card_value_from_string_constructor(){
        Card SUD = new Card("AH");
        assertEquals(SUD.getValue(), Card.CardValue.ACE);
    }

}