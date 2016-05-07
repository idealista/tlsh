package com.idealista.tlsh.buckets;

class Triplet {

	private final int c1;

	private final int c2;

	private final int c3;

	private final int salt;

	public Triplet(int c1, int c2, int c3, int salt) {
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.salt = salt;
	}

	public int getHash() {
		return PearsonHash.compute(new int[] { salt, c1, c2, c3 });
	}
}