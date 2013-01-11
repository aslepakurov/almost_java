package com.raxacoricofallapatorius.service;

public class VarDecl {
	private TokenType varType;
	private String name;
	public VarDecl(String name,TokenType type){
		this.name = name;
		this.varType = type;
	}
	public TokenType getVarType() {
		return varType;
	}
	public String getName() {
		return name;
	}
}
