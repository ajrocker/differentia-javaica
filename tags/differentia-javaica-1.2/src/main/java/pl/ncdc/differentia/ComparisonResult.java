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

/**
 * Result of comparison between to java source code files.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class ComparisonResult {

	/**
	 * Indicates no difference result.
	 */
	public static final ComparisonResult NO_DIFFERENCE = new ComparisonResult(null, null);

	private final Position m_differencePositionInExpected;

	private final Position m_differencePositionInActual;

	/**
	 * Creates comparison result.
	 *
	 * @param differencePositionInExpected the position of encountered difference in expected file.
	 * @param differencePositionInActual the position of encountered difference in actual file.
	 *
	 * @throws IllegalArgumentException if one of the parameters is <code>null</code> and the other is not.
	 */
	public ComparisonResult(final Position differencePositionInExpected, final Position differencePositionInActual) {
		if ((differencePositionInExpected == null) && (differencePositionInActual != null)) {
			throw new IllegalArgumentException("Difference positions should either have values or both be null");
		}
		m_differencePositionInExpected = differencePositionInExpected;
		m_differencePositionInActual = differencePositionInActual;
	}

	/**
	 * Returns difference flag.
	 *
	 * @return <code>true</code> if code differs, <code>false</code> otherwise.
	 */
	public boolean isDifferent() {
		return (m_differencePositionInExpected != null);
	}

	/**
	 * Returns difference position in expected file.
	 *
	 * @return the differencePositionInExpected
	 */
	public Position getDifferencePositionInExpected() {
		return m_differencePositionInExpected;
	}

	/**
	 * Returns difference position in actual file.
	 *
	 * @return the differencePositionInActual
	 */
	public Position getDifferencePositionInActual() {
		return m_differencePositionInActual;
	}

}
