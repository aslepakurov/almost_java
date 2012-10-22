package com.raxacoricofallapatorius.run;
import java.util.ArrayList;

import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
import com.raxacoricofallapatorius.service.Token;
public class TestClass {
	public static void main(String[] args) {
		LexicalAnalyzer la = new LexicalAnalyzer("p for drew while serega true vlad\nawesome(var{break else paper if somethin}mouse/int 4 float 4.5 str \"sss\" pampam)");
		la.parse();
		ArrayList<Token> tokenlist = la.getTokens();
		for(Token token: tokenlist){
			System.out.println(token.toString());
		}
			
//		Read from quote to quote:
		la = new LexicalAnalyzer("\"123 if\" \"true_\"\"breakdown\" make total insane void");
		
		ArrayList<String> rslt = la.quoteRead();
		for(int i = 0; i<rslt.size(); i++){
			System.out.println(rslt.get(i));
		}
	}
}
