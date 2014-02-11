package annas.test.math;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import annas.math.Complex;
import org.junit.Assert;
import org.junit.Ignore;

public class TestComplex {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testComplex() {
		try {
		Complex c = new Complex(9, 1);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testReal() {
		Complex c = new Complex(9, 1);
		assertTrue(c.real() == 9d);
	}

	@Test
	public void testImag() {
		Complex c = new Complex(9, 1);
		assertTrue(c.imag() == 1d);
	}

	@Test
	public void testMod() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(1, 1);
		Complex e = new Complex(0, 0);

		assertTrue(e.mod() == 0d);
		assertTrue(d.mod() == Math.sqrt(2));
		assertTrue(c.mod() == Math.sqrt(82));
	}

	@Test
	public void testArg() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(1, 1);
		Complex e = new Complex(0, 0);

		assertTrue(e.arg() == Math.atan2(0, 0));
		assertTrue(d.arg() == Math.atan2(1, 1));
		assertTrue(c.arg() == Math.atan2(1, 9));
	}

	@Test
	public void testConj() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(1, 1);
		Complex e = new Complex(0, 0);

		assertTrue(c.conj().real() == 9 && c.conj().imag() == -1);
		assertTrue(d.conj().real() == 1 && d.conj().imag() == -1);
		assertTrue(e.conj().real() == 0 && e.conj().imag() == 0);
	}

	@Test
	public void testPlus() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(1, 1);
		Complex e = c.plus(d);

		assertTrue(e.real() == 10 && e.imag() == 2);
	}

	@Test
	public void testMinus() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(1, 1);
		Complex e = c.minus(d);

		assertTrue(e.real() == 8 && e.imag() == 0);
	}

	@Test
	public void testTimes() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(2, 2);
		Complex e = c.times(d);

		assertTrue(e.real() == 16 && e.imag() == 20);
	}

	@Test
	public void testDiv() {
		Complex c = new Complex(9, 1);
		Complex d = new Complex(2, 2);
		Complex e = c.div(d);
		System.out.println(e);
		assertTrue(e.real() == (20d / 8d) && e.imag() == (-16d / 8d));
	}

        @Ignore
	@Test
	public void testExp() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testLog() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testSqrt() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testSin() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testCos() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testSinh() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testCosh() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testTan() {
		fail("Not yet implemented");
	}

        @Ignore
	@Test
	public void testChs() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
            //x+i*y, x-i*y, x, or i*y
            Complex c = new Complex(1,2);
            assertTrue("1.0+i*2.0".equals(c.toString()));
            c = new Complex(0,2);
            assertTrue("i*2.0".equals(c.toString()));
            c = new Complex(1,-2);
            assertTrue("1.0-i*2.0".equals(c.toString()));
            c = new Complex(0,2);
            assertTrue("i*2.0".equals(c.toString()));
	}

}
