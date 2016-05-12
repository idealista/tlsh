package com.idealista.tlsh.buckets;

import com.idealista.tlsh.digests.Body;
import com.idealista.tlsh.digests.Checksum;
import com.idealista.tlsh.digests.Digest;
import com.idealista.tlsh.digests.LValue;
import com.idealista.tlsh.digests.Q;

public class DigestBuilder {

	private static final int MOD_VALUE = 256;

	private static final float LOG_1_1 = 0.095310180f;

	private static final float LOG_1_3 = 0.26236426f;

	private static final float LOG_1_5 = 0.4054651f;

	private Checksum checksum;

	private LValue lValue;

	private Q q;

	private Body body;

	public DigestBuilder withChecksum(int[] checksum) {
		this.checksum = new Checksum(checksum);
		return this;
	}

	public DigestBuilder withLength(int length) {
		this.lValue = new LValue(calculateLValue(length));
		return this;
	}

	public DigestBuilder withQuartiles(Quartiles quartiles) {
		this.q = new Q(quartiles.getQ1Ratio(), quartiles.getQ2Ratio());
		return this;
	}

	public DigestBuilder withBody(int[] bodyData) {
		this.body = new Body(bodyData);
		return this;
	}

	public Digest build() {
		return new Digest(checksum, lValue, q, body);
	}

	private static int calculateLValue(int length) {
		if (length <= Ranges.LOW.value) {
			return (int) Math.floor(Math.log(length) / LOG_1_5) % MOD_VALUE;
		}

		if (length <= Ranges.MID.value) {
			return (int) Math.floor(Math.log(length) / LOG_1_3 - 8.72777) % MOD_VALUE;
		}

		return (int) Math.floor(Math.log(length) / LOG_1_1 - 62.5472) % MOD_VALUE;
	}

	private enum Ranges {
		LOW(656), MID(3199);

		int value;

		private Ranges(int value) {
			this.value = value;
		}
	}
}
