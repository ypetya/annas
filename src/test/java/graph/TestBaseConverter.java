package annas.test.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBaseConverter {

	@Test
	public final void testBaseConverter(){
	int n = 47;
	String s = "101111";
		assertTrue(annas.math.BaseConverter.toBinaryString(n).equals(s));
		assertTrue(annas.math.BaseConverter.fromBinaryString(s) == 47);
		assertTrue(annas.math.BaseConverter.toHexString(n).equals("2F"));

	n = 2620;
	s = "A3C";
		assertTrue(annas.math.BaseConverter.toBinaryString(n).equals(
				"101000111100"));
		assertTrue(annas.math.BaseConverter.fromHexString(s) == 2620);
		assertTrue(annas.math.BaseConverter.toHexString(n).equals(s));
	}
}
