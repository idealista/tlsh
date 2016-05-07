package com.idealista.tlsh;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.idealista.tlsh.exceptions.InsufficientComplexityException;

public class TLSHTest {
	
	@Test(expected = NullPointerException.class)
	public void testWhenProcessNullInputString_ShouldThrowNPE() {
		String data = null;
		TLSH tlsh = new TLSH(data);

		tlsh.hash();
	}

	@Test(expected=InsufficientComplexityException.class)
	public void testWhenProcessEmptyInputString_ShouldThrowInsufficientComplexityException() {
		String data = "";
		
		TLSH tlsh = new TLSH(data);
		tlsh.hash();
	}

	@Test(expected=InsufficientComplexityException.class)
	public void testWhenProcessInputWithoutMinimumLengthString_ShouldThrowInsufficientComplexityException() {
		// minimum input are 512 bytes
		String data = "Two of the most famous products of Berkeley are LSD and Unix. I don’t think that this is a coincidence";
		
		TLSH tlsh = new TLSH(data);
		tlsh.hash();
	}

	@Test(expected=InsufficientComplexityException.class)
	public void testWhenProcessInputWithoutMinimumComplexityString_ShouldThrowInsufficientComplexityException() {
		String data = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		TLSH tlsh = new TLSH(data);
		tlsh.hash();
	}

	@Test
	public void testProcessValidInput() {
		String data = "The best documentation is the UNIX source. After all, this is what the "
				+ "system uses for documentation when it decides what to do next! The "
				+ "manuals paraphrase the source code, often having been written at "
				+ "different times and by different people than who wrote the code. "
				+ "Think of them as guidelines. Sometimes they are more like wishes... "
				+ "Nonetheless, it is all too common to turn to the source and find "
				+ "options and behaviors that are not documented in the manual. Sometimes "
				+ "you find options described in the manual that are unimplemented " 
				+ "and ignored by the source.";
		TLSH tlsh = new TLSH(data);

		assertEquals("6FF02BEF718027B0160B4391212923ED7F1A463D563B1549B86CF62973B197AD2731F8", tlsh.hash());
		
	}
}