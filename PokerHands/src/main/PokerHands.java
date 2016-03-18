package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PokerHands {
	public static final String PLIK = "C:/Users/LUKSAK/Desktop/PokerHands/src/main/poker.txt";
	private List<String> pokerHandsList = new ArrayList<String>();
	private PokerHand pokerHandPlayer1;
	private PokerHand pokerHandPlayer2;

	private void readPokerHands(String s) {
		String[] tokeny = s.split(" ");
		List<Card> cardList = new ArrayList<Card>();
		String[] s1 = new String[5];
		String[] s2 = new String[5];
		for (int i = 0; i < 5; i++) {
			s1[i] = tokeny[i];
			s2[i] = tokeny[i + 5];
		}
		for (int i = 0; i < 5; i++) {
			Card card = new Card();
			card.setCardValue(tokeny[i].charAt(0));
			card.setCardSuit(tokeny[i].charAt(1));
			cardList.add(card);
		}

		pokerHandPlayer1 = new PokerHand(cardList);

		cardList.clear();
		for (int i = 5; i < 10; i++) {
			Card card = new Card();
			card.setCardValue(tokeny[i].charAt(0));
			card.setCardSuit(tokeny[i].charAt(1));
			cardList.add(card);
		}
		pokerHandPlayer2 = new PokerHand(cardList);
	}

	public List<Card> stringToCardList(String s) {
		String[] tokeny = s.split(" ");
		List<Card> cardList = new ArrayList<Card>();

		for (int i = 0; i < 5; i++) {
			Card card = new Card();
			card.setCardValue(tokeny[i].charAt(0));
			card.setCardSuit(tokeny[i].charAt(1));
			cardList.add(card);
		}
		return cardList;
	}

	public void wczytajZPliku() {
		File file = new File(PLIK);
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {

				String s = sc.nextLine();
				pokerHandsList.add(s);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private int countResult() {
		int player1Wins = 0;
		int gameWinner;
		for (String s : pokerHandsList) {
			readPokerHands(s);
			gameWinner = pokerHandPlayer1.compareTo(pokerHandPlayer2);
			if (gameWinner > 0) {
				player1Wins++;
			}
		}
		return player1Wins;
	}

	public static void main(String... args) {
		PokerHands ph = new PokerHands();
		ph.wczytajZPliku();
		int player1Wins = ph.countResult();
		System.out.println("player 1 wins " + player1Wins + " times");
	}
}