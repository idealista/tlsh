package com.idealista.tlsh.digests;

public class Digest {

	private Checksum checksum;

	private LValue lValue;

	private Q q;

	private Body body;

	public Digest(Checksum checksum, LValue lValue, Q q, Body body) {
		this.lValue = lValue;
		this.checksum = checksum;
		this.q = q;
		this.body = body;
	}

	public int calculateDifference(Digest other, boolean lengthDiff) {
		int difference = 0;

		if (lengthDiff) {
			difference += lValue.calculateDifference(other.lValue);
		}

		difference += q.calculateDifference(other.q);
		difference += checksum.calculateDifference(other.checksum);
		difference += body.calculateDiffence(other.body);

		return difference;
	}

	public String toString() {
		return new DigestStringBuilder().append(checksum).append(lValue).append(q).append(body).build();
	}
}