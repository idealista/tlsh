package com.idealista.tlsh.buckets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SlideWindow {

	private static final int CHECKSUM_LENGTH = 1;

	private static final int SLIDING_WINDOW_SIZE = 5;

	private final int[] storage;

	private int counter;

	public SlideWindow() {
		this.storage = new int[SLIDING_WINDOW_SIZE];
		this.counter = 0;
	}

	public void put(int value) {
		storage[getPivot()] = value & 0xff;
		counter++;
	}

	public List<Integer> getTripletHashes(int fromStartWindow) {
		if (!isComplete())	return new ArrayList<Integer>();

		int startWindow = fromStartWindow;
		int j2 = (startWindow + 1) % SLIDING_WINDOW_SIZE;
		int j3 = (startWindow + 2) % SLIDING_WINDOW_SIZE;
		int j4 = (startWindow + 3) % SLIDING_WINDOW_SIZE;
		int endWindow = (startWindow + 4) % SLIDING_WINDOW_SIZE;

		return Arrays.asList(getHash(storage[startWindow], storage[endWindow], storage[j4], 2),
								getHash(storage[startWindow], storage[endWindow], storage[j3], 3),
								getHash(storage[startWindow], storage[j4], storage[j3], 5),
								getHash(storage[startWindow], storage[j4], storage[j2], 7),
								getHash(storage[startWindow], storage[endWindow], storage[j2], 11),
								getHash(storage[startWindow], storage[j3], storage[j2], 13));
	}

	public int[] getChecksum(int fromStartWindow, int[] lastChecksum) {
		if (!isComplete()) return null;

		int endWindow = (fromStartWindow + 4) % SLIDING_WINDOW_SIZE;
		int[] checksum = new int[CHECKSUM_LENGTH];

		for (int i = 0; i < CHECKSUM_LENGTH; i++) {
			int c1 = getValue(fromStartWindow);
			int c2 = getValue(endWindow);
			int c3 = 0;
			int salt = 0;

			if (lastChecksum != null) {
				c3 = lastChecksum[i];
			}

			if (i != 0) {
				salt = checksum[i - 1];
			}

			checksum[i] = getHash(c1, c2, c3, salt);
		}

		return checksum;
	}

	public int getPivot() {
		return counter % SLIDING_WINDOW_SIZE;
	}

	private boolean isComplete() {
		return counter >= SLIDING_WINDOW_SIZE;
	}

	private int getHash(int c1, int c2, int c3, int salt) {
		return new Triplet(c1, c2, c3, salt).getHash();
	}

	private int getValue(int index) {
		return storage[index];
	}
}