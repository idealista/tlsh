package com.idealista.tlsh.digests;

import com.idealista.tlsh.buckets.ProcessedBuckets;

public class DigestBuilder {

	private Checksum checksum;

	private LValue lValue;

	private Q q;

	private Body body;

	public DigestBuilder withHash(String hash) {
		int[] digestData = fromHex(hash);
		int i = 0;

		withChecksumData(digestData[i++]);
		withLValueData(digestData[i++]);
		withQData(digestData[i++]);
		withBodyData(getBodyData(digestData, i));

		return this;
	}

	public Digest build() {
		return new Digest(checksum, lValue, q, body);
	}

	private static int[] fromHex(String s) {
		int[] result = new int[s.length() / 2];

		for (int i = 0; i < s.length(); i += 2) {
			result[i / 2] = Integer.parseInt(s.substring(i, i + 2), 16);
		}

		return result;
	}

	private void withChecksumData(int data) {
		this.checksum = new Checksum(new int[] { swap(data) });
	}

	private void withLValueData(int data) {
		this.lValue = new LValue(swap(data));
	}

	private void withQData(int data) {
		this.q = new Q(swap(data));
	}

	private void withBodyData(int[] data) {
		int bodyData[] = new int[data.length];

		for (int j = 0; j < data.length; j++) {
			bodyData[j] = (data[data.length - 1 - j]);
		}

		this.body = new Body(bodyData);
	}

	private static int[] getBodyData(int[] digestData, int from) {
		int[] bodyData = new int[ProcessedBuckets.CODE_SIZE];
		System.arraycopy(digestData, from, bodyData, 0, bodyData.length);

		return bodyData;
	}

	private int swap(int data) {
		return ByteSwapper.swap(data);
	}
}