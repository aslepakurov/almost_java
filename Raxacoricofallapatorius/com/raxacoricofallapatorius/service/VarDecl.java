package com.raxacoricofallapatorius.service;

public class VarDecl {
	private TokenType varType;
	private String name;
	public VarDecl(){
	}
	public VarDecl(String name,TokenType type){
		this.name = name;
		this.varType = type;
	}
}
