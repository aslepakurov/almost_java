package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.TokenType;

public class StrLiteralExpression extends LiteralExpression {
	
	public StrLiteralExpression(String value) {
		super(TokenType.TK_STRING, value);
	}

	public String getValue(){
		return (String) super.getValue();
	}
}
