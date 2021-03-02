import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;


public class AWSTest {

	private static final int FILLER_VALUE = Integer.MIN_VALUE;
	private int[] original={1, 2, 3};
	AWS originalAWS;
	
	@Before
	public void setUp() throws Exception {
		 originalAWS = new AWS(this.original);
	}

	@Test
	public void testGetValues() {
		int[] x = {1,2,3};
		
		AWS aws = new AWS(x);
		
		int[] result = aws.getValues();
		
		assertEquals(result[0], x[0]);
	}

	@Test
	public void testSetValues() {
		int[] x = {1,2,3};
		int[] y = {4,5,6};
		
		AWS aws = new AWS(x);
		
		aws.setValues(y);
		
		int[] result = aws.getValues();
		
		assertEquals(result[0], y[0]);
		assertEquals(result[2], y[2]);
		assertEquals(result.length, y.length);
	}

	@Test
	public void testToString() {
		int[] x = {1,2,3};
		
		AWS aws = new AWS(x);
		
		String expected = "AWS [values=[1, 2, 3]]";
		
		String result = aws.toString(); 
		
		assertEquals(expected, result);
	}

	@Test
	public void testAWS() {
		int[] expected = {1, 2, 3};
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		x[1] = 100;
		
		int[] actual = aws.getValues();
		assertEquals(actual[0], expected[0]);
		assertEquals(actual[1], expected[1]);
	}
	
	@Test
	public void testRemove() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int value = aws.remove(-1);
		int expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		 value = aws.remove(x.length + 10);
		expected = FILLER_VALUE;
		assertEquals(expected, value);
		
		value = aws.remove(0);
		assertEquals(x[0], value);
		
		int[] r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
		value = aws.remove(2);
		assertEquals(r[2], value);
		
		r = aws.getValues();
		value = r[r.length - 1];
		assertEquals(expected, value);
		
	}
	
	@Test
	public void testFillAndExpand() {
		int position = 1;
		int numberOfTimes = 2;
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
		int first = org[0];
 		
		int expected = originalAWS.getValues().length + numberOfTimes;
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		assertEquals(first, result[0]);
		 
		
	
	}
	@Test
	public void testFillAndExpandWithNegative() {
		int position = 1;
		int numberOfTimes = -2;
		
		int[] org = originalAWS.getValues();
		int expectedValue = org[position];
 		int first = org[0];
		int expected = originalAWS.getValues().length + Math.abs(numberOfTimes);
		originalAWS.fillAndExpand(position, numberOfTimes);
		int[] result = originalAWS.getValues();
		assertEquals(expected, result.length);
		
		int a = result[1];
		int b = result[2];
		int c = result[3];
		assertEquals(expectedValue, a);
		assertEquals(expectedValue, b);
		assertEquals(expectedValue, c);
		 
		assertEquals(first, result[0]);
	}
	
		
	@Test
	public void testRemoveBiggerThan() {
		int[] x = {1, 2, 3};
		AWS aws = new AWS(x);
		
		int threshold = 2;
		int[] org = aws.getValues();
		int expectedCount = 1;
		int expected = FILLER_VALUE;
		
		int resultCount = aws.removeBiggerThan(threshold);
		
		int[] resultValues = aws.getValues();
		
		assertEquals(expectedCount, resultCount);
		assertEquals(expected, resultValues[2]);
		assertEquals(org[1], resultValues[1]);
	}

}
