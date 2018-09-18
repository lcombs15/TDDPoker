package main;

import java.util.Arrays;

public class Hand {
    Card cards[] = new Card[5];

    public Hand(String hand) {
        String cardStrings[] = hand.split(" ");
        for (int i = 0; i < cardStrings.length; i++) {
            this.cards[i] = new Card(cardStrings[i]);
        }
    }

    public Card getHighCard() {
        Arrays.sort(cards);
        return cards[0];
    }
}