public class ConstHolder {
	public static final String separators[] = { "\"", "(", ")", " ", "\n", "{",
			"}","||","&&","!","*","/","+","-"};
	public static final String keywords[] = { "int", "float", "str", "void",
			"bool", "true", "false", "function", "for", "while",
			"if", "else", "continue", "break", "const", "def", "div" };
	public static final TokenType separatorsTK[] = {TokenType.TK_S_QUOT,TokenType.TK_S_LEFT_PARENT,TokenType.TK_S_RIGHT_PARENT,TokenType.TK_S_WS,TokenType.TK_S_CARRET,
		TokenType.TK_S_LEFT_BRACE,TokenType.TK_S_RIGHT_BRACE,TokenType.TK_S_FASTAND,TokenType.TK_S_FASTOR,TokenType.TK_S_NOT,
		TokenType.TK_S_MULTI,TokenType.TK_S_DIVIDE,TokenType.TK_S_ADD,TokenType.TK_S_SUBTRACT};
	public static final TokenType ketwordsTK[] = {TokenType.TK_K_INT,TokenType.TK_K_FLOAT,TokenType.TK_K_STR,TokenType.TK_K_VOID,TokenType.TK_K_BOOL,TokenType.TK_K_TRUE,
		TokenType.TK_K_FALSE,TokenType.TK_K_FUNC,TokenType.TK_K_FOR,TokenType.TK_K_WHILE,TokenType.TK_K_IF,
		TokenType.TK_K_ELSE,TokenType.TK_K_CONT,TokenType.TK_K_BREAK,TokenType.TK_K_CONST,TokenType.TK_K_DEF,TokenType.TK_K_DIV};
}
