package main;

import main.Card.CardValue;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {
    Card cards[] = new Card[5];

    public Hand(String hand) {
        if (hand == null)
            throw new IllegalArgumentException("Hand string cannot be null");

        String cardStrings[] = hand.split(" ");

        if (cardStrings.length != 5)
            throw new IllegalArgumentException("Hand string must contain 5 cards. Given: " + hand);

        for (int i = 0; i < cardStrings.length; i++) {
            this.cards[i] = new Card(cardStrings[i]);
        }
    }

    public Card getHighCard() {
        Arrays.sort(cards);
        return cards[cards.length - 1];
    }

    public boolean isTwoOfKind() {
        // Populate list of all card values
        ArrayList<CardValue> cardValues = new ArrayList();

        for (Card cardInHand : cards)
            cardValues.add(cardInHand.getValue());

        // Loop through first n - 1 cards (card n has an obvious lastIndexOf)
        int i = 0;
        boolean isTwoOfKind = false;
        while (i < cardValues.size() - 1 && !isTwoOfKind) {
            // If there exists another occurrence of this card value in hand
            if (cardValues.lastIndexOf(cardValues.get(i)) != i) {
                isTwoOfKind = true;
            }

            i++;
        }

        return isTwoOfKind;
    }

    public boolean isStraight() {
        // Any hand with two of a kind cannot be a straight
        if (isTwoOfKind())
            return false;

        // Put hand in order
        Arrays.sort(cards);

        // Possible card values, in order by default
        ArrayList<CardValue> cardValues = new ArrayList<>(Arrays.asList(CardValue.values()));

        // Start index of possible straight
        int firstCardValueIndex = cardValues.indexOf(cards[0].getValue());

        // A straight can only exist if there are enough consecutive card values to include entire hand
        boolean isStraight = cardValues.size() - firstCardValueIndex >= cards.length;

        // Loop through cards in hand and check against cardValues order
        int handIndex = 0;
        while (isStraight && handIndex < cards.length) {
            CardValue currentValue = cards[handIndex].getValue();

            // Ignore Ace since it can be low or high
            if (!currentValue.equals(CardValue.ACE))
                isStraight = currentValue.equals(cardValues.get(handIndex + firstCardValueIndex));

            // Move to next card
            handIndex++;
        }

        return isStraight;
    }

    public boolean isFlush() {
        // Assume hand to be a flush
        boolean isFlush = true;

        // Save first suit in hand
        Card.Suit flushSuit = cards[0].getSuit();

        // Index 0 is given, Loop through hand up to n-1, verify that all suits are flushSuit
        int indexToCheck = 1;
        while (isFlush && indexToCheck < cards.length) {
            // Not a valid flush if different suit
            if (!cards[indexToCheck].getSuit().equals(flushSuit))
                isFlush = false;
            indexToCheck++;
        }

        return isFlush;
    }
}