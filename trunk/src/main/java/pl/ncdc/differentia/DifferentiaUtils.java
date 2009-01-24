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

import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;

import pl.ncdc.differentia.antlr.JavaLexer;
import pl.ncdc.differentia.antlr.JavaParser;

/**
 * Supporting code of Differentia Javaica.
 * <p>
 * Created on Jan 19, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class DifferentiaUtils {

	private DifferentiaUtils() {
		/* util class, non-instantiable */
	}

	/**
	 * Prepares comparison result according to text {@link Position} associated with given trees nodes.
	 *
	 * @param expected the expected source tree node.
	 * @param actual the actual source tree node.
	 * @return the comparison result.
	 */
    public static ComparisonResult getDifferenceResult(final Tree expected, final Tree actual) {
    	return new ComparisonResult(getTextPosition(expected), getTextPosition(actual));
    }

    /**
     * Returns text position of the given tree node.
     *
     * @param tree the source tree node.
     * @return the text position.
     */
    public static Position getTextPosition(final Tree tree) {
    	return new Position(tree.getLine(), tree.getCharPositionInLine());
    }

    /**
     * Creates parser from given stream.
     *
     * @param stream the char stream.
     * @return the java parser.
     */
    public static JavaParser getParser(final CharStream stream) {
        final JavaLexer lexer = new JavaLexer(stream);
        final CommonTokenStream in = new CommonTokenStream(lexer);
        return new JavaParser(in);
    }

	/**
	 * Prepares message which describes difference between compared Java sources.
	 *
	 * @param expectedId id of the expected source.
	 * @param actualId id of the actual source.
	 * @param result the comparison result.
	 * @return textual representation of difference.
	 */
	public static String getDifferenceMessage(final String expectedId, final String actualId, final ComparisonResult result) {
		final String msg;
		if (result.isDifferent()) {
			final Position expectedPosition = result.getDifferencePositionInExpected();
			final Position actualPosition = result.getDifferencePositionInActual();
			msg = "Difference[" + expectedId + expectedPosition + "<->" + actualId + actualPosition + "]";
		} else {
			msg = "No difference[" + expectedId + "<->" + actualId + "]";
		}
		return msg;
	}

}
