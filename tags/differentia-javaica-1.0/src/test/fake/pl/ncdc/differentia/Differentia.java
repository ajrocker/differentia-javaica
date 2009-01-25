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
import java.io.InputStream;
import java.io.Reader;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import pl.ncdc.differentia.antlr.JavaParser;

/**
 *
 * <p>
 * Created on Jan 17, 2009
 *
 * @author hshsce
 * @version $Id$
 */
public class Differentia {

	public static void main(final String[] args) {
		final int exitCode;
		if (args.length != 2) {
			System.out.println("Usage: differentia-javaica expected_source actual_source");
			exitCode = 2;
		} else {
			final String expected = args[0];
			final String actual = args[1];
			final Result result = compareCheckingExceptions(expected, actual);
			if (result.m_exception != null) {
				System.out.println("differentia-javaica: parsing error");
				exitCode = 3;
			} else {
				if (result.m_result.isDifferent()) {
					System.out.println("differentia-javaica: difference: " + DifferentiaUtils.getDifferenceMessage(expected, actual, result.m_result));
					exitCode = 1;
				} else {
					System.out.println("differentia-javaica: no difference");
					exitCode = 0;
				}
			}
		}
		System.exit(exitCode);
	}

	private static Result compareCheckingExceptions(final String expected, final String actual) {
		final Result result = new Result();
		try {
			result.m_result = compare(expected, actual);
		} catch (final IOException e) {
			result.m_exception = e;
		} catch (final RecognitionException e) {
			result.m_exception = e;
		}
		return result;
	}

    public static ComparisonResult compareStrings(final String expected, final String actual) throws RecognitionException {
        return compare(new ANTLRStringStream(expected), new ANTLRStringStream(actual));
    }

    public static ComparisonResult compare(final String expected, final String actual) throws  IOException, RecognitionException {
        return compare(new ANTLRFileStream(expected), new ANTLRFileStream(actual));
    }

    public static ComparisonResult compare(final InputStream expected, final InputStream actual) throws IOException, RecognitionException {
    	return compare(new ANTLRInputStream(expected), new ANTLRInputStream(actual));
    }

    public static ComparisonResult compare(final Reader expected, final Reader actual) throws RecognitionException, IOException {
        return compare(new ANTLRReaderStream(expected), new ANTLRReaderStream(actual));
    }

    public static ComparisonResult compare(final CharStream expected, final CharStream actual) throws RecognitionException {
        return compare(DifferentiaUtils.getParser(expected), DifferentiaUtils.getParser(actual));
    }

    public static ComparisonResult compare(final JavaParser expected, final JavaParser actual) throws RecognitionException {
        return compare((CommonTree) expected.compilationUnit().getTree(), (CommonTree) expected.compilationUnit().getTree());
    }

    /**
     * Generic method to compare recursively two AST nodes.
     * <p/>
     * There are three steps for the comparison :
     * <ul>
     * <li>- nodes are compared to each others</li>
     * <li>- first child nodes are compared to each others</li>
     * <li>- next sibling nodes are compared to each others</li>
     * </ul>
     * If all these comparison succeed, nodes are considered to be equals
     * </p>
     *
     * @param expected node
     * @param actual   node
     */
    private static ComparisonResult compare(final Tree expected, final Tree actual) {
    	final String expectedText = expected.getText();
    	final String actualText = actual.getText();
    	final ComparisonResult result;
    	if (expectedText == null) {
    		if (actualText != null) {
            	result = DifferentiaUtils.getDifferenceResult(expected, actual);
    		} else {
    			result = ComparisonResult.NO_DIFFERENCE;
    		}
    	} else {
            if (expectedText.equals(actualText)) {
            	result = compareChildren(expected, actual);
            } else {
            	result = DifferentiaUtils.getDifferenceResult(expected, actual);
            }
    	}
    	return result;
    }

    private static ComparisonResult compareChildren(final Tree expected, final Tree actual) {
        final int expectedChildCount = expected.getChildCount();
        final int actualChildCount = actual.getChildCount();
        final ComparisonResult result;
        if (expectedChildCount != actualChildCount) {
        	result = DifferentiaUtils.getDifferenceResult(expected, actual);
        } else {
        	ComparisonResult childResult = null;
            for (int i = 0; i < expectedChildCount; i++) {
    			final Tree c1 = expected.getChild(i);
    			final Tree c2 = actual.getChild(i);
    			final ComparisonResult innerResult = compare(c1, c2);
    			if (innerResult.isDifferent()) {
    				childResult = innerResult;
    				break;
    			}
    		}
            if (childResult == null) {
            	result = ComparisonResult.NO_DIFFERENCE;
			} else {
				result = childResult;
			}
        }
        return result;
    }

    private static class Result {
    	private ComparisonResult m_result;
    	private Exception m_exception;
    }

}
