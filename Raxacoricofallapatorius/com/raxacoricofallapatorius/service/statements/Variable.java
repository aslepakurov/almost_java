package com.raxacoricofallapatorius.service.statements;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class Variable {
	private String name;
	private TokenType returnType;
	private boolean isInitialized;
	private Token value;
	public Variable(String name, TokenType returnType, boolean isInitialized,
			Token value) {
		super();
		this.name = name;
		this.returnType = returnType;
		this.isInitialized = isInitialized;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TokenType getReturnType() {
		return returnType;
	}
	public void setReturnType(TokenType returnType) {
		this.returnType = returnType;
	}
	public boolean isInitialized() {
		return isInitialized;
	}
	public void setInitialized(boolean isInitialized) {
		this.isInitialized = isInitialized;
	}
	public Token getValue() {
		return value;
	}
	public void setValue(Token value) {
		this.value = value;
	}
}
