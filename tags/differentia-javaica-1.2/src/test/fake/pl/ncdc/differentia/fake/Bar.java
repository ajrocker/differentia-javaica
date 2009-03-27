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
package pl.ncdc.differentia.fake;

/**
 * Completety different comment then in Bar.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class Bar {

	/** private fields comments */
	private final String m_id;

	/** private fields comments */
	private String m_name;

	/**
	 * Creates Bar.
	 *
	 * @param id the id.
	 */
	public Bar(final String id) { // some test comments
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
