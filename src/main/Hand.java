package main;

import main.Card.CardValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Hand implements Comparable<Hand> {
    private Card cards[] = new Card[5];
    private HashMap<CardValue, Integer> cardMap = new HashMap<CardValue, Integer>();

    public Hand(String hand) {
        if (hand == null)
            throw new IllegalArgumentException("Hand string cannot be null");

        String cardStrings[] = hand.split(" ");

        if (cardStrings.length != 5)
            throw new IllegalArgumentException("Hand string must contain 5 cards. Given: " + hand);

        for (int i = 0; i < cardStrings.length; i++) {
            Card cardToAdd = new Card(cardStrings[i]);

            // Add card to hand
            this.cards[i] = cardToAdd;

            // Add card to map
            CardValue currentValue = cardToAdd.getValue();

            Integer currentMapValue = cardMap.get(currentValue);

            if (currentMapValue == null) {
                cardMap.put(currentValue, 1);
            } else {
                cardMap.replace(currentValue, currentMapValue + 1);
            }
        }
    }

    public Card getHighCard() {
        Arrays.sort(cards);
        return cards[cards.length - 1];
    }

    public boolean isPair() {
        return numPairs() > 0;
    }

    public boolean isTwoPair() {
        return numPairs() > 1;
    }

    private int numPairs() {
        int numPairs = 0;

        for (Integer count : cardMap.values()) {
            if (count.intValue() >= 2)
                numPairs++;
        }

        return numPairs;
    }

    public boolean isStraight() {
        // Any hand with two of a kind cannot be a straight
        if (isPair())
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
            if (!currentValue.equals(CardValue.Ace))
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

    public HashMap<CardValue, Integer> getCardMap() {
        return this.cardMap;
    }

    @Override
    public int compareTo(Hand that) {
        int result = this.getScore().compareTo(that.getScore());

        if (result > 0) {
            return 1;
        } else if (result < 0) {
            return -1;
        } else {
            return this.getHighCard().compareTo(that.getHighCard());
        }
    }

    public Hand winningHandAgainst(Hand opponent) {
        int result = this.compareTo(opponent);
        // Tie: winning hand doesn't exist
        if (result == 0) {
            return null;
        }

        return result == 1 ? this : opponent;
    }

    public enum Score {
        HighCard("High Card"),
        Pair("Pair"),
        TwoPair("Two Pair"),
        ThreeOfKind("Three Of A Kind"),
        Straight("Straight"),
        Flush("Flush"),
        FullHouse("Full House"),
        FourOfKind("Four Of A Kind"),
        StraightFlush("Straight Flush");

        private String name;

        Score(String name) {
            this.name = name;
        }

        @Override
        public String toString(){
            return name;
        }
    }

    public Score getScore() {
        if (isFlush() && isStraight()) {
            return Score.StraightFlush;
        } else if (isFourOfKind()) {
            return Score.FourOfKind;
        } else if (isFullHouse()) {
            return Score.FullHouse;
        } else if (isFlush()) {
            return Score.Flush;
        } else if (isStraight()) {
            return Score.Straight;
        } else if (isThreeOfKind()) {
            return Score.ThreeOfKind;
        } else if (isTwoPair()) {
            return Score.TwoPair;
        } else if (isPair()) {
            return Score.Pair;
        } else {
            return Score.HighCard;
        }
    }

    private boolean isFullHouse() {
        Collection<Integer> vals = cardMap.values();
        return vals.contains(2) && vals.contains(3);
    }

    private boolean isThreeOfKind() {
        return cardMap.values().contains(3);
    }

    private boolean isFourOfKind() {
        return cardMap.values().contains(4);
    }
}