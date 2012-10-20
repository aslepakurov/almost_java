import java.util.ArrayList;


public class TestClass {
	public static void main(String[] args) {
		LexicalAnalyzer la = new LexicalAnalyzer("p for drew while serega true vlad\nawesome(var{break else paper if somethin}mouse/int 4 float 4.5 str \"sss\" pampam)");
		la.parse();
		ArrayList<Token> tokenlist = la.getTokens();
		for(Token token: tokenlist){
			System.out.println(token.toString());
		}
	}
}
