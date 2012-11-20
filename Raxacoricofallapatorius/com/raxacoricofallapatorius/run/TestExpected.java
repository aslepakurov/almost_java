package com.raxacoricofallapatorius.run;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class TestExpected {
	
	public static String codeInt = "1 2 3 4 5 6 7 8 9 0";
//	public static String codeInt = "1";
	public static ArrayList<Token> expectedInt = new ArrayList<Token>();
	public static String codeDouble = "1.1 2.2 3.3 4.4 5.5 6.6 7.7 8.8 9.9 0.123456";
	public static ArrayList<Token> expectedDouble = new ArrayList<Token>();
	public static String codeStrings = "\"almost\" \"java\" \"rules the\" \"web\"";
	public static ArrayList<Token> expectedStrings = new ArrayList<Token>();
	public static String codeKeywords = "func  int    float      true false bool                   continue";
	public static ArrayList<Token> expectedKeywords = new ArrayList<Token>();
	public static String codeSeparator = ", == <= >= != * + - = &";
	public static ArrayList<Token> expectedSeparator = new ArrayList<Token>();
	public static String codeVenigret = "func main(int varInt,float varFloat){" +
											"\nstr varStr = \"This is a string var\"" +
											"\nvarInt = 42" +
											"\nvarFloat = 42.42" +
											"\nif(varInt==24){" +
												"\nvarFloat = 24.24" +
											"\n}" +
										"\n}";
	public static ArrayList<Token> expectedVenigret = new ArrayList<Token>();
	static
	{
		expectedSeparator.add(new Token(TokenType.TK_S_COMMA.getName(), TokenType.TK_S_COMMA, 1, 1));
		expectedSeparator.add(new Token(TokenType.TK_S_EQUAL.getName(), TokenType.TK_S_EQUAL, 1, 3));
		expectedSeparator.add(new Token(TokenType.TK_S_EQUAL_OR_LESS.getName(), TokenType.TK_S_EQUAL_OR_LESS, 1, 6));
		expectedSeparator.add(new Token(TokenType.TK_S_EQUAL_OR_GREATER.getName(), TokenType.TK_S_EQUAL_OR_GREATER, 1, 9));
		expectedSeparator.add(new Token(TokenType.TK_S_NOT.getName(), TokenType.TK_S_NOT, 1, 12));
		expectedSeparator.add(new Token(TokenType.TK_S_MULTI.getName(), TokenType.TK_S_MULTI, 1, 15));
		expectedSeparator.add(new Token(TokenType.TK_S_ADD.getName(), TokenType.TK_S_ADD, 1, 17));
		expectedSeparator.add(new Token(TokenType.TK_S_SUBTRACT.getName(), TokenType.TK_S_SUBTRACT, 1, 19));
		expectedSeparator.add(new Token(TokenType.TK_S_INIT.getName(), TokenType.TK_S_INIT, 1, 21));
		expectedSeparator.add(new Token(TokenType.TK_S_AND.getName(), TokenType.TK_S_AND, 1, 23));
		expectedSeparator.add(new Token(TokenType.TK_EOP.getName(), TokenType.TK_EOP, 1, 24));
	}
	static
	{
		expectedVenigret.add(new Token("func", TokenType.TK_K_FUNC, 1, 1));
		expectedVenigret.add(new Token("main", TokenType.TK_ID, 1, 6));
		expectedVenigret.add(new Token("(", TokenType.TK_S_LEFT_PARENT, 1, 10));
		expectedVenigret.add(new Token("int", TokenType.TK_K_INT, 1, 11));
		expectedVenigret.add(new Token("varInt", TokenType.TK_ID, 1, 15));
		expectedVenigret.add(new Token(",", TokenType.TK_S_COMMA, 1, 21));
		expectedVenigret.add(new Token("float", TokenType.TK_K_FLOAT, 1, 22));
		expectedVenigret.add(new Token("varFloat", TokenType.TK_ID, 1, 28));
		expectedVenigret.add(new Token(")", TokenType.TK_S_RIGHT_PARENT, 1, 36));
		expectedVenigret.add(new Token("{", TokenType.TK_S_LEFT_BRACE, 1, 37));
		expectedVenigret.add(new Token("str", TokenType.TK_K_STR, 2, 1));
		expectedVenigret.add(new Token("varStr", TokenType.TK_ID, 2, 5));
		expectedVenigret.add(new Token("=", TokenType.TK_S_INIT, 2, 12));
		expectedVenigret.add(new Token("This is a string var", TokenType.TK_STRING, 2, 15));
		expectedVenigret.add(new Token("varInt", TokenType.TK_ID, 3, 1));
		expectedVenigret.add(new Token("=", TokenType.TK_S_INIT, 3, 8));
		expectedVenigret.add(new Token("42", TokenType.TK_INT, 3, 10));
		expectedVenigret.add(new Token("varFloat", TokenType.TK_ID, 4, 1));
		expectedVenigret.add(new Token("=", TokenType.TK_S_INIT, 4, 10));
		expectedVenigret.add(new Token("42.42", TokenType.TK_FLOAT, 4, 12));
		expectedVenigret.add(new Token("if", TokenType.TK_K_IF, 5, 1));
		expectedVenigret.add(new Token("(", TokenType.TK_S_LEFT_PARENT, 5, 3));
		expectedVenigret.add(new Token("varInt", TokenType.TK_ID, 5, 4));
		expectedVenigret.add(new Token("==", TokenType.TK_S_EQUAL, 5, 10));
		expectedVenigret.add(new Token("24", TokenType.TK_INT, 5, 12));
		expectedVenigret.add(new Token(")", TokenType.TK_S_RIGHT_PARENT, 5, 14));
		expectedVenigret.add(new Token("{", TokenType.TK_S_LEFT_BRACE, 5, 15));
		expectedVenigret.add(new Token("varFloat", TokenType.TK_ID, 6, 1));
		expectedVenigret.add(new Token("=", TokenType.TK_S_INIT, 6, 10));
		expectedVenigret.add(new Token("24.24", TokenType.TK_FLOAT, 6, 12));
		expectedVenigret.add(new Token("}", TokenType.TK_S_RIGHT_BRACE, 7, 1));
		expectedVenigret.add(new Token("}", TokenType.TK_S_RIGHT_BRACE, 8, 1));
		expectedVenigret.add(new Token("",TokenType.TK_EOP,8,2));
	}
	static
	{
		expectedKeywords.add(new Token("func", TokenType.TK_K_FUNC, 1, 1));
		expectedKeywords.add(new Token("int", TokenType.TK_K_INT, 1, 7));
		expectedKeywords.add(new Token("float", TokenType.TK_K_FLOAT, 1, 14));
		expectedKeywords.add(new Token("true", TokenType.TK_K_TRUE, 1, 25));
		expectedKeywords.add(new Token("false", TokenType.TK_K_FALSE, 1, 30));
		expectedKeywords.add(new Token("bool", TokenType.TK_K_BOOL, 1, 36));
		expectedKeywords.add(new Token("continue", TokenType.TK_K_CONTINUE, 1, 59));
		expectedKeywords.add(new Token("", TokenType.TK_EOP, 1, 67));
	}
	static
	{
		expectedInt.add(new Token("1", TokenType.TK_INT, 1, 1));
		expectedInt.add(new Token("2", TokenType.TK_INT, 1, 3));
		expectedInt.add(new Token("3", TokenType.TK_INT, 1, 5));
		expectedInt.add(new Token("4", TokenType.TK_INT, 1, 7));
		expectedInt.add(new Token("5", TokenType.TK_INT, 1, 9));
		expectedInt.add(new Token("6", TokenType.TK_INT, 1, 11));
		expectedInt.add(new Token("7", TokenType.TK_INT, 1, 13));
		expectedInt.add(new Token("8", TokenType.TK_INT, 1, 15));
		expectedInt.add(new Token("9", TokenType.TK_INT, 1, 17));
		expectedInt.add(new Token("0", TokenType.TK_INT, 1, 19));
		expectedInt.add(new Token("", TokenType.TK_EOP, 1, 20));
	}
	static
	{
		expectedDouble.add(new Token("1.1", TokenType.TK_FLOAT, 1, 1));
		expectedDouble.add(new Token("2.2", TokenType.TK_FLOAT, 1, 5));
		expectedDouble.add(new Token("3.3", TokenType.TK_FLOAT, 1, 9));
		expectedDouble.add(new Token("4.4", TokenType.TK_FLOAT, 1, 13));
		expectedDouble.add(new Token("5.5", TokenType.TK_FLOAT, 1, 17));
		expectedDouble.add(new Token("6.6", TokenType.TK_FLOAT, 1, 21));
		expectedDouble.add(new Token("7.7", TokenType.TK_FLOAT, 1, 25));
		expectedDouble.add(new Token("8.8", TokenType.TK_FLOAT, 1, 29));
		expectedDouble.add(new Token("9.9", TokenType.TK_FLOAT, 1, 33));
		expectedDouble.add(new Token("0.123456", TokenType.TK_FLOAT, 1, 37));
		expectedDouble.add(new Token("", TokenType.TK_EOP, 1, 45));
	}
	static
	{
		expectedStrings.add(new Token("almost", TokenType.TK_STRING, 1, 2));
		expectedStrings.add(new Token("java", TokenType.TK_STRING, 1, 11));
		expectedStrings.add(new Token("rules the", TokenType.TK_STRING, 1, 18));
		expectedStrings.add(new Token("web", TokenType.TK_STRING, 1, 30));
		expectedStrings.add(new Token("", TokenType.TK_EOP, 1, 34));
	}
}
