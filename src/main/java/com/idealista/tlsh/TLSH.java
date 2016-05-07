package com.idealista.tlsh;

import com.idealista.tlsh.buckets.BucketProcessor;
import com.idealista.tlsh.buckets.ProcessedBuckets;
import com.idealista.tlsh.exceptions.InsufficientComplexityException;

public class TLSH {

	private String data;

	public TLSH(String data) {
		this.data = data;
	}

	public String hash() {
		ProcessedBuckets buckets = new BucketProcessor().process(data);

		if (buckets.isProcessedDataTooSimple()) {
			throw new InsufficientComplexityException("Input data hasn't enough complexity");
		}

		return buckets.buildDigest().toString();
	}
}