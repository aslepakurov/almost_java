package com.raxacoricofallapatorius.run;

//import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
import com.raxacoricofallapatorius.service.LexerException;
import com.raxacoricofallapatorius.service.Token;

//package com.raxacoricofallapatorius.run;
//
//import java.util.ArrayList;
//
//import com.raxacoricofallapatorius.analyzer.LexicalAnalyzer;
//import com.raxacoricofallapatorius.service.ParseException;
//import com.raxacoricofallapatorius.service.Token;
//
public class TestClass {
	public static void main(String[] args) {
		LexicalAnalyzer l = new LexicalAnalyzer("{} 12.");
		Token token = null;
		try {
			do {
				// try {
				token = l.lex();
				System.out.println("token = " + token);
				// } catch (LexerException e) {

				// break;
				// }
			} while (token != null);
		} catch (LexerException e) {
			System.out.println("Error : " + e);
		}
	}
}
// System.out.println("~~~~~~~~~~~~~~~~first test~~~~~~~~~~~~~~~~");
// LexicalAnalyzer la = new LexicalAnalyzer(
// "p for drew while serega true vlad\nawesome(var{break else paper if somethin}mouse/int 4 float 4.5 str \"sss\" pampam)");
// ArrayList<Token> tokenlist = null;
// try {
// la.parse();
// tokenlist = la.getTokens();
// for (Token token : tokenlist) {
// System.out.println(token.toString());
// }
// } catch (ParseException e) {
// System.out.println(e);
// }
//
// System.out.println("~~~~~~~~~~~~~~~~second test~~~~~~~~~~~~~~~~");
// la = new LexicalAnalyzer(
// "void\"123 if\" \"true_\"\"breakdown\" \nmake total insane \"\"\n void \"\"");
// try {
// la.parse();
// tokenlist = la.getTokens();
// for (Token token : tokenlist) {
// System.out.println(token.toString());
// }
// } catch (ParseException e) {
// System.out.println(e);
// }
//
// System.out.println("------------------------");
//
//
// System.out.println("~~~~~~~~~~~~~~~~Third test~~~~~~~~~~~~~~~~");
// la = new LexicalAnalyzer("int 42 float 3.14 tt 13\n");
// try {
// la.parse();
// tokenlist = la.getTokens();
// for (Token token : tokenlist) {
// System.out.println(token.toString());
// }
// } catch (ParseException e) {
// System.out.println(e);
// }
// }
// }
