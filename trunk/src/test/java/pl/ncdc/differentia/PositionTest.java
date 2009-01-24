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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.Test;

/**
 *
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class PositionTest {

	/**
	 * Test method for {@link Position#Position(int, int)} - invalid arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void newTextPosition00() {
		new Position(-1, -1);
	}

	/**
	 * Test method for {@link Position#Position(int, int)} - invalid arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void newTextPosition01() {
		new Position(-1, 0);
	}

	/**
	 * Test method for {@link Position#Position(int, int)} - invalid arguments.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void newTextPosition10() {
		new Position(0, -1);
	}

	/**
	 * Test method for {@link Position#Position(int, int)} - valid arguments.
	 */
	public final void newTextPosition11() {
		new Position(0, 0);
		new Position(10, 23);
	}

	/**
	 * Test method for {@link Position#getLine()}.
	 */
	@Test
	public final void getLine() {
		final Position p = new Position(313, 42);
		assertEquals("Invalid line", 313, p.getLine());
		assumeThat(p.getLine(), is(313));
	}

	/**
	 * Test method for {@link Position#getColumn()}.
	 */
	@Test
	public final void getColumn() {
		final Position p = new Position(313, 42);
		assumeThat(p.getColumn(), is(42));
	}

	/**
	 * Test method for {@link Position#toString()}.
	 */
	@Test
	public final void testToString() {
		final Position p = new Position(313, 42);
		assertThat(p.toString(), is("[313:42]"));
	}

}
