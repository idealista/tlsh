package com.idealista.tlsh.digests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.idealista.tlsh.digests.ModularDifferenceCalculator;

// https://github.com/trendmicro/tlsh/blob/master/TLSH_Introduction.pdf (slide 13)
@RunWith(Parameterized.class)
public class ModularDifferenceCalculatorTest {
	
	@Parameter(value=0)
	public int initialPosition;
	
	@Parameter(value=1)
	public int finalPosition;
	
	@Parameter(value=2)
	public int circularQueueSize;
	
	@Parameter(value=3)
	public int expected;
	
	@Parameters(name="calculate({0},{1},{2}) Expecting {3}")
	public static List<Integer[]> data(){
		return Arrays.asList(new Integer[][]{
			{3, 4, 16, 1}, {3, 10, 16, 7}, {3, 15, 16, 4}
		});
	}
		
	@Test
	public void testModDifferenceCalculation() {
		assertEquals(expected, ModularDifferenceCalculator.calculate(initialPosition, finalPosition, circularQueueSize));
	}	
}