package com.idealista.tlsh.digests;

public class Body {

	private int[] body;

	public Body(int[] bodyData) {
		this.body = bodyData;
	}

	public int[] getBody() {
		return body;
	}

	public int calculateDiffence(Body other) {
		return hDistance(other);
	}

	private int hDistance(Body other) {
		int diff = 0;

		for (int i = 0; i < body.length; i++) {
			diff += BitPairsTable.getValue(body[i], other.getBody()[i]);
		}

		return diff;
	}

}