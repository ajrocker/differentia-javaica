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

import org.apache.commons.lang.Validate;

/**
 * Position in the text.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class Position {

	private final int m_line;

	private final int m_column;

	/**
	 * Creates position object with given coordinates.
	 *
	 * @param line the line.
	 * @param column the column.
	 * @throws IllegalArgumentException if the <code>line</code> or the <code>column</code> is negative number.
	 */
	public Position(final int line, final int column) {
		Validate.isTrue(line >= 0, "line cannot be negative");
		Validate.isTrue(column >= 0, "column cannot be negative");
		m_line = line;
		m_column = column;
	}

	/**
	 * Returns line.
	 *
	 * @return the line.
	 */
	public int getLine() {
		return m_line;
	}

	/**
	 * Returns column.
	 *
	 * @return the column.
	 */
	public int getColumn() {
		return m_column;
	}

	/**
	 * Returns textual representation of position in text.
	 * Example: <code>[134:33]</code>
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + m_line + ":" + m_column + "]";
	}

}
