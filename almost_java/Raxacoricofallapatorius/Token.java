public class Token {
	private String name;
	private String attributes;
	private String type;
	private int line;

	public Token(String name, String attributes, String type, int line) {
		this.name = name;
		this.attributes = attributes;
		this.type = type;
		this.line = line;
	}
}
