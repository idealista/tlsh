package com.idealista.tlsh.digests;

public class LValue {

	private static final int RANGE_LVALUE = 256;

	private int value;

	public LValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public int calculateDifference(LValue other) {
		int ldiff = ModularDifferenceCalculator.calculate(value, other.getValue(), RANGE_LVALUE);

		if (ldiff == 0) {
			return 0;
		}

		if (ldiff == 1) {
			return 1;
		}

		return ldiff * 12;
	}

}