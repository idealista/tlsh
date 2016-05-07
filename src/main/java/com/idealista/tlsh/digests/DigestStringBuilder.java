package com.idealista.tlsh.digests;

class DigestStringBuilder {

	private StringBuilder value;

	public DigestStringBuilder() {
		this.value = new StringBuilder();
	}

	public DigestStringBuilder append(Checksum checksum) {
		int swappedChecksum[] = new int[checksum.getValue().length];

		for (int k = 0; k < swappedChecksum.length; k++) {
			swappedChecksum[k] = swap(checksum.getValue()[k]);
		}

		value.append(toHex(swappedChecksum));
		return this;
	}

	public DigestStringBuilder append(LValue lValue) {
		value.append(toHex(swap(lValue.getValue())));
		return this;
	}

	public DigestStringBuilder append(Q q) {
		value.append(toHex(swap(q.getValue())));
		return this;
	}

	public DigestStringBuilder append(Body body) {
		int[] swappedBody = new int[body.getBody().length];

		for (int i = 0; i < swappedBody.length; i++) {
			swappedBody[i] = body.getBody()[swappedBody.length - 1 - i];
		}

		value.append(toHex(swappedBody));
		return this;
	}

	public String build() {
		return value.toString();
	}

	private String toHex(int value) {
		return String.format("%02X", (0xFF & value));
	}

	private String toHex(int[] values) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			result.append(toHex(values[i]));
		}

		return result.toString().toUpperCase();
	}

	private int swap(int data) {
		return ByteSwapper.swap(data);
	}
}
