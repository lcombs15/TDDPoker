package main;

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
        return cards[0];
    }
}