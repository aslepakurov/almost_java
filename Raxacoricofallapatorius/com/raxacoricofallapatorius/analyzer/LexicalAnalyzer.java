package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curCharLine = 0;
	public int curCh = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String code = null;
	private int code_size = code.length();

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
	}

	/**
	 * Token getter
	 * 
	 * @return Arraylist of Tokens
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}

	// public void parse() throws ParseException {
	// char ch;
	// StringBuilder buf = new StringBuilder();
	// boolean checkOnQuote = false;
	// boolean isKeyword = false;
	// boolean boolSep = false;
	// TokenType nextSeparator = null;
	// TokenType nextKeyword = null;
	// for (int curCh = 0; curCh < code.length(); curCh++) {
	// ch = code.charAt(curCh);
	// boolSep = false;
	//
	// if ((ch == '\"') && (!checkOnQuote)) {
	//
	// checkOnQuote = true;
	//
	// addToken(new Token("\"", TokenType.TK_S_QUOT, curLine,
	// curCharLine++));
	// continue;
	// } else if ((checkOnQuote) && (ch == '\"')) {
	// checkOnQuote = false;
	//
	// addToken(new Token(buf.toString(), TokenType.TK_STRING,
	// curLine, curCharLine - buf.length()));
	// addToken(new Token("\"", TokenType.TK_S_QUOT, curLine,
	// curCharLine++));
	// buf = new StringBuilder();
	// continue;
	// } else if (checkOnQuote) {
	// curCharLine++;
	// if (ch == '\n') {
	// curLine++;
	// curCharLine = 0;
	// }
	// buf.append(ch);
	// continue;
	// }
	// Set<TokenType> separatorsType = separatorsMap.keySet();
	// Iterator<TokenType> separatorsIterator = separatorsType.iterator();
	//
	// // for (int sepIndex = 0; sepIndex <
	// // ConstHolder.separatorsMap.size(); sepIndex++) {
	// while (separatorsIterator.hasNext()) {
	//
	// nextSeparator = separatorsIterator.next();
	// Token word = null;
	// Token separator = null;
	// // if (ConstHolder.separators[sepIndex].charAt(0) == ch) {
	// if (separatorsMap.get(nextSeparator).charAt(0) == ch) {
	// boolSep = true;
	// isKeyword = false;
	// Set<TokenType> keywordsSet = keywordsMap
	// .keySet();
	// Iterator<TokenType> keywordsIterator = keywordsSet
	// .iterator();
	// // for (int keyIndex = 0; keyIndex < ConstHolder.keywordsMap
	// // .size() && !buf.toString().isEmpty(); keyIndex++) {
	// while (keywordsIterator.hasNext()
	// && !buf.toString().isEmpty()) {
	// nextKeyword = keywordsIterator.next();
	// if (keywordsMap.get(nextKeyword)
	// .equalsIgnoreCase(buf.toString())) {
	// isKeyword = true;
	// word = new Token(buf.toString(), nextKeyword,
	// curLine, curCharLine - buf.length());
	// }
	// }
	// if (!isKeyword) {
	// word = new Token(buf.toString(), TokenType.TK_ID,
	// curLine, curCharLine - buf.length());
	// }
	// separator = new Token((ch == '\n' ? "NL" : " " + ch),
	// nextSeparator, curLine, curCharLine);
	// switch (ch) {
	// case '\n': {
	// curLine++;
	// curCharLine = -1;
	// break;
	// }
	//
	// default:
	// break;
	// }
	// }
	// if (word != null && separator != null) {
	// addToken(word);
	// addToken(separator);
	// buf = new StringBuilder();
	// break;
	// }
	// }
	// if (!boolSep)
	// buf.append(ch);
	// curCharLine++;
	// }
	// }

	// private void addToken(Token token) throws LexerException {
	// if (token.getColumn() < lastColumn && token.getLine() <= lastLine)
	// throw new LexerException("Error in statement at line "
	// + token.getLine() + ", column " + (token.getColumn() - 1));
	// lastLine = token.getLine();
	// lastColumn = token.getColumn();
	// boolean notAnInt = false;
	// boolean notAFloat = false;
	// if (token.getType() == TokenType.TK_ID) {
	//
	// try {
	// Float.parseFloat(token.getName());
	// } catch (NumberFormatException e) {
	// notAFloat = true;
	// }
	// try {
	// Integer.parseInt(token.getName());
	// } catch (NumberFormatException e) {
	// notAnInt = true;
	// }
	// if (notAnInt && !notAFloat) {
	// token.setType(TokenType.TK_FLOAT);
	// } else if (!notAnInt) {
	// token.setType(TokenType.TK_INT);
	// }
	// }
	// tokens.add(token);
	// }

	public Token lex() {
		boolean tokenRead = false;
		StringBuilder buf = new StringBuilder();
		//true or die ???? 
		
		for (; tokenRead; curCh++, curCharLine++) {
			if (curCh > code_size)
				return null;
			switch (curCh) {

			// -------ignored separators---------

			case ' ':
				continue;
			case '\n':
				curCharLine = 0;
				curLine++;
				continue;
				// add ignored separators if need

				// ------tokenized separators--------

			case '(':
				return new Token(TokenType.TK_S_LEFT_PARENT.toString(),
						TokenType.TK_S_LEFT_PARENT, curLine, curCharLine);
			case ')':
				return new Token(TokenType.TK_S_RIGHT_PARENT.toString(),
						TokenType.TK_S_RIGHT_PARENT, curLine, curCharLine);
			case '{':
				return new Token(TokenType.TK_S_LEFT_BRACE.toString(),
						TokenType.TK_S_LEFT_BRACE, curLine, curCharLine);
			case '}':
				return new Token(TokenType.TK_S_RIGHT_BRACE.toString(),
						TokenType.TK_S_RIGHT_BRACE, curLine, curCharLine);
			case '&':
				return new Token(TokenType.TK_S_AND.toString(),
						TokenType.TK_S_AND, curLine, curCharLine);
			case '|':
				return new Token(TokenType.TK_S_OR.toString(),
						TokenType.TK_S_OR, curLine, curCharLine);
			case '*':
				return new Token(TokenType.TK_S_MULTI.toString(),
						TokenType.TK_S_MULTI, curLine, curCharLine);
			case '/':
				return new Token(TokenType.TK_S_DIVIDE.toString(),
						TokenType.TK_S_DIVIDE, curLine, curCharLine);
			case '+':
				return new Token(TokenType.TK_S_ADD.toString(),
						TokenType.TK_S_ADD, curLine, curCharLine);
			case '-':
				return new Token(TokenType.TK_S_SUBTRACT.toString(),
						TokenType.TK_S_SUBTRACT, curLine, curCharLine);

				// -------Ch x 2-----------

			case '!':
				if (code.charAt(curCh+1) == '=')
					return new Token(TokenType.TK_S_NOT.toString(),
							TokenType.TK_S_NOT, curLine, curCharLine);
				else
					new LexerException("= expected on line: " + curLine
							+ " row: " + (curCharLine + 1));
			case '=':
				if (code.charAt(curCh+1) == '=')
					return new Token(TokenType.TK_S_EQUAL.toString(),
							TokenType.TK_S_EQUAL, curLine, curCharLine);
				else
					new LexerException("= expected on line: " + curLine
							+ " row: " + (curCharLine + 1));
			case '<':
				if (code.charAt(curCh+1) == '=')
					return new Token(TokenType.TK_S_EQUAL_OR_LESS.toString(),
							TokenType.TK_S_EQUAL_OR_LESS, curLine, curCharLine);
				else
					return new Token(TokenType.TK_S_LESS.toString(),
							TokenType.TK_S_LESS, curLine, curCharLine);
			case '>':
				if (code.charAt(curCh+1) == '=')
					return new Token(
							TokenType.TK_S_EQUAL_OR_GREATER.toString(),
							TokenType.TK_S_EQUAL_OR_GREATER, curLine,
							curCharLine);
				return new Token(TokenType.TK_S_GREATER.toString(),
						TokenType.TK_S_GREATER, curLine, curCharLine);

				// -------Numeric -----------
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				if ((code.charAt(curCh+1)) < '0' && (code.charAt(curCh+1)) > '9' && (code.charAt(curCh+1) != '.')) {
					if(buf.toString().contains(".")) return new Token(buf.toString(), TokenType.TK_FLOAT, curLine, curCharLine);
					else return new Token(buf.toString(), TokenType.TK_INT, curLine, curCharLine);
				}else{
					buf.append(curCh);
				}
				
			//---------Dealing with strings--------
				
				
			case '\"':
				//Vlad it's your time
			default:
				buf.append(curCh);
				if((code.charAt(curCh+1)<'A')&&(code.charAt(curCh+1)>'z')&&((code.charAt(curCh+1)>'Z')&(code.charAt(curCh+1)<95))||(code.charAt(curCh+1)!='$')){
					
				}
			}	
		}
		return null;
	}

	/**
	 * Temporary lex() driver As an example of what sintaxanalyzer would do
	 */
	public void run_lex() {
		Token token = null;
		do {
			token = lex();
			if (token != null)
				tokens.add(token);
		} while (token != null);
	}
}
