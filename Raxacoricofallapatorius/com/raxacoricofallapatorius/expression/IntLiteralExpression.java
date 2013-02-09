package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.TokenType;

public class IntLiteralExpression extends LiteralExpression {
	
	public IntLiteralExpression(String value) {
		super(TokenType.TK_INT, value);
	}

	public Integer getValue() {
		return (Integer) super.getValue();
	}
}
