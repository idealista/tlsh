package com.idealista.tlsh.digests;

class ModularDifferenceCalculator {

	public static int calculate(int initialPosition, int finalPosition, int circularQueueSize) {
		int internalDistance = Math.abs(finalPosition - initialPosition);
		int externalDistance = circularQueueSize - internalDistance;

		return Math.min(internalDistance, externalDistance);
	}

}