/*
 * Copyright 2009 Nordic Consulting & Development Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.ncdc.differentia;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests for {@link DifferentiaAssert}.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class DifferentiaAssertTest {

	/**
	 * Test method for {@link DifferentiaAssert#assertSourceEquals(String, String)}: equal sources.
	 */
	@Test
	public final void assertSourcesEqual() {
		DifferentiaAssert.assertSourcesEqual(getPath("Foo"), getPath("Foo"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourceEquals(String, String)}: equal sources, different comments.
	 */
	@Test
	public final void assertSourcesEqualDifferentComments() {
		DifferentiaAssert.assertSourcesEqual(getPath("Bar"), getFakePath("Bar"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourceEquals(String, String)}: different sources.
	 */
	@Test
	public final void assertSourcesEqualDifference() {
		boolean passed = false;
		String msg = "";
		try {
			DifferentiaAssert.assertSourcesEqual(getPath("Foo"), getPath("Bar"));
			passed = true;
		} catch (final AssertionError e) {
			msg = e.getMessage();
		}
		if (passed) {
			fail("No AssertionError was throw on comparison of different sources");
		}
		assertTrue("Incorrent message: " + msg, msg.matches("Java source codes differ: Difference\\[src/test/java/pl/ncdc/differentia/fake/Foo\\.java\\[[0-9]+:[0-9]+\\]<->src/test/java/pl/ncdc/differentia/fake/Bar\\.java\\[[0-9]+:[0-9]+\\]\\]"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourceEquals(String, String)}: different sources, small difference at the end.
	 */
	@Test
	public final void assertSourcesEqualSmallDifferenceAtTheEnd() {
		boolean passed = false;
		String msg = "";
		try {
			DifferentiaAssert.assertSourcesEqual(getPath("Buzz"), getFakePath("Buzz"));
			passed = true;
		} catch (final AssertionError e) {
			msg = e.getMessage();
		}
		if (passed) {
			fail("No AssertionError was throw on comparison of different sources");
		}
		assertTrue("Incorrent message: " + msg, msg.matches("Java source codes differ: Difference\\[src/test/java/pl/ncdc/differentia/fake/Buzz\\.java\\[[0-9]+:[0-9]+\\]<->src/test/fake/pl/ncdc/differentia/fake/Buzz\\.java\\[[0-9]+:[0-9]+\\]\\]"));
	}

	/**
	 * Test method for
	 * {@link DifferentiaAssert#assertSourceEquals(String, String)}: almost
	 * equal sources, unequal time stamp in @Generated.
	 *
	 * @throws Exception
	 */
	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentTimeStampInGeneratedAnnotation() throws Exception {
		String expectedPath = getPath("FooBar");

		String actualPath = getFakePath("FooBar");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentTimeStampInGeneratedAnnotation2() throws Exception {
		String expectedPath = getPath("FooBar2");

		String actualPath = getFakePath("FooBar2");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentPackageName() throws Exception {
		String expectedPath = getPath("FooBar");

		String actualPath = getPath("another/FooBar");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentPackageNameAndTimeStampInGeneratedAnnotation() throws Exception {
		String expectedPath = getPath("FooBar2");

		String actualPath = getPath("another/FooBar2");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	private String getPath(final String klass) {
		return "src/test/java/pl/ncdc/differentia/fake/" + klass + ".java";
	}

	private String getFakePath(final String klass) {
		return "src/test/fake/pl/ncdc/differentia/fake/" + klass + ".java";
	}

}
