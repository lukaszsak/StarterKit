package test;

import main.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokerHandsTest {

	@Test
	public void highCardVsHighCardTest() {
		String highCard1 = "2C AS 5H JH 6D";
		String highCard2 = "TC 9C 4D AD 7D";
		PokerHand ph1 = new PokerHand(highCard1);
		PokerHand ph2 = new PokerHand(highCard2);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void highCardVsOnePairTest() {
		String onePair = "2C 7S 2H KH 9D";
		String highCard = "QC 3C TD 7H 2D";
		PokerHand ph1 = new PokerHand(onePair);
		PokerHand ph2 = new PokerHand(highCard);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void onePairVsTwoPairTest() {
		String twoPair = "2C 7S 2H KH 7D";
		String onePair = "QC 4C AD 7H AC";
		PokerHand ph1 = new PokerHand(twoPair);
		PokerHand ph2 = new PokerHand(onePair);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void TwoPairsTest() {
		String twoPairs1 = "2C 7S 2H KH 7D";
		String twoPairs2 = "QC 7C 2D 7H 2C";
		PokerHand ph1 = new PokerHand(twoPairs1);
		PokerHand ph2 = new PokerHand(twoPairs2);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void TwoPairsVSThreeOfKindTest() {
		String twoPairs = "AC 7S AH KH 7D";
		String threeOfKind = "2C AD 2H 7H 2D";
		PokerHand ph1 = new PokerHand(twoPairs);
		PokerHand ph2 = new PokerHand(threeOfKind);
		assertEquals(false, ph1.isBigger(ph2));
	}

	@Test
	public void ThreeOfKindVSStraihhtTest() {
		String straight = "9H 7S 5H 8H 6D";
		String threeOfKind = "2C AD 2H 7H 2D";
		PokerHand ph1 = new PokerHand(straight);
		PokerHand ph2 = new PokerHand(threeOfKind);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void straightVSFlushTest() {
		String straight = "QD JS AH KH TD";
		String flush = "QC 7C 2C 5C TC";
		PokerHand ph1 = new PokerHand(straight);
		PokerHand ph2 = new PokerHand(flush);
		assertEquals(false, ph1.isBigger(ph2));
	}

	@Test
	public void flushVSFullHouseTest() {
		String flush = "QC 7C 2C 5C TC";
		String fullHouse = "2S 7S 2H 7D 2D";
		PokerHand ph1 = new PokerHand(fullHouse);
		PokerHand ph2 = new PokerHand(flush);
		assertEquals(true, ph1.isBigger(ph2));
	}

	@Test
	public void fourOfKindVSFullHouseTest() {
		String fourOfKind = "QC 7C QH QS QD";
		String fullHouse = "2S 7S 2H 7D 2D";
		PokerHand ph1 = new PokerHand(fullHouse);
		PokerHand ph2 = new PokerHand(fourOfKind);
		assertEquals(false, ph1.isBigger(ph2));
	}

	@Test
	public void straightFlushVSFourOfKindTest() {
		String straightFlush = "QC 8C JC 9C TC";
		String fourOfKind = "AS AC AH 7D AD";
		PokerHand ph1 = new PokerHand(straightFlush);
		PokerHand ph2 = new PokerHand(fourOfKind);
		assertEquals(true, ph1.isBigger(ph2));
	}
}
