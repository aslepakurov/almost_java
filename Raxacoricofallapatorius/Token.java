public class Token {
	private String name;
	private TokenType type;
	private int line;
	private int column;
//	private String semanticM;
	
	public Token(String name,  TokenType type, int line, int column) {
		this.name = name;
		this.type = type;
		this.line = line;
		this.column = column;
//		this.semanticM = semanticM;
	}
	
	public String toString(){
		return "Name: "+name+"; Type: "+type+"; Line: "+line+"; Colomn: "+column+";";
	}
}
