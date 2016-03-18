package main;

enum sortOfThrow {
	POINT, SPARE, STRIKE
}

class OneThrow {
	private sortOfThrow sort;
	private int pinsKnockedDown;

	public sortOfThrow getSort() {
		return sort;
	}

	public void setSort(sortOfThrow sort) {
		this.sort = sort;
	}

	public int getPinsKnockedDown() {
		return pinsKnockedDown;
	}

	public void setPinsKnockedDown(int pinsKnockedDown) {
		this.pinsKnockedDown = pinsKnockedDown;
	}
}