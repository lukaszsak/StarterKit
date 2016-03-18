package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {
	private TypeOfPokerHand typeOfPokerHand;
	private List<Pattern> orderedPattern;
	private List<Card> orderedCards;

	PokerHand() {
	}

	public PokerHand(String s) {
		orderedCards = stringToCardList(s);
		initializePokerHand();
	}

	public PokerHand(List<Card> cards) {
		orderedCards = cards;
		initializePokerHand();
	}

	private void initializePokerHand() {
		setPattern();
		sortOrderedCardsAccordingToOrderedPattern();
		setTypeOfPokerHand();
	}

	private void setPattern() {
		mergeEqualValues();
		Collections.sort(orderedPattern);
	}

	private void mergeEqualValues() {
		List<Pattern> patterns = new ArrayList<Pattern>();
		boolean inserted;
		for (int i = 0; i < orderedCards.size(); i++) {
			inserted = false;
			for (Pattern pattern : patterns) {
				if (pattern.getCardValue() == orderedCards.get(i).getCardValue()) {
					pattern.setRepetition(pattern.getRepetition() + 1);
					inserted = true;
					break;
				}
			}
			if (!inserted) {
				Pattern pattern = new Pattern();
				pattern.setCardValue(orderedCards.get(i).getCardValue());
				pattern.setRepetition(1);
				patterns.add(pattern);
			}
		}
		orderedPattern = patterns;
	}

	private void sortOrderedCardsAccordingToOrderedPattern() {
		List<Card> cards = new ArrayList<Card>();
		for (Pattern pattern : orderedPattern) {
			int repetition = pattern.repetition;
			for (int i = 0; i < orderedCards.size(); i++) {
				if (pattern.getCardValue() == orderedCards.get(i).getCardValue()) {
					Card card = new Card(orderedCards.get(i));
					cards.add(card);
					repetition--;
				}
				if (repetition == 0) {
					break;
				}
			}
		}
		orderedCards = cards;
	}

	private void setTypeOfPokerHand() {
		if (orderedPattern.size() == 5) {
			setTypeOfPokerHandAllCardValuesDifferent();
		} else {
			setTypeOfPokerHandSomeCardValuesRepeated();
		}
	}

	private void setTypeOfPokerHandAllCardValuesDifferent() {
		if (isFlush()) {
			if (isStraight()) {
				typeOfPokerHand = TypeOfPokerHand.STRAIGHT_FLUSH;
			} else {
				typeOfPokerHand = TypeOfPokerHand.FLUSH;
			}
		} else if (isStraight()) {
			typeOfPokerHand = TypeOfPokerHand.STRAIGHT;
		} else {
			typeOfPokerHand = TypeOfPokerHand.HIGH_CARD;
		}
	}

	private boolean isFlush() {
		boolean flush = true;
		CardSuit cardSuit = orderedCards.get(0).getCardSuit();
		for (Card card : orderedCards) {
			if (card.getCardSuit() != cardSuit) {
				flush = false;
				break;
			}
		}
		return flush;
	}

	private boolean isStraight() {
		boolean straight = false;
		if (isOrderedCardsOrderedFrom(0)) {
			straight = true;
		} else if (isOrderedCardsOrderedFrom(1) && orderedCards.get(0).getCardValue() == CardValue.ACE
				&& orderedCards.get(4).getCardValue() == CardValue.TWO) {
			straight = true;
		}
		return straight;
	}

	private boolean isOrderedCardsOrderedFrom(int i) {
		boolean tailOrdered = true;
		while (i < orderedCards.size() - 1) {
			if (orderedCards.get(i).getCardValue().ordinal() != orderedCards.get(i + 1).getCardValue().ordinal() + 1) {
				tailOrdered = false;
				break;
			}
			i++;
		}
		return tailOrdered;
	}

	private void setTypeOfPokerHandSomeCardValuesRepeated() {
		if (orderedPattern.get(0).getRepetition() == 2) {
			if (orderedPattern.get(1).getRepetition() == 2) {
				typeOfPokerHand = TypeOfPokerHand.TWO_PAIR;
			} else {
				typeOfPokerHand = TypeOfPokerHand.ONE_PAIR;
			}
		} else if (orderedPattern.get(0).getRepetition() == 3) {
			if (orderedPattern.get(1).getRepetition() == 2) {
				typeOfPokerHand = TypeOfPokerHand.FULL_HOUSE;
			} else {
				typeOfPokerHand = TypeOfPokerHand.THREE_OF_A_KIND;
			}
		} else {
			typeOfPokerHand = TypeOfPokerHand.FOUR_OF_A_KIND;
		}
	}

	public TypeOfPokerHand getTypeOfPokerHand() {
		return typeOfPokerHand;
	}

	public int compareTo(PokerHand pokerHand) {
		int difference = this.typeOfPokerHand.ordinal() - pokerHand.typeOfPokerHand.ordinal();

		if (difference == 0) {
			for (int i = 0; i < orderedPattern.size(); i++) {
				int thisCardValue = this.orderedPattern.get(i).cardValue.ordinal();
				int pokerHandCardValue = pokerHand.orderedPattern.get(i).cardValue.ordinal();
				difference = thisCardValue - pokerHandCardValue;
				if (difference != 0) {
					break;
				}
			}
		}
		return difference;
	}

	public boolean isBigger(PokerHand pokerHand) {
		int difference = this.compareTo(pokerHand);
		if (difference > 0) {
			return true;
		}
		return false;
	}

	private List<Card> stringToCardList(String s) {
		List<Card> cards = new ArrayList<Card>();
		String[] cardsStrings = s.split(" ");
		for (String strng : cardsStrings) {
			Card card = new Card(strng);
			cards.add(card);
		}

		return cards;
	}
}