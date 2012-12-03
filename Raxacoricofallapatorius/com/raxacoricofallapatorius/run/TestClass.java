package com.raxacoricofallapatorius.run;

import java.util.ArrayList;

import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
import com.raxacoricofallapatorius.analyzer.SyntaxAnalyzer;
import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class TestClass {
	public static void main(String[] args) {
		ArrayList<Token> tokens = new ArrayList<Token>();
		LexicalAnalyzer l = new LexicalAnalyzer(
				"func int main(int i, bool f,str s){" +
						"call main(t,e,w);" +
				"}");
		Token token = null;
		try {
			do {
				token = l.lex();
				tokens.add(token);
//				System.out.println("token = " + token);
			} while (token.getType() != TokenType.TK_EOP);
		} catch (LexerException e) {
			System.out.println("Error : " + e);
		}
		Token[] tok = new Token[tokens.size()];
		for(int i=0;i<tok.length;i++){
			tok[i]=tokens.get(i);
		}
		SyntaxAnalyzer sa = new SyntaxAnalyzer(tok);
		try {
			Function[] functions = sa.parse();
		} catch (SyntaxException e) {
			System.out.println("Error : " + e);
		}
	}
}
