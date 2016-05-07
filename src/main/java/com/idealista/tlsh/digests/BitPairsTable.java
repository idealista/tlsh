package com.idealista.tlsh.digests;

class BitPairsTable {

	private static final int BIT_PAIRS_DIFF_TABLE_SIZE = 256;

	private static final int[][] BIT_PAIRS_DIFF_TABLE = generateDefaultBitPairsDiffTable();

	private static int[][] generateDefaultBitPairsDiffTable() {
		int[][] result = new int[BIT_PAIRS_DIFF_TABLE_SIZE][BIT_PAIRS_DIFF_TABLE_SIZE];

		for (int i = 0; i < BIT_PAIRS_DIFF_TABLE_SIZE; i++) {
			for (int j = 0; j < BIT_PAIRS_DIFF_TABLE_SIZE; j++) {
				int x = i;
				int y = j;
				int diff = 0;

				for (int z = 0; z < 4; z++) {
					int d = Math.abs(x % 4 - y % 4);

					if (d == 3) {
						diff += d * 2;
					} else {
						diff += d;
					}

					if (z < 3) {
						x /= 4;
						y /= 4;
					}
				}

				result[i][j] = diff;
			}
		}

		return result;
	}

	public static int getValue(int row, int column) {
		return BIT_PAIRS_DIFF_TABLE[row][column];
	}
}