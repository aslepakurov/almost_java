package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.raxacoricofallapatorius.service.ConstHolder;
import com.raxacoricofallapatorius.service.ParseException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curCharLine = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String code = null;
	private int lastLine = 0;
	private int lastColumn = 0;
	

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

	/**
	 * method for parsing source code
	 * 
	 * i think this method must be in separated thread
	 */
	public void parse() throws ParseException {
		// if file is in directory - use file. This is an pre-pre-pre-pre
		// ... -pre alpha moment, that will be replaced by some serious
		// stuff, like cyborgs, or sharks with AK-47's or a dino with a
		// rocket launcher ^^
		// wierdo buffer. Cosmic power told me that I need it
		// Just need it, like ice-cream of fish fingers with custard
		char ch;
		StringBuilder buf = new StringBuilder();
		boolean checkOnQuote = false;
		boolean isKeyword = false;
		boolean boolSep = false;
		TokenType nextSeparator = null;
		TokenType nextKeyword = null;
		for (int curCh = 0; curCh < code.length(); curCh++) {
			ch = code.charAt(curCh);
			boolSep = false;

			if ((ch == '\"') && (!checkOnQuote)) {

				checkOnQuote = true;

				addToken(new Token("\"", TokenType.TK_S_QUOT, curLine,
						curCharLine++));
				continue;
			} else if ((checkOnQuote) && (ch == '\"')) {
				checkOnQuote = false;

				addToken(new Token(buf.toString(), TokenType.TK_STRING,
						curLine, curCharLine - buf.length()));
				addToken(new Token("\"", TokenType.TK_S_QUOT, curLine,
						curCharLine++));
				buf = new StringBuilder();
				continue;
			} else if (checkOnQuote) {
				curCharLine++;
				if (ch == '\n') {
					curLine++;
					curCharLine = 0;
				}
				buf.append(ch);
				continue;
			}
			Set<TokenType> separatorsType = ConstHolder.separatorsMap.keySet();
			Iterator<TokenType> separatorsIterator = separatorsType.iterator();

			// for (int sepIndex = 0; sepIndex <
			// ConstHolder.separatorsMap.size(); sepIndex++) {
			while (separatorsIterator.hasNext()) {

				nextSeparator = separatorsIterator.next();
				Token word = null;
				Token separator = null;
				// if (ConstHolder.separators[sepIndex].charAt(0) == ch) {
				if (ConstHolder.separatorsMap.get(nextSeparator).charAt(0) == ch) {
					boolSep = true;
					isKeyword = false;
					Set<TokenType> keywordsSet = ConstHolder.keywordsMap
							.keySet();
					Iterator<TokenType> keywordsIterator = keywordsSet
							.iterator();
					// for (int keyIndex = 0; keyIndex < ConstHolder.keywordsMap
					// .size() && !buf.toString().isEmpty(); keyIndex++) {
					while (keywordsIterator.hasNext()
							&& !buf.toString().isEmpty()) {
						nextKeyword = keywordsIterator.next();
						if (ConstHolder.keywordsMap.get(nextKeyword)
								.equalsIgnoreCase(buf.toString())) {
							isKeyword = true;
							word = new Token(buf.toString(), nextKeyword,
									curLine, curCharLine - buf.length());
						}
					}
					if (!isKeyword) {
						word = new Token(buf.toString(), TokenType.TK_ID,
								curLine, curCharLine - buf.length());
					}
					separator = new Token((ch == '\n' ? "NL" : " " + ch),
							nextSeparator, curLine, curCharLine);
					switch (ch) {
					case '\n': {
						curLine++;
						curCharLine = -1;
						break;
					}

					default:
						break;
					}
				}
				if (word != null && separator != null) {
					addToken(word);
					addToken(separator);
					buf = new StringBuilder();
					break;
				}
			}
			if (!boolSep)
				buf.append(ch);
			curCharLine++;
		}
	}

	private void addToken(Token token) throws ParseException {
		if (token.getColumn() < lastColumn && token.getLine() <= lastLine)
			throw new ParseException("Error in statement at line "
					+ token.getLine() + ", column " + (token.getColumn() - 1));
		lastLine = token.getLine();
		lastColumn = token.getColumn();
		boolean notAnInt = false;
		boolean notAFloat = false;
		if(token.getType() == TokenType.TK_ID){

			try {
				float floatNum = Float.parseFloat(token.getName());
			} catch (NumberFormatException e) {
				notAFloat = true;
			}
			try {
				int intNum = Integer.parseInt(token.getName());
			} catch (NumberFormatException e) {
				notAnInt = true;
			}
			if(notAnInt && !notAFloat){
				token.setType(TokenType.TK_FLOAT);
			}else if(!notAnInt){
				token.setType(TokenType.TK_INT);
			}
		}
		tokens.add(token);
	}

	/**
	 * Method to read all text from quote to quote.
	 */
	public ArrayList<Token> quoteRead() {

		char currentChar;
		StringBuilder buf = new StringBuilder();
		ArrayList<Token> text = new ArrayList<Token>();
		boolean checkOnQuote = false;

		for (int ch = 0; ch < code.length(); ch++) {
			currentChar = code.charAt(ch);

			if ((currentChar == '\"') && (!checkOnQuote)) {
				checkOnQuote = true;
				continue;
			} else if ((checkOnQuote) && (currentChar == '\"')) {
				checkOnQuote = false;
				text.add(new Token(buf.toString(), TokenType.TK_STRING,
						0/* temporary_not_implemented */, 0/* temporary_not_implemented */));
				buf = new StringBuilder();
			} else if (checkOnQuote) {
				buf.append(currentChar);
			}
		}
		return text;
	}

	public ArrayList<String> alternativeQuoteRead() {
		ArrayList<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\"([\\w\\s])*\"");
		Matcher m = pattern.matcher(code);
		while (m.find()) {
			String found = m.group();
			result.add(found.substring(1, found.length() - 1));
		}
		return result;
	}
}
