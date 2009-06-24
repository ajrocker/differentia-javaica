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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.runtime.RecognitionException;
import org.junit.Assert;

/**
 * JUnit specific assert.
 * <p>
 * Created on Jan 19, 2009
 * 
 * @author hshsce
 * @author lukasz.k.wolski
 * @version $Id$
 * @see Assert
 */
public class DifferentiaAssert {

	/**
	 * Asserts that two java source codes are equal.
	 *
	 * @param actual the path to expected java source code.
	 * @param expected the path to actual java source code.
	 */
	public static void assertSourcesEqual(final String expected, final String actual) {
		assertSourcesEqual(expected, actual, false, false);
	}

	/**
	 * Asserts that two java source codes are equal but package names and
	 * content of <code>@Generated</code> annotation may differ.
	 * 
	 * @param actual the path to expected java source code.
	 * @param expected the path to actual java source code.
	 * @param debug print syntax tree to standard output if true
	 * @param relaxed true if package names and <code>@Generated</code> annotation
	 * 				  may differ, false otherwise
	 */
	public static void assertSourcesEqual(final String expected, final String actual, final boolean debug, final boolean relaxed) {
		final Differentia differentia = new Differentia();
		differentia.setDebug(debug);
		differentia.setRelaxed(relaxed);
		final ComparisonResult result;
		try {
			result = differentia.compare(expected, actual);
		} catch (final IOException e) {
			throw new RuntimeException("IO Exception while comparing java sources", e);
		} catch (final RecognitionException e) {
			throw new RuntimeException("Parsing error while comparing java sources", e);
		}
		if (result.isDifferent()) {
			Assert.fail("Java source codes differ: " + DifferentiaUtils.getDifferenceMessage(expected, actual, result));
		}
	}

	public static void assertSourcesEqual(final Reader expected, final Reader actual) {
		assertSourcesEqual(expected, actual, false, false);
	}
	
	public static void assertSourcesEqual(final Reader expected, final Reader actual, final boolean debug, final boolean relaxed) {
		final Differentia differentia = new Differentia();
		differentia.setDebug(debug);
		differentia.setRelaxed(relaxed);
		final ComparisonResult result;
		try {
			result = differentia.compare(expected, actual);
		} catch (final IOException e) {
			throw new RuntimeException("IO Exception while comparing java sources", e);
		} catch (final RecognitionException e) {
			throw new RuntimeException("Parsing error while comparing java sources", e);
		}
		if (result.isDifferent()) {
			Assert.fail("Java source codes differ: " + DifferentiaUtils.getDifferenceMessage("expected", "actual", result));
		}
	}

	public static void assertSourcesAsStringsEqual(final String expected, final String actual) {
		assertSourcesAsStringsEqual(expected, actual, false, false);
	}

	/**
	 * Asserts that two java source codes are equal.
	 * 
	 * @param actual
	 *            the path to expected java source code.
	 * @param expected
	 *            the path to actual java source code.
	 * @param relaxed
	 *            if <code>true</code> comparison will omit values of parameter
	 *            <code>date</code> and parameter <code>comment</code> from
	 *            annotation <code>Generated</code>.
	 */
	public static void assertSourcesAsStringsEqual(final String expected, final String actual, final boolean debug, final boolean relaxed) {
		assertSourcesEqual(new StringReader(expected), new StringReader(actual), debug, relaxed);
	}

}
