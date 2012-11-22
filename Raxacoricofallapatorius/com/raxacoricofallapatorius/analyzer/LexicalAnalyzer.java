package com.raxacoricofallapatorius.analyzer;

import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.raxacoricofallapatorius.service.ConstHolder;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curCharLine = 1;
	public int curPos = 0;
	public char curCh = 0;
	private String code = null;

	/**
	 * Consructor
	 * 
	 * @param code
	 *            source code
	 */
	public LexicalAnalyzer(String code) {
		this.code = code;
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
				curCharLine = 1;
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
			case ',':
				return new Token(TokenType.TK_S_COMMA.getName(),
						TokenType.TK_S_COMMA, curLine, curCharLine);
			case ';':
				return new Token(TokenType.TK_S_SEMICOLON.getName(),
						TokenType.TK_S_SEMICOLON, curLine, curCharLine);

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
					return new Token(TokenType.TK_S_INIT.getName(),
							TokenType.TK_S_INIT, curLine, curCharLine);
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
				for (int tmpPos = curPos; tmpPos < code.length(); tmpPos++) {
					curCh = code.charAt(tmpPos);
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
					else
						break;
				}
				if (buf.toString().endsWith("."))
					throw new LexerException(" value expected after dot at "
							+ curLine + " row: " + (curCharLine + buf.length()));
				return new Token(buf.toString(), type, curLine, curCharLine);

				// ---------Dealing with strings--------

			case '\"': {
				boolean endOfString = false;
				for (int tmpPos = curPos + 1; tmpPos < code.length(); tmpPos++) {
					curCh = code.charAt(tmpPos);
					if (curCh != '\"')
						buf.append(curCh);
					else {
						endOfString = true;
						break;
					}
				}
				if (!endOfString)
					throw new LexerException("string quotes not closed at "
							+ curLine + " row: " + (curPos + 1));
				curPos += 2;
				curCharLine++;
				return new Token(buf.toString(), TokenType.TK_STRING, curLine,
						curCharLine++);
			}

			default:
				buf.append(curCh);
				char firstChar = curCh;
				for (int tmpPos = curPos + 1; tmpPos < code.length(); tmpPos++) {
					curCh = code.charAt(tmpPos);
					if ((curCh >= 'a' && curCh <= 'z')
							|| (curCh >= 'A' && curCh <= 'Z')
							|| (curCh >= '0' && curCh <= '9')) {
						buf.append(curCh);
					} else
						break;
				}
				if (firstChar == '$') {
					if (buf.length() == 1)
						throw new LexerException(
								"variable declaration expected at " + curLine
										+ " row: " + (curCharLine + 1));
					return new Token(buf.toString(), TokenType.TK_ID, curLine,
							curCharLine);
				} else {
					TokenType tokenType = ConstHolder.keywordsMap.get(buf
							.toString().toLowerCase());
					if (tokenType == null)
						tokenType = TokenType.TK_ID;
					return new Token(buf.toString(), tokenType, curLine,
							curCharLine);
				}
			}

		}
		return new Token(TokenType.TK_EOP.getName(), TokenType.TK_EOP, curLine,
				curCharLine);
	}

	public Token lex() throws LexerException {
		Token result = lexProcessor();

		curPos += result.getName().length();
		curCharLine += result.getName().length();

		return result;
	}

	private boolean hasNextChar() {
		return curPos + 1 != code.length();
	}

}
