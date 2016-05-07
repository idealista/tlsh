package com.idealista.tlsh.digests;

import java.util.Arrays;

public class Checksum {

	private static final int DIFF_CHECKSUM_VALUE = 1;

	private static final int EQUALS_CHECKSUM_VALUE = 0;

	private int[] value;

	public Checksum(int[] checksum) {
		this.value = checksum;
	}

	public int calculateDifference(Checksum other) {
		if (!Arrays.equals(value, other.getValue())) return DIFF_CHECKSUM_VALUE;
		return EQUALS_CHECKSUM_VALUE;
	}

	public int[] getValue() {
		return value;
	}

}