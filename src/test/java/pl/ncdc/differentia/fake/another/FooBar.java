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
package pl.ncdc.differentia.fake.another;

import javax.annotation.Generated;

/**
 * Fake foo bar class with <code>Generated</code> annotation.
 *
 * <p>
 * Created on Mar 24, 2009
 *
 * @author lukasz.k.wolski
 * @version $Id: FooBar.java 54 2009-03-27 15:20:25Z lukasz.k.wolski $
 */
@Generated(
	    value = "generated by copy&paste...",
	    date = "Sun Jan 14 07:10:00 CET 1979",
	    comments = "nothing special here")
public class FooBar {

	private final String m_id;

	private String m_name;

	/**
	 * Creates FooBar.
	 *
	 * @param id the id.
	 */
	public FooBar(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		m_id = id;
	}

	/**
	 * Return id.
	 *
	 * @return the id.
	 */
	public String getId() {
		return m_id;
	}

	/**
	 * Returns name.
	 *
	 * @return the name.
	 */
	public String getName() {
		return m_name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name to set.
	 */
	public void setName(final String name) {
		m_name = name;
	}

}
