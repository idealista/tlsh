package com.idealista.tlsh.buckets;

import com.idealista.tlsh.digests.Digest;

public class ProcessedBuckets {

	public static final int CODE_SIZE = 32;

	private static final int MINIMUM_HASH_INPUT_LENGTH = 512;

	private int[] checksum;

	private int[] bucketArray;

	private int processedDataLength;

	private Quartiles quartiles;

	public ProcessedBuckets(int[] checksum, int[] bucketArray, int processedDataLength, Quartiles quartiles) {
		this.checksum = checksum;
		this.bucketArray = bucketArray;
		this.processedDataLength = processedDataLength;
		this.quartiles = quartiles;
	}

	public boolean isProcessedDataTooSimple() {
		return !hasMinimumAmountOfDataProcessed() || !hasMinimumNonZeroBuckets();
	}

	public Digest buildDigest() {
		int[] bodyData = calculateBody();

		return new DigestBuilder()
				.withChecksum(checksum)
				.withLength(processedDataLength)
				.withQuartiles(quartiles)
				.withBody(bodyData).build();
	}

	private int[] calculateBody() {
		int[] body = new int[CODE_SIZE];

		for (int i = 0; i < CODE_SIZE; i++) {
			int h = 0;

			for (int j = 0; j < 4; j++) {
				int k1 = bucketArray[4 * i + j];

				if (quartiles.getThird() < k1) {
					h += 3 << (j * 2);
				} else if (quartiles.getSecond() < k1) {
					h += 2 << (j * 2);
				} else if (quartiles.getFirst() < k1) {
					h += 1 << (j * 2);
				}
			}

			body[i] = h;
		}

		return body;
	}

	private boolean hasMinimumAmountOfDataProcessed() {
		return processedDataLength >= MINIMUM_HASH_INPUT_LENGTH;
	}

	private boolean hasMinimumNonZeroBuckets() {
		int nonZeroBuckets = 0;

		for (int index = 0; index < (CODE_SIZE * 4); index++) {
			if (isPositiveBucket(index)) {
				nonZeroBuckets++;
			}
		}

		return nonZeroBuckets > (2 * CODE_SIZE);
	}

	private boolean isPositiveBucket(int index) {
		return bucketArray[index] > 0;
	}
}