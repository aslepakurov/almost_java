public class Token {
	private String name;
	private TokenType type;
	private int line;
	public Token(String name,  TokenType type, int line) {
		this.name = name;
		this.type = type;
		this.line = line;
	}
	public String toString(){
		return name+" "+type+" "+line;
	}
}
