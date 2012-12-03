package com.raxacoricofallapatorius.service;
public class Junk {}
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
//}
////LocalVarStatement localVarStatement = new LocalVarStatement();
////if (look().getType() == TokenType.TK_K_DEF) {
////	consume();
////	if (look().getType() != TokenType.TK_S_LEFT_BRACE)
////		throw new SyntaxException("'{' expected at " + look().getLine()
////				+ ":" + look().getColumn());
////	consume();
////	localVarStatement = parseLocalVariables();
////}
//
//
///**
// * parse function's local variable declarations method
// * 
// * @return object of function's local declarations class
// * @throws SyntaxException
// */
//private LocalVarStatement parseLocalVariables() throws SyntaxException {
//	ArrayList<Token> types = new ArrayList<Token>();
//	ArrayList<Token> ids = new ArrayList<Token>();
//	ArrayList<Token> initValues = new ArrayList<Token>();
//	TokenType type;
//	while (true) {
//		if ((type = look().getType()) == TokenType.TK_S_RIGHT_BRACE) {
//			Token[] tokens = new Token[types.size()];
//			return new LocalVarStatement(types.toArray(tokens),
//					ids.toArray(tokens), initValues.toArray(tokens));
//		}
//		if (type != TokenType.TK_K_INT || type != TokenType.TK_K_FLOAT
//				|| type != TokenType.TK_K_STR
//				|| type != TokenType.TK_K_BOOL)
//			throw new SyntaxException("Variable type expected at "
//					+ look().getLine() + ":" + look().getColumn());
//		types.add(look());
//		consume();
//		if (look().getType() != TokenType.TK_ID)
//			throw new SyntaxException("Variable id expected at "
//					+ look().getLine() + ":" + look().getColumn());
//		ids.add(look());
//		consume();
//		if (look().getType() == TokenType.TK_S_SEMICOLON) {
//			consume();
//			continue;
//		}
//		if (look().getType() != TokenType.TK_S_INIT)
//			throw new SyntaxException("';' expected at " + look().getLine()
//					+ ":" + look().getColumn());
//		consume();
//		type = look().getType();
//		if (type != TokenType.TK_STRING || type != TokenType.TK_INT
//				|| type != TokenType.TK_FLOAT
//				|| type != TokenType.TK_K_TRUE
//				|| type != TokenType.TK_K_FALSE)
//			throw new SyntaxException(
//					"Variable initialization expected at "
//							+ look().getLine() + ":" + look().getColumn());
//		initValues.add(look());
//	}
//}
//}
//System.out.println("~~~~~~~~~~~~~~~~first test~~~~~~~~~~~~~~~~");
//LexicalAnalyzer la = new LexicalAnalyzer(
//"p for drew while serega true vlad\nawesome(var{break else paper if somethin}mouse/int 4 float 4.5 str \"sss\" pampam)");
//ArrayList<Token> tokenlist = null;
//try {
//la.parse();
//tokenlist = la.getTokens();
//for (Token token : tokenlist) {
//System.out.println(token.toString());
//}
//} catch (ParseException e) {
//System.out.println(e);
//}
//
//System.out.println("~~~~~~~~~~~~~~~~second test~~~~~~~~~~~~~~~~");
//la = new LexicalAnalyzer(
//"void\"123 if\" \"true_\"\"breakdown\" \nmake total insane \"\"\n void \"\"");
//try {
//la.parse();
//tokenlist = la.getTokens();
//for (Token token : tokenlist) {
//System.out.println(token.toString());
//}
//} catch (ParseException e) {
//System.out.println(e);
//}
//
//System.out.println("------------------------");
//
//
//System.out.println("~~~~~~~~~~~~~~~~Third test~~~~~~~~~~~~~~~~");
//la = new LexicalAnalyzer("int 42 float 3.14 tt 13\n");
//try {
//la.parse();
//tokenlist = la.getTokens();
//for (Token token : tokenlist) {
//System.out.println(token.toString());
//}
//} catch (ParseException e) {
//System.out.println(e);
//}
//}
//}