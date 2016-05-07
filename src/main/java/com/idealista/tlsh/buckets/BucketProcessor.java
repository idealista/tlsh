package com.idealista.tlsh.buckets;

public class BucketProcessor {

	private static final int ARRAY_BUCKET_SIZE = 256;

	public ProcessedBuckets process(String data) {
		int[] checksum = null;
		int[] bucketArray = new int[ARRAY_BUCKET_SIZE];

		SlideWindow slideWindow = new SlideWindow();
		int length = data.length();

		for (int i = 0; i < length; i++) {
			int startWindow = slideWindow.getPivot();
			slideWindow.put(data.charAt(i));

			checksum = slideWindow.getChecksum(startWindow, checksum);

			for (Integer tripletHash : slideWindow.getTripletHashes(startWindow)) {
				bucketArray[tripletHash]++;
			}
		}

		return buildProcessedBuckets(length, bucketArray, checksum);
	}

	private ProcessedBuckets buildProcessedBuckets(int dataLength, int[] bucketArray, int[] checksum) {
		Quartiles quartiles = new Quartiles(bucketArray);
		return new ProcessedBuckets(checksum, bucketArray, dataLength, quartiles);
	}
}