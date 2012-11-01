package com.raxacoricofallapatorius.service;


public class Junk {
//	/**
//	 * Token getter
//	 * 
//	 * @return Arraylist of Tokens
//	 */
//	public ArrayList<Token> getTokens() {
//		return tokens;
//	}

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
}
