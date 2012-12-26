package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.TokenType;

public class LiteralExpression extends Expression {
	private TokenType literalType;
	private String value;
	public LiteralExpression() {
	}
	public LiteralExpression(TokenType literalType, String value) {
		super();
		this.literalType = literalType;
		this.value = value;
	}
}
