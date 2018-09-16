package main;

public class Card {
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
    }

    public CardValue getValue() {
        return this.value;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public enum CardValue {
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5"),
        SIX("6"),
        SEVEN("7"),
        EIGHT("8"),
        NINE("9"),
        TEN("10"),
        JACK("J"),
        QUEEN("Q"),
        KING("K"),
        ACE("A");

        // Store the above "A", "8", "K", ext.
        private String abbreviation;

        // Constructor for the above
        CardValue(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        // Given "K" returns CardValue.KING
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
    }
}
