package com.raxacoricofallapatorius.service;

import java.util.HashMap;

public class ConstHolder {
	// public static final HashMap<TokenType, String> separatorsMap = new
	// HashMap<TokenType, String>();
	public static final HashMap<String, TokenType> keywordsMap = new HashMap<String, TokenType>();

	static {
		/**
		 * // ----separators---- separatorsMap.put(TokenType.TK_S_QUOT, "\"");
		 * separatorsMap.put(TokenType.TK_S_LEFT_PARENT, "(");
		 * separatorsMap.put(TokenType.TK_S_RIGHT_PARENT, ")"); //
		 * separatorsMap.put(TokenType.TK_S_WS, " "); //
		 * separatorsMap.put(TokenType.TK_S_CARRET, "\n"); //
		 * separatorsMap.put(TokenType.TK_S_END, //
		 * System.getProperty("line.separator").toString()); //is it actually //
		 * working?? // separatorsMap.put(TokenType.TK_S_END, "\r");
		 * separatorsMap.put(TokenType.TK_S_LEFT_BRACE, "{");
		 * separatorsMap.put(TokenType.TK_S_RIGHT_BRACE, "}"); //
		 * separatorsMap.put(TokenType.TK_S_FASTAND, "||"); //
		 * separatorsMap.put(TokenType.TK_S_FASTOR, "&&");
		 * separatorsMap.put(TokenType.TK_S_NOT, "!");
		 * separatorsMap.put(TokenType.TK_S_MULTI, "*");
		 * separatorsMap.put(TokenType.TK_S_DIVIDE, "/");
		 * separatorsMap.put(TokenType.TK_S_ADD, "+");
		 * separatorsMap.put(TokenType.TK_S_SUBTRACT, "-");
		 */
		// ----keywords----
		keywordsMap.put(TokenType.TK_K_INT.getName(), TokenType.TK_K_INT);
		keywordsMap.put(TokenType.TK_K_FLOAT.getName(), TokenType.TK_K_FLOAT);
		keywordsMap.put(TokenType.TK_K_STR.getName(), TokenType.TK_K_STR);
		keywordsMap.put(TokenType.TK_K_VOID.getName(), TokenType.TK_K_VOID);
		keywordsMap.put(TokenType.TK_K_BOOL.getName(), TokenType.TK_K_BOOL);
		keywordsMap.put(TokenType.TK_K_TRUE.getName(), TokenType.TK_K_TRUE);
		keywordsMap.put(TokenType.TK_K_FALSE.getName(), TokenType.TK_K_FALSE);
		keywordsMap.put(TokenType.TK_K_FUNC.getName(), TokenType.TK_K_FUNC);
		keywordsMap.put(TokenType.TK_K_FOR.getName(), TokenType.TK_K_FOR);
		keywordsMap.put(TokenType.TK_K_WHILE.getName(), TokenType.TK_K_WHILE);
		keywordsMap.put(TokenType.TK_K_IF.getName(), TokenType.TK_K_IF);
		keywordsMap.put(TokenType.TK_K_ELSE.getName(), TokenType.TK_K_ELSE);
		keywordsMap.put(TokenType.TK_K_CONTINUE.getName(),
				TokenType.TK_K_CONTINUE);
		keywordsMap.put(TokenType.TK_K_BREAK.getName(), TokenType.TK_K_BREAK);
		keywordsMap.put(TokenType.TK_K_CONST.getName(), TokenType.TK_K_CONST);
		keywordsMap.put(TokenType.TK_K_DEF.getName(), TokenType.TK_K_DEF);
		keywordsMap.put(TokenType.TK_K_DIV.getName(), TokenType.TK_K_DIV);
	}

}
