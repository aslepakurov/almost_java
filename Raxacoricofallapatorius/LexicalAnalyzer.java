import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curChar = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String code = null;
	private Iterator<Token> tokenIterator;

	/**
	 * Consructor, kep :)
	 * 
	 * @param code
	 *            source code
	 */
	public LexicalAnalyzer(String code) {
		this.code = code;
	}
	/**
	 * For syntax analyzer
	 * @see SyntaxAnalyzer (some of this days...some og this days)
	 * @return token iterator, kinda obvious
	 */
	public Iterator<Token> initializeIterator(){
		tokenIterator = tokens.iterator();
		return tokenIterator;
	}
	/**
	 * For syntax analyzer
	 * @see SyntaxAnalyzer (some of this days...some og this days)
	 * @return next token
	 */
	public Token nextToken(){
		if(hasNextToken()) return tokenIterator.next();
		else return null;
	}
	/**
	 * For syntax analyzer
	 * @see SyntaxAnalyzer (some of this days...some og this days)
	 * @return either next token exists
	 */
	public boolean hasNextToken(){
		return tokenIterator.hasNext();
	}
	/**
	 * method for parsing source code
	 * 
	 * i think this method must be in separated thread
	 */
	public void parse() {
		BufferedReader bf;
		try {
			// if file is in directory - use file. This is an pre-pre-pre-pre
			// ... -pre alpha moment, that will be replaced by some serious
			// stuff, like cyborgs, or sharks with AK-47's or a dino with a
			// rocket launcher ^^
			bf = new BufferedReader(new StringReader(code));
			// String buf; // wierdo buffer. Cosmic power told me that I need it
			// Just need it, like ice-cream of fish fingers with custard
			char ch;
			while (bf.ready()) {
				curChar++;
				ch = (char) bf.read();
				switch (ch) {
				//sense don't need we this
//				case 'a':
//				case 'b':
//				case 'c':
//				case 'd':
//				case 'e':
//				case 'f':
//				case 'g':
//				case 'h':
//				case 'i':
//				case 'j':
//				case 'k':
//				case 'l':
//				case 'm':
//				case 'n':
//				case 'o':
//				case 'p':
//				case 'q':
//				case 'r':
//				case 's':
//				case 't':
//				case 'u':
//				case 'v':
//				case 'w':
//				case 'x':
//				case 'y':
//				case 'z':
				case '\n': {
					curLine++;
					curChar = 0;
					break;
				}
				case ' ': {

					break;
				}
				}
			}

			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.printf("Problem at %d,%d", curLine, curChar);
		}
	}

}
