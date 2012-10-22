import java.util.ArrayList;

public class LexicalAnalyzer {
	public int currentLine = 1;
	public int currentCharLine = 0;
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
			char currentChar;
			StringBuilder buf = new StringBuilder();
			for(int ch =0;ch<code.length();ch++) {
				currentChar = code.charAt(ch);
				boolean boolSep = false;
				for(int sepIndex=0;sepIndex<ConstHolder.separators.length;sepIndex++){
					Token word = null;
					Token separator = null;
					if(ConstHolder.separators[sepIndex].charAt(0)==currentChar){
						boolSep = true;
						boolean isKeyword = false;
						for(int keyIndex=0;keyIndex<ConstHolder.keywords.length && !buf.toString().isEmpty();keyIndex++){
							if(ConstHolder.keywords[keyIndex].equalsIgnoreCase(buf.toString())){
								isKeyword = true;
								word = new Token(buf.toString(),ConstHolder.ketwordsTK[keyIndex],currentLine,currentCharLine-buf.length());
							}
						}
						if(!isKeyword){
							word = new Token(buf.toString(),TokenType.TK_ID, currentLine, currentCharLine-buf.length());
						}
					separator = new Token((currentChar=='\n'?"NL":" "+currentChar), ConstHolder.separatorsTK[sepIndex], currentLine, currentCharLine);
					switch (currentChar) {
					case '\n':
						currentLine++;
						currentCharLine = 0;
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
				if(!boolSep)buf.append(currentChar);
				currentCharLine++;
			}
		
	}
	/**
	 * Method to read all text from quote to quote.
	 */
	public ArrayList<String> quoteRead(){
		
		char currentChar;
		String buf = "";
		ArrayList<String> text = new ArrayList<String>();
		boolean checkOnQuote = false;
		
		for(int ch = 0; ch<code.length(); ch++){
			currentChar = code.charAt(ch);
			
			if ((currentChar == '\"')&&(!checkOnQuote)){
				checkOnQuote = true;
				continue;
			}else if ((checkOnQuote)&&(currentChar == '\"')){
				checkOnQuote = false;
				text.add(buf);
				buf = "";
			}else if (checkOnQuote){
				buf+=currentChar;
			}
		}
		return text;
	}
}

