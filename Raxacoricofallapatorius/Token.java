public class Token {
	public String name;
	public String attributes;
	public String type;
	public int line;
	public Token(String name, String attributes, String type,int line) {
		this.name = name;
		this.attributes = attributes;
		this.type = type;
		this.line = line;
		
	}
}
