package com.raxacoricofallapatorius.run;

import java.util.ArrayList;

//import junit.framework.Assert;


//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;

import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//public class UnitTest extends Assert {
public class UnitTest extends TestCase {
	private  ArrayList<Token> test;
	private ArrayList<Token> expected;
	private String code;
	private LexicalAnalyzer la;
	public UnitTest(String name,ArrayList<Token> expected,String code) {
		super(name);
		this.expected = expected;
		this.code = code;
	}
	@Override
//	@Before
	protected void setUp() throws Exception {
//		super.setUp();
		la = new LexicalAnalyzer(code);
		test = new ArrayList<Token>();
		Token token;
		try {
			do {
				token = la.lex();
//				System.out.println("token = " + token);
				test.add(token);
			} while (token.getType() != TokenType.TK_EOP);
		} catch (LexerException e) {
			System.out.println("Error : " + e);
		}
	}
//	@After
	@Override
	protected void tearDown() throws Exception {
//		super.tearDown();
		test = null;
	}
//	@Test

	public void testInt(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
//O*&^(*&^#(*&^($*%)@#(%*_@)#*%_@%
//	@Test
	public void testDouble(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
//	@Test
	public void testString(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
//	@Test
	public void testKeyword(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
//	@Test
	public void testSeparator(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
//	@Test
	public void testVenigret(){
//		System.out.println(expected);
//		System.out.println(test);
		assertTrue(Token.arrEquals(test, expected));
	}
	public static Test suite(){
		TestSuite ts = new TestSuite();
		ts.addTest(new UnitTest("testInt", TestExpected.expectedInt, TestExpected.codeInt));
		ts.addTest(new UnitTest("testDouble", TestExpected.expectedDouble, TestExpected.codeDouble));
		ts.addTest(new UnitTest("testString", TestExpected.expectedStrings, TestExpected.codeStrings));
		ts.addTest(new UnitTest("testKeyword", TestExpected.expectedKeywords, TestExpected.codeKeywords));
		ts.addTest(new UnitTest("testSeparator", TestExpected.expectedSeparator, TestExpected.codeSeparator));
		ts.addTest(new UnitTest("testVenigret", TestExpected.expectedVenigret, TestExpected.codeVenigret));
		return ts;
	}
}
