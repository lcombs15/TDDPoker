package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    Card cards[] = new Card[5];

    public Hand(String hand) {
        if(hand == null)
            throw new IllegalArgumentException("Hand string cannot be null");

        String cardStrings[] = hand.split(" ");

        if(cardStrings.length != 5)
            throw new IllegalArgumentException("Hand string must contain 5 cards. Given: " + hand);

        for (int i = 0; i < cardStrings.length; i++) {
            this.cards[i] = new Card(cardStrings[i]);
        }
    }

    public Card getHighCard() {
        Arrays.sort(cards);
        return cards[cards.length-1];
    }

    public boolean isTwoOfKind() {
        // Populate list of all card values
        ArrayList<Card.CardValue> cardValues = new ArrayList();

        for(Card cardInHand : cards)
            cardValues.add(cardInHand.getValue());

        // Loop through first n - 1 cards (card n has an obvious lastIndexOf)
        int i = 0;
        boolean isTwoOfKind = false;
        while(i < cardValues.size() -1 && !isTwoOfKind){
            // If there exists another occurrence of this card value in hand
            if(cardValues.lastIndexOf(cardValues.get(i)) != i) {
                isTwoOfKind = true;
            }

            i++;
        }

        return isTwoOfKind;
    }

    public boolean isStraight() {
        if (isTwoOfKind())
            return false;

        // Put cards in order
        Arrays.sort(this.cards);

        // Possible card values, in order
        Card.CardValue cardValues[] = Card.CardValue.values();

        // Find first cards values index
        int i = 0;
        int firstCardValueIndex = -1;
        while(firstCardValueIndex < 0){
            if (this.cards[0].getValue().equals(cardValues[i]))
                firstCardValueIndex = i;
            i++;
        }

        // Verify that hand card values are consecutive
        boolean isStraight = false;
        for (int j = 1; j < this.cards.length; j++){
            if (this.cards[j].getValue().equals(Card.CardValue.ACE))
                continue;
            isStraight = this.cards[j].getValue().equals(cardValues[j + firstCardValueIndex]);
        }

        return isStraight;
    }
}