package com.raxacoricofallapatorius.service;

import java.util.ArrayList;

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
	
	public void setType(TokenType type) {
		this.type = type;
	}

	public TokenType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + " " + type + " " + line + " " + column + " ";
	}
	
	public boolean equals(Token t){
		return t.getType().equals(getType()) && column ==t.getColumn() && line==t.getLine() && name.trim().equals(t.getName().trim());
	}
	
	public static boolean arrEquals(ArrayList<Token> a1,ArrayList<Token> a2){
		if(a1.size()!=a2.size()) return false;
		for(int i=0; i<a1.size();i++){
			if(!a1.get(i).equals(a2.get(i))) return false; 
		}
		return true;
	}
}
