package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerGame implements BowlingGameResultCalculator {
	private final static int MAX_Frame = 10;

	private List<Frame> Frames;

	public PlayerGame() {
		Frames = new ArrayList<Frame>();
	}

	public void roll(int numberOfPins) {
		if (isFirstThrowInFrame()) {
			Frame r;
			if (Frames.size() == MAX_Frame - 1) {
				r = new LastFrame();
			} else {
				r = new NormalFrame();
			}
			Frames.add(r);
		}
		Frames.get(Frames.size() - 1).roll(numberOfPins);
	}

	public int score() {
		List<OneThrow> throwList;

		throwList = getThrowList();
		markLastFrameThrowsAsPoints(throwList);

		int score = 0;
		for (int i = 0; i < throwList.size(); i++) {
			score += throwList.get(i).getPinsKnockedDown();
			if (throwList.get(i).getSort() != sortOfThrow.POINT) { // SPARE ||
																	// STIKE
				if (i < throwList.size() - 1) {
					score += throwList.get(i + 1).getPinsKnockedDown();
				}
			}
			if (throwList.get(i).getSort() == sortOfThrow.STRIKE) {
				if (i < throwList.size() - 2) {
					score += throwList.get(i + 2).getPinsKnockedDown();
				}
			}
		}
		return score;
	}

	private List<OneThrow> getThrowList() {
		List<OneThrow> throwList = new ArrayList<OneThrow>();
		for (Frame r : Frames) {
			for (OneThrow t : r.FrameThrows) {
				throwList.add(t);
			}
		}
		return throwList;
	}

	private void markLastFrameThrowsAsPoints(List<OneThrow> throwList) {
		if (Frames.size() == MAX_Frame) {
			int numberOfLastFrameThrowns = Frames.get(MAX_Frame - 1).FrameThrows.size();
			for (int i = throwList.size() - numberOfLastFrameThrowns; i < throwList.size(); i++) {
				throwList.get(i).setSort(sortOfThrow.POINT);
			}
		}
	}

	public boolean isFinished() {
		return isPlayerGameFinished();
	}

	boolean isPlayerGameFinished() {
		boolean playerGameFinihed = false;
		if (Frames.size() == MAX_Frame) {
			if (Frames.get(MAX_Frame - 1).isFrameFinished())
				playerGameFinihed = true;
		}
		return playerGameFinihed;
	}

	private boolean isFirstThrowInFrame() {
		boolean firstThrowInFrame;
		if (Frames.isEmpty()) {
			firstThrowInFrame = true;
		} else {
			firstThrowInFrame = Frames.get(Frames.size() - 1).isFrameFinished();
		}
		return firstThrowInFrame;
	}

	public void readPlayerGameFromInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Podaj punkty gracza oddzielone przecinkami: ");
		String s = sc.nextLine();
		String[] throwsyString = s.split(",");
		int[] throwsy = new int[throwsyString.length];
		for (int i = 0; i < throwsy.length; i++) {
			throwsy[i] = Integer.parseInt(throwsyString[i]);
		}
		for (int x : throwsy) {
			roll(x);
		}
		sc.close();
	}

	private void showScore() {
		System.out.println("Player has " + this.score() + " points");
	}

	public static void main(String[] args) {
		PlayerGame playerGame = new PlayerGame();
		playerGame.readPlayerGameFromInput();
		playerGame.showScore();
	}

}