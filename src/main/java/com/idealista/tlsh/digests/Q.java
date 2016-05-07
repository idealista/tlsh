package com.idealista.tlsh.digests;

public class Q {

	private static final int RANGE_QRATIO = 16;

	private int value;

	public Q(int value) {
		this.value = value;
	}

	public Q(int QLo, int QHi) {
		this(calculateValue(QLo, QHi));
	}

	public int calculateDifference(Q other) {
		int diff = 0;

		int q1diff = ModularDifferenceCalculator.calculate(getQLo(), other.getQLo(), RANGE_QRATIO);

		if (q1diff <= 1) {
			diff += q1diff;
		} else {
			diff += (q1diff - 1) * 12;
		}

		int q2diff = ModularDifferenceCalculator.calculate(getQHi(), other.getQHi(), RANGE_QRATIO);

		if (q2diff <= 1) {
			diff += q2diff;
		} else {
			diff += (q2diff - 1) * 12;
		}

		return diff;
	}

	public int getValue() {
		return value;
	}

	private int getQLo() {
		return value & 0x0F;
	}

	private int getQHi() {
		return (value & 0xF0) >> 4;
	}

	private static int calculateValue(int QLo, int QHi) {
		return (((0 & 0xF0) | (QLo & 0x0F)) & 0x0F) | ((QHi & 0x0F) << 4);
	}
}