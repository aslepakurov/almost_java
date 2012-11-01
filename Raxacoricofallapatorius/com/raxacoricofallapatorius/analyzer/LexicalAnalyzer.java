package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

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

	public Token lex() {
		boolean tokenRead = false;
		StringBuilder buf = new StringBuilder();
		//true or die ????  cause never changed
		
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
					if(buf.toString().equalsIgnoreCase("int")) return new Token(buf.toString(),TokenType.TK_K_INT,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("float")) return new Token(buf.toString(),TokenType.TK_K_FLOAT,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("str")) return new Token(buf.toString(),TokenType.TK_K_STR,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("void")) return new Token(buf.toString(),TokenType.TK_K_VOID,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("bool")) return new Token(buf.toString(),TokenType.TK_K_BOOL,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("true")) return new Token(buf.toString(),TokenType.TK_K_TRUE,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("false")) return new Token(buf.toString(),TokenType.TK_K_FALSE,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("func")) return new Token(buf.toString(),TokenType.TK_K_FUNC,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("for")) return new Token(buf.toString(),TokenType.TK_K_FOR,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("while")) return new Token(buf.toString(),TokenType.TK_K_WHILE,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("if")) return new Token(buf.toString(),TokenType.TK_K_IF,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("else")) return new Token(buf.toString(),TokenType.TK_K_ELSE,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("continue")) return new Token(buf.toString(),TokenType.TK_K_CONTINUE,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("break")) return new Token(buf.toString(),TokenType.TK_K_BREAK,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("const")) return new Token(buf.toString(),TokenType.TK_K_CONST,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("def")) return new Token(buf.toString(),TokenType.TK_K_DEF,curLine,curCharLine);
					if(buf.toString().equalsIgnoreCase("div")) return new Token(buf.toString(),TokenType.TK_K_DIV,curLine,curCharLine);
					//do we need more keywords????
					return new Token(buf.toString(),TokenType.TK_ID,curLine,curCharLine);
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
