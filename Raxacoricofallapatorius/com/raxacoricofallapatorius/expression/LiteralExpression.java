package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.TokenType;

public class LiteralExpression extends Expression {
	private TokenType literalType;
	private String valueHolder;
	public LiteralExpression() {
	}
	public LiteralExpression(TokenType literalType, String value) {
		super();
		this.literalType = literalType;
		this.valueHolder = value;
	}
	public Object getValue(){
		return valueHolder;
	}
	public TokenType getType(){
		return literalType;
	}
}
