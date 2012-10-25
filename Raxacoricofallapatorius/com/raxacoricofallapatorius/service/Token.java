package com.raxacoricofallapatorius.service;

public class Token {
	private String name;
	private TokenType type;
	private int line;
	private int column;

	// private String semanticM;
	public Token(String name, TokenType type, int line, int column) {
		this.name = name;
		this.type = type;
		this.line = line;
		this.column = column;
		// this.semanticM = semanticM;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public String toString() {
		return name + " " + type + " " + line + " " + column + " ";
	}
}
