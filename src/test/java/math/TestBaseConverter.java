package annas.test.math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import annas.math.BaseConverter;

public class TestBaseConverter {

	@Test
	public final void testBaseConverter(){
	int n = 47;
	String s = "101111";
		assertTrue(BaseConverter.toBinaryString(n).equals(s));
		assertTrue(BaseConverter.fromBinaryString(s) == 47);
		assertTrue(BaseConverter.toHexString(n).equals("2F"));

	n = 2620;
	s = "A3C";
		assertTrue(BaseConverter.toBinaryString(n).equals("101000111100"));
		assertTrue(BaseConverter.fromHexString(s) == 2620);
		assertTrue(BaseConverter.toHexString(n).equals(s));
	}
}
