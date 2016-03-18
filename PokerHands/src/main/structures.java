package main;

enum CardValue {
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING,
	ACE
}

enum CardSuit {
	HEARTS,
	DIAMONDS,
	CLUBS,
	SPADES
}

enum TypeOfPokerHand {
	HIGH_CARD,
	ONE_PAIR,
	TWO_PAIR,
	THREE_OF_A_KIND,
	STRAIGHT,
	FLUSH,
	FULL_HOUSE,
	FOUR_OF_A_KIND,
	STRAIGHT_FLUSH
}

class Card implements Comparable<Card> {
	private CardValue cardValue;
	private CardSuit cardSuit;

	Card() {
	}

	Card(Card c) {
		setCardValue(c.getCardValue());
		setCardSuit(c.getCardSuit());
	}

	public CardValue getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValue cardValue) {
		this.cardValue = cardValue;
	}

	public CardSuit getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(CardSuit cardSuit) {
		this.cardSuit = cardSuit;
	}

	public void setCardValue(char c) {
		switch (c) {
		case '2':
			cardValue = CardValue.TWO;
			break;
		case '3':
			cardValue = CardValue.THREE;
			break;
		case '4':
			cardValue = CardValue.FOUR;
			break;
		case '5':
			cardValue = CardValue.FIVE;
			break;
		case '6':
			cardValue = CardValue.SIX;
			break;
		case '7':
			cardValue = CardValue.SEVEN;
			break;
		case '8':
			cardValue = CardValue.EIGHT;
			break;
		case '9':
			cardValue = CardValue.NINE;
			break;
		case 'T':
			cardValue = CardValue.TEN;
			break;
		case 'J':
			cardValue = CardValue.JACK;
			break;
		case 'Q':
			cardValue = CardValue.QUEEN;
			break;
		case 'K':
			cardValue = CardValue.KING;
			break;
		case 'A':
			cardValue = CardValue.ACE;
			break;
		}
	}

	public void setCardSuit(char c) {
		switch (c) {
		case 'H':
			cardSuit = CardSuit.HEARTS;
			break;
		case 'D':
			cardSuit = CardSuit.DIAMONDS;
			break;
		case 'C':
			cardSuit = CardSuit.CLUBS;
			break;
		case 'S':
			cardSuit = CardSuit.SPADES;
			break;
		}
	}

	/*
	 * compares in reverse order : return >0 when this < p This enables sorting
	 * in descending order
	 */
	public int compareTo(Card card) {

		return card.cardValue.ordinal() - this.cardValue.ordinal();
	}

	Card(String s){
		setCardValue(s.charAt(0));
		setCardSuit(s.charAt(1));
	}
}

class Pattern implements Comparable<Pattern> {
	CardValue cardValue;
	int repetition;

	public CardValue getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValue cardValue) {
		this.cardValue = cardValue;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	/*
	 * compares in reverse order : return >0 when this < p This enables sorting
	 * in descending order
	 */
	public int compareTo(Pattern p) {
		int difference = p.repetition - this.repetition;
		if (difference == 0) {
			difference = p.cardValue.ordinal() - this.cardValue.ordinal();
		}
		return difference;
	}
}