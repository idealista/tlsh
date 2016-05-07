package com.idealista.tlsh.digests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.idealista.tlsh.digests.Digest;
import com.idealista.tlsh.digests.DigestBuilder;

public class DigestTest {

	@Test
	public void testOriginalExamples() throws Exception {
		Digest digest1 = new DigestBuilder().withHash("301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C").build();
		Digest digest2 = new DigestBuilder().withHash("09F05A198CC69A5A4F0F9380A9EE93F2B927CF42089EA74276DC5F0BB2D34E68114448").build();
		
		assertEquals(0, digest1.calculateDifference(digest1, true));
		assertEquals(121, digest1.calculateDifference(digest2, true));
		assertEquals(97, digest1.calculateDifference(digest2, false));
	}
	
	@Test
	public void testWhenCalculateDifferenceUsingSameDigest_ShouldReturnZero() throws Exception {
		Digest digest1 = new DigestBuilder().withHash("301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C").build();
		assertEquals(0, digest1.calculateDifference(digest1, true));
	}
	
	@Test
	public void testWhenCalculateDifferenceUsingDigestsWithSameHashes_ShouldReturnZero() throws Exception {
		Digest digest1 = new DigestBuilder().withHash("301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C").build();
		Digest digest2 = new DigestBuilder().withHash("301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C").build();

		assertEquals(0, digest1.calculateDifference(digest2, true));
	}
	
	@Test
	public void testWhenCalculateDifferenceUsingHashesWithSameDistanceByte_ShouldReturnSameDiff() throws Exception {
		Digest digest1 = new DigestBuilder().withHash("CBF0C0EFB28027B0260F4391212923ECBF1D42396637114DB85CF62436B187AD2731F8").build();
		Digest digest2 = new DigestBuilder().withHash("BEF08BEFB28027B0260B4391212922E8BF1D42396637114DB85CF62436B187AD2721B8").build();
		
		assertTrue(digest1.calculateDifference(digest2, true) == digest1.calculateDifference(digest2, false));
	}
	
	@Test
	public void testWhenCalculateDifferenceUsingHashesWithDifferentDistanceByte_ShouldReturnDifferentDiff() throws Exception {
		Digest digest1 = new DigestBuilder().withHash("301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C").build();
		Digest digest2 = new DigestBuilder().withHash("09F05A198CC69A5A4F0F9380A9EE93F2B927CF42089EA74276DC5F0BB2D34E68114448").build();
		
		assertFalse(digest1.calculateDifference(digest2, true) == digest1.calculateDifference(digest2, false));
	}
}