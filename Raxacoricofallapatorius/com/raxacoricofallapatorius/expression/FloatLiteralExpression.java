package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.TokenType;

public class FloatLiteralExpression extends LiteralExpression {
	public FloatLiteralExpression(String value) {
		super(TokenType.TK_FLOAT, value);
	}
	public Double getValue(){
		return (Double) super.getValue();
	}
}
