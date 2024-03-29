package main;

public class Card implements Comparable<Card> {
    private CardValue value;
    private Suit suit;

    /*
        String will be in format: AH
        A = Ace = Value
        H = Suit = Hearts
     */
    public Card(String card) {
        this.value = CardValue.lookupByAbbreviation(card.substring(0, card.length() - 1));
        this.suit = Suit.valueOf(card.substring(card.length() - 1));

        if (this.value == null || this.suit == null)
            throw new IllegalArgumentException("Invalid card string: " + card);
    }

    public CardValue getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    // Comparing only uses value
    @Override
    public int compareTo(Card that) {
        return this.value.compareTo(that.value);
    }

    // Determining if two cards are identical requires suit and value
    @Override
    public boolean equals(Object that) {
        // Only cards are compared to cards
        if (that instanceof Card) {
            Card thatCard = (Card) that;
            return this.value.equals(thatCard.value) && this.suit.equals(thatCard.suit);
        } else {
            return false;
        }
    }


    public enum CardValue {
        Two("2"),
        Three("3"),
        Four("4"),
        Five("5"),
        Six("6"),
        Seven("7"),
        Eight("8"),
        Nine("9"),
        Ten("10"),
        Jack("J"),
        Queen("Q"),
        King("K"),
        Ace("A");

        // Store the above "A", "8", "K", ext.
        private String abbreviation;

        // Constructor for the above
        CardValue(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        // Given "K" returns CardValue.King
        public static CardValue lookupByAbbreviation(String abbreviation) {

            // Loop through values, return as soon as match is found
            for (CardValue cv : CardValue.values()) {
                if (cv.abbreviation.equals(abbreviation))
                    return cv;
            }

            // Abbreviation not found
            return null;
        }
    }

    public enum Suit {
        H("Hearts"),
        D("Diamonds"),
        C("Clubs"),
        S("Spades");

        private String name;

        Suit(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return this.value.name() + " of " + this.suit.toString();
    }
}
