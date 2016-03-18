package main;

import java.util.ArrayList;
import java.util.List;

abstract class Frame {
	protected List<OneThrow> FrameThrows;

	Frame() {
		FrameThrows = new ArrayList<OneThrow>();
	}

	abstract boolean isFrameFinished();

	abstract void roll(int numberOfPinsKnockedDown);
}

class NormalFrame extends Frame {

	boolean isFrameFinished() {
		boolean FrameFinished = false;
		if (FrameThrows.size() == 2) {
			FrameFinished = true;
		}
		if (FrameThrows.size() == 1 && FrameThrows.get(0).getSort() == sortOfThrow.STRIKE) {
			FrameFinished = true;
		}
		return FrameFinished;
	}

	void roll(int numberOfPinsKnockedDown) {
		OneThrow aThrow = new OneThrow();
		aThrow.setPinsKnockedDown(numberOfPinsKnockedDown);

		if (areAllPinsKnockedDown(numberOfPinsKnockedDown)) {
			if (FrameThrows.size() == 0) { // first throw in Frame
				aThrow.setSort(sortOfThrow.STRIKE);
			} else { // second throw
				aThrow.setSort(sortOfThrow.SPARE);
			}
		} else {
			aThrow.setSort(sortOfThrow.POINT);
		}
		FrameThrows.add(aThrow);
	}

	private boolean areAllPinsKnockedDown(int numberOfPinsKnockedDown) {
		int pinsKnockedDown = 0;
		for (OneThrow t : FrameThrows) {
			pinsKnockedDown += t.getPinsKnockedDown();
		}
		pinsKnockedDown += numberOfPinsKnockedDown;

		boolean allPinsKnockedDown = false;
		if (pinsKnockedDown == 10) {
			allPinsKnockedDown = true;
		}
		return allPinsKnockedDown;
	}
}

class LastFrame extends Frame {

	boolean isFrameFinished() {
		boolean FrameFinished = false;
		if (FrameThrows.size() == 3) {
			FrameFinished = true;
		}
		if (FrameThrows.size() == 2) {
			if (!(FrameThrows.get(1).getSort() == sortOfThrow.SPARE
					|| FrameThrows.get(0).getSort() == sortOfThrow.STRIKE)) {
				FrameFinished = true;
			}
		}
		return FrameFinished;
	}

	void roll(int numberOfPinsKnockedDown) {
		OneThrow aThrow = new OneThrow();
		aThrow.setPinsKnockedDown(numberOfPinsKnockedDown);

		if (areAllPinsKnockedDown(numberOfPinsKnockedDown)) {
			if (numberOfPinsKnockedDown == 10) {
				aThrow.setSort(sortOfThrow.STRIKE);
			} else {
				aThrow.setSort(sortOfThrow.SPARE);
			}
		} else {
			aThrow.setSort(sortOfThrow.POINT);
		}
		FrameThrows.add(aThrow);
	}

	private boolean areAllPinsKnockedDown(int numberOfPinsKnockedDown) {
		int pinsKnockedDown = 0;
		for (OneThrow t : FrameThrows) {
			if (t.getSort() == sortOfThrow.POINT) {
				pinsKnockedDown += t.getPinsKnockedDown();
			} else {
				pinsKnockedDown = 0;
			}
		}
		pinsKnockedDown += numberOfPinsKnockedDown;

		boolean allPinsKnockedDown = false;
		if (pinsKnockedDown == 10) {
			allPinsKnockedDown = true;
		}
		return allPinsKnockedDown;
	}
}