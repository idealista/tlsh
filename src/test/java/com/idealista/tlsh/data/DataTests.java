package com.idealista.tlsh.data;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.idealista.tlsh.TLSH;

@RunWith(Parameterized.class)
public class DataTests {
	
	@Parameter(value=0)
	public String expected;
	
	@Parameter(value=1)
	public String filePath;
	
	@Parameters(name="new TLSH(data from {1}) Expecting {0}")
	public static List<String[]> data(){
		return Arrays.asList(new String[][]{
			{"301124198C869A5A4F0F9380A9AE92F2B9278F42089EA34272885F0FB2D34E6911444C", "jon_oliver.txt"},
			{"09F05A198CC69A5A4F0F9380A9EE93F2B927CF42089EA74276DC5F0BB2D34E68114448", "lili_diao.txt"},
			{"E481852B33C423B545639375535F5AFBB74EC694421183F0A89EC43E735698C11B9AE8", "spanish_place_namesA.txt"}
		});
	}
	
	@Test
	public void testModDifferenceCalculation() throws Exception {
		assertEquals(expected, new TLSH(readFile(filePath)).hash());
	}
	
	private String readFile(String filePath) throws Exception {
		 return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(filePath), "UTF-8");
	}
}