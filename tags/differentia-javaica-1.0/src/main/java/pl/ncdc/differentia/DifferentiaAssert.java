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

import org.antlr.runtime.RecognitionException;
import org.junit.Assert;

/**
 * JUnit specific assert.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
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
	public static void assertSourceEquals(final String expected, final String actual) {
		final ComparisonResult result;
		try {
			result = Differentia.compare(expected, actual);
		} catch (final IOException e) {
			throw new RuntimeException("IO Exception while comparing java sources", e);
		} catch (final RecognitionException e) {
			throw new RuntimeException("Parsing error while comparing java sources", e);
		}
		if (result.isDifferent()) {
			Assert.fail("Java source codes differ: " + DifferentiaUtils.getDifferenceMessage(expected, actual, result));
		}
	}

}
