package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curCharLine = 0;
	public int curPos = 0;
	public char curCh = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String code = null;

	// private int code_size = 0;// code.length();

	// private int lastLine = 0;
	// private int lastColumn = 0;

	/**
	 * Consructor, kep :)
	 * 
	 * @param code
	 *            source code
	 */
	public LexicalAnalyzer(String code) {
		this.code = code;
		// code_size = code.length();
	}

	private Token lexProcessor() throws LexerException {
		StringBuilder buf = new StringBuilder();
		for (; curPos < code.length(); curPos++) {
			curCh = code.charAt(curPos);
			switch (curCh) {

			// -------ignored separators---------

			case ' ':
				curCharLine++;
				continue;
			case '\n':
				curCharLine = 0;
				curLine++;
				continue;

				// ------tokenized separators--------

			case '(':
				return new Token(TokenType.TK_S_LEFT_PARENT.getName(),
						TokenType.TK_S_LEFT_PARENT, curLine, curCharLine);
			case ')':
				return new Token(TokenType.TK_S_RIGHT_PARENT.getName(),
						TokenType.TK_S_RIGHT_PARENT, curLine, curCharLine);
			case '{':
				return new Token(TokenType.TK_S_LEFT_BRACE.getName(),
						TokenType.TK_S_LEFT_BRACE, curLine, curCharLine);
			case '}':
				return new Token(TokenType.TK_S_RIGHT_BRACE.getName(),
						TokenType.TK_S_RIGHT_BRACE, curLine, curCharLine);
			case '&':
				return new Token(TokenType.TK_S_AND.getName(),
						TokenType.TK_S_AND, curLine, curCharLine);
			case '|':
				return new Token(TokenType.TK_S_OR.getName(),
						TokenType.TK_S_OR, curLine, curCharLine);
			case '*':
				return new Token(TokenType.TK_S_MULTI.getName(),
						TokenType.TK_S_MULTI, curLine, curCharLine);
			case '/':
				return new Token(TokenType.TK_S_DIVIDE.getName(),
						TokenType.TK_S_DIVIDE, curLine, curCharLine);
			case '+':
				return new Token(TokenType.TK_S_ADD.getName(),
						TokenType.TK_S_ADD, curLine, curCharLine);
			case '-':
				return new Token(TokenType.TK_S_SUBTRACT.getName(),
						TokenType.TK_S_SUBTRACT, curLine, curCharLine);

				// -------Ch x 2-----------

			case '!':
				if (hasNextChar() && code.charAt(curPos + 1) == '=') {
					return new Token(TokenType.TK_S_NOT.getName(),
							TokenType.TK_S_NOT, curLine, curCharLine);
				} else {
					throw new LexerException("= expected on line: " + curLine
							+ " row: " + (curCharLine + 1));
				}
			case '=':
				if (hasNextChar() && code.charAt(curPos + 1) == '=') {
					return new Token(TokenType.TK_S_EQUAL.getName(),
							TokenType.TK_S_EQUAL, curLine, curCharLine);
				} else
					throw new LexerException("= expected on line: " + curLine
							+ " row: " + (curCharLine + 1));
			case '<':
				if (hasNextChar() && code.charAt(curPos + 1) == '=')
					return new Token(TokenType.TK_S_EQUAL_OR_LESS.getName(),
							TokenType.TK_S_EQUAL_OR_LESS, curLine, curCharLine);
				else
					return new Token(TokenType.TK_S_LESS.getName(),
							TokenType.TK_S_LESS, curLine, curCharLine);
			case '>':
				if (hasNextChar() && code.charAt(curPos + 1) == '=') {
					return new Token(TokenType.TK_S_EQUAL_OR_GREATER.getName(),
							TokenType.TK_S_EQUAL_OR_GREATER, curLine,
							curCharLine);
				} else
					return new Token(TokenType.TK_S_GREATER.getName(),
							TokenType.TK_S_GREATER, curLine, curCharLine);
				// -------Numeric -----------
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				boolean isFloat = false;
				TokenType type = TokenType.TK_INT;
				for (; curPos < code.length(); curPos++) {
					curCh = code.charAt(curPos);
					if (curCh == '.' && !isFloat) {
						isFloat = true;
						type = TokenType.TK_FLOAT;
						buf.append(curCh);
						continue;
					} else if (curCh == '.' && isFloat) {
						throw new LexerException("wrong expresion at "
								+ curLine + " row: " + curPos);
					}
					if (curCh >= '0' && curCh <= '9')
						buf.append(curCh);
					else {
						if (buf.toString().endsWith("."))
							throw new LexerException(
									" value expected after dot at " + curLine
											+ " row: "
											+ (curCharLine + buf.length()));
						return new Token(buf.toString(), type, curLine,
								curCharLine);
					}
				}
				if (buf.toString().endsWith("."))
					throw new LexerException(" value expected after dot at "
							+ curLine + " row: " + (curCharLine + buf.length()));
				return new Token(buf.toString(), type, curLine, curCharLine);

				// ---------Dealing with strings--------

				// marked as comments because not fixed yet

				// // for (int ch = 0; ch < code.length(); ch++) {
				// currentChar = code.charAt(ch);
				//
				// if ((currentChar == '\"') && (!checkOnQuote)) {
				// checkOnQuote = true;
				// continue;
				// } else if ((checkOnQuote) && (currentChar == '\"')) {
				// checkOnQuote = false;
				// text.add(new Token(buf.toString(), TokenType.TK_STRING,
				// 0/* temporary_not_implemented */, 0/*
				// temporary_not_implemented */));
				// buf = new StringBuilder();
				// } else if (checkOnQuote) {
				// buf.append(currentChar);
				// }
				// }
				// return text;
				// }

				// def temporry anavailable
				// default:
				// buf.append(curCh);
				// if ((code.charAt(curCh + 1) < 'A')
				// && (code.charAt(curCh + 1) > 'z')
				// && ((code.charAt(curCh + 1) > 'Z') & (code
				// .charAt(curCh + 1) < 95))
				// || (code.charAt(curCh + 1) != '$')) {
				// if (buf.toString().equalsIgnoreCase("int"))
				// return new Token(buf.toString(), TokenType.TK_K_INT,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("float"))
				// return new Token(buf.toString(), TokenType.TK_K_FLOAT,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("str"))
				// return new Token(buf.toString(), TokenType.TK_K_STR,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("void"))
				// return new Token(buf.toString(), TokenType.TK_K_VOID,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("bool"))
				// return new Token(buf.toString(), TokenType.TK_K_BOOL,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("true"))
				// return new Token(buf.toString(), TokenType.TK_K_TRUE,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("false"))
				// return new Token(buf.toString(), TokenType.TK_K_FALSE,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("func"))
				// return new Token(buf.toString(), TokenType.TK_K_FUNC,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("for"))
				// return new Token(buf.toString(), TokenType.TK_K_FOR,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("while"))
				// return new Token(buf.toString(), TokenType.TK_K_WHILE,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("if"))
				// return new Token(buf.toString(), TokenType.TK_K_IF,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("else"))
				// return new Token(buf.toString(), TokenType.TK_K_ELSE,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("continue"))
				// return new Token(buf.toString(),
				// TokenType.TK_K_CONTINUE, curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("break"))
				// return new Token(buf.toString(), TokenType.TK_K_BREAK,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("const"))
				// return new Token(buf.toString(), TokenType.TK_K_CONST,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("def"))
				// return new Token(buf.toString(), TokenType.TK_K_DEF,
				// curLine, curCharLine);
				// if (buf.toString().equalsIgnoreCase("div"))
				// return new Token(buf.toString(), TokenType.TK_K_DIV,
				// curLine, curCharLine);
				// // do we need more keywords????
				// return new Token(buf.toString(), TokenType.TK_ID, curLine,
				// curCharLine);
				// }
			}

		}
		return null;
	}

	public Token lex() throws LexerException {
		Token result = lexProcessor();
		System.out.println("curPos " + curPos);
		if (result != null) {
			curPos += result.getName().length();
			curCharLine += result.getName().length();
		}
		return result;
	}

	private boolean hasNextChar() {
		return curPos + 1 != code.length();
	}

	/**
	 * already unnecessary
	 * 
	 * @param token
	 * @return
	 * @throws LexerException
	 */
	private Token checkNumericType(Token token) throws LexerException {
		if (token.getName().endsWith("."))
			throw new LexerException(" value expected after dot at " + curLine
					+ " row: " + (curCharLine + token.getName().length()));
		if (token.getName().contains("."))
			token.setType(TokenType.TK_FLOAT);
		return token;
	}

	/**
	 * Temporary lex() driver As an example of what sintaxanalyzer would do
	 */
	// public void run_lex() {
	// Token token = null;
	// do {
	// try {
	// token = lex();
	// if (token != null)
	// tokens.add(token);
	// } catch (LexerException e) {
	// System.out.println("Error : " + e);
	// }
	// } while (token != null);
	// }
}
