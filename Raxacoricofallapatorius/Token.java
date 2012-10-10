public class Token {
	public String name;
	public String attributes;
	public String type;
	public int line;
	public boolean uselessvar;
	public Token(String name, String attributes, String type,int line) {
		super();
		this.name = name;
		this.attributes = attributes;
		this.type = type;
		this.line = line;
		
	}
}
