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
 * Tests for {@link ComparisonResult}.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class ComparisonResultTest {


	/**
	 * Test method for {@link ComparisonResult#ComparisonResult(Position, Position)} - illegal argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void newComparisonResultIllegalArgument() {
		new ComparisonResult(null, new Position(1, 2));
	}

	/**
	 * Test method for {@link pl.ncdc.differentia.ComparisonResult#isDifferent()}.
	 */
	@Test
	public final void isDifferent() {
		final ComparisonResult r1 = new ComparisonResult(null, null);
		assertFalse(r1.isDifferent());
		final ComparisonResult r2 = new ComparisonResult(new Position(10, 20), new Position(30, 40));
		assertTrue(r2.isDifferent());
	}

	/**
	 * Test method for {@link ComparisonResult#getDifferencePositionInExpected()}.
	 */
	@Test
	public final void getDifferencePositionInExpected() {
		final ComparisonResult r = new ComparisonResult(new Position(1, 2), new Position(3, 4));
		final Position p = r.getDifferencePositionInExpected();
		assertEquals(1, p.getLine());
		assertEquals(2, p.getColumn());
	}

	/**
	 * Test method for {@link ComparisonResult#getDifferencePositionInActual()}.
	 */
	@Test
	public final void getDifferencePositionInActual() {
		final ComparisonResult r = new ComparisonResult(new Position(1, 2), new Position(3, 4));
		final Position p = r.getDifferencePositionInActual();
		assertEquals(3, p.getLine());
		assertEquals(4, p.getColumn());
	}

}
