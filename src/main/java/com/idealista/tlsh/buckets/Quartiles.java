package com.idealista.tlsh.buckets;

import java.util.Arrays;

public class Quartiles {

	private static final int ARRAY_SAMPLE_SIZE = 128;

	private static final int Q_RATIO_MODULE = 16;

	private int[] sampleArray;

	public Quartiles(int[] data) {
		if (data.length < ARRAY_SAMPLE_SIZE) throw new IllegalArgumentException("cannot calculate quartiles for arrays with less than " + ARRAY_SAMPLE_SIZE + " numbers");
		
		this.sampleArray = getSortedSampleArray(data);
	}

	public int getFirst() {
		return sampleArray[ARRAY_SAMPLE_SIZE / 4 - 1];
	}

	public int getSecond() {
		return sampleArray[ARRAY_SAMPLE_SIZE / 2 - 1];
	}

	public int getThird() {
		return sampleArray[ARRAY_SAMPLE_SIZE - (ARRAY_SAMPLE_SIZE / 4) - 1];
	}

	public int getQ1Ratio() {
		return (getFirst() * 100 / getThird()) % Q_RATIO_MODULE;
	}

	public int getQ2Ratio() {
		return (getSecond() * 100 / getThird()) % Q_RATIO_MODULE;
	}

	private int[] getSortedSampleArray(int[] data) {
		int[] sampleArray = new int[ARRAY_SAMPLE_SIZE];

		System.arraycopy(data, 0, sampleArray, 0, ARRAY_SAMPLE_SIZE);
		Arrays.sort(sampleArray);

		return sampleArray;
	}
}