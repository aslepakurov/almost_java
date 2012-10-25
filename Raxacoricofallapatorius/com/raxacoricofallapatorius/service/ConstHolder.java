package com.raxacoricofallapatorius.service;

import java.util.HashMap;

public class ConstHolder {
	/*
	 * public static final String separators[] = { "\"", "(", ")", " ", "\n",
	 * "{", "}", "||", "&&", "!", "*", "/", "+", "-" }; public static final
	 * String keywords[] = { "int", "float", "str", "void", "bool", "true",
	 * "false", "function", "for", "while", "if", "else", "continue", "break",
	 * "const", "def", "div" };
	 */
	public static final HashMap<TokenType, String> separatorsMap = new HashMap<TokenType, String>();
	public static final HashMap<TokenType, String> keywordsMap = new HashMap<TokenType, String>();

	static {
		// ----separators----
		separatorsMap.put(TokenType.TK_S_QUOT, "\"");
		separatorsMap.put(TokenType.TK_S_LEFT_PARENT, "(");
		separatorsMap.put(TokenType.TK_S_RIGHT_PARENT, ")");
		separatorsMap.put(TokenType.TK_S_WS, " ");
		separatorsMap.put(TokenType.TK_S_CARRET, "\n");
		separatorsMap.put(TokenType.TK_S_LEFT_BRACE, "{");
		separatorsMap.put(TokenType.TK_S_RIGHT_BRACE, "}");
		separatorsMap.put(TokenType.TK_S_FASTAND, "||");
		separatorsMap.put(TokenType.TK_S_FASTOR, "&&");
		separatorsMap.put(TokenType.TK_S_NOT, "!");
		separatorsMap.put(TokenType.TK_S_MULTI, "*");
		separatorsMap.put(TokenType.TK_S_DIVIDE, "/");
		separatorsMap.put(TokenType.TK_S_ADD, "+");
		separatorsMap.put(TokenType.TK_S_SUBTRACT, "-");
		// ----keywords----
		keywordsMap.put(TokenType.TK_K_FLOAT, "int");
		keywordsMap.put(TokenType.TK_K_STR, "str");
		keywordsMap.put(TokenType.TK_K_VOID, "void");
		keywordsMap.put(TokenType.TK_K_BOOL, "bool");
		keywordsMap.put(TokenType.TK_K_TRUE, "true");
		keywordsMap.put(TokenType.TK_K_FALSE, "false");
		keywordsMap.put(TokenType.TK_K_FUNC, "function");
		keywordsMap.put(TokenType.TK_K_FOR, "for");
		keywordsMap.put(TokenType.TK_K_WHILE, "while");
		keywordsMap.put(TokenType.TK_K_IF, "if");
		keywordsMap.put(TokenType.TK_K_ELSE, "else");
		keywordsMap.put(TokenType.TK_K_CONT, "continue");
		keywordsMap.put(TokenType.TK_K_BREAK, "break");
		keywordsMap.put(TokenType.TK_K_CONST, "const");
		keywordsMap.put(TokenType.TK_K_DEF, "def");
		keywordsMap.put(TokenType.TK_K_DIV, "div");
	}
	/*
	 * public static final TokenType separatorsTK[] = { TokenType.TK_S_QUOT,
	 * TokenType.TK_S_LEFT_PARENT, TokenType.TK_S_RIGHT_PARENT,
	 * TokenType.TK_S_WS, TokenType.TK_S_CARRET, TokenType.TK_S_LEFT_BRACE,
	 * TokenType.TK_S_RIGHT_BRACE, TokenType.TK_S_FASTAND,
	 * TokenType.TK_S_FASTOR, TokenType.TK_S_NOT, TokenType.TK_S_MULTI,
	 * TokenType.TK_S_DIVIDE, TokenType.TK_S_ADD, TokenType.TK_S_SUBTRACT };
	 * public static final TokenType ketwordsTK[] = { TokenType.TK_K_INT,
	 * TokenType.TK_K_FLOAT, TokenType.TK_K_STR, TokenType.TK_K_VOID,
	 * TokenType.TK_K_BOOL, TokenType.TK_K_TRUE, TokenType.TK_K_FALSE,
	 * TokenType.TK_K_FUNC, TokenType.TK_K_FOR, TokenType.TK_K_WHILE,
	 * TokenType.TK_K_IF, TokenType.TK_K_ELSE, TokenType.TK_K_CONT,
	 * TokenType.TK_K_BREAK, TokenType.TK_K_CONST, TokenType.TK_K_DEF,
	 * TokenType.TK_K_DIV };
	 */
}
