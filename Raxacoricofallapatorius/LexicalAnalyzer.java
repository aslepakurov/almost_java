import java.util.ArrayList;

public class LexicalAnalyzer {
	public int curLine = 1;
	public int curCharLine = 0;
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String code = null;
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
	 * Token getter
	 * @return Arraylist of Tokens
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	/**
	 * method for parsing source code
	 * 
	 * i think this method must be in separated thread
	 */
	public void parse() {
			// if file is in directory - use file. This is an pre-pre-pre-pre
			// ... -pre alpha moment, that will be replaced by some serious
			// stuff, like cyborgs, or sharks with AK-47's or a dino with a
			// rocket launcher ^^
			// wierdo buffer. Cosmic power told me that I need it
			// Just need it, like ice-cream of fish fingers with custard
			char ch;
			StringBuilder buf = new StringBuilder(); 
			for(int curCh =0;curCh<code.length();curCh++) {
				ch = code.charAt(curCh);
				boolean boolSep = false;
				for(int sepIndex=0;sepIndex<ConstHolder.separators.length;sepIndex++){
					Token word = null;
					Token separator = null;
					if(ConstHolder.separators[sepIndex].charAt(0)==ch){
						boolSep = true;
						boolean isKeyword = false;
						for(int keyIndex=0;keyIndex<ConstHolder.keywords.length && !buf.toString.isEmpty;keyIndex++){
							if(ConstHolder.keywords[keyIndex].equalsIgnoreCase(buf.toString())){
								isKeyword = true;
								word = new Token(buf.toString(),ConstHolder.ketwordsTK[keyIndex],curLine,curCharLine-buf.length());
							}
						}
						if(!isKeyword){
							word = new Token(buf.toString(),TokenType.TK_ID, curLine, curCharLine-buf.length());
						}
					separator = new Token((ch=='\n'?"NL":" "+ch), ConstHolder.separatorsTK[sepIndex], curLine, curCharLine);
					switch (ch) {
					case '\n':
						curLine++;
						curCharLine = 0;
						break;

						default:
							break;
						}
					}
					if(word!=null && separator!=null){
						tokens.add(word);
						tokens.add(separator);
						buf = new StringBuilder();
						break;
					}
				}
				if(!boolSep)buf.append(ch);
				curCharLine++;
	}
	}
}

