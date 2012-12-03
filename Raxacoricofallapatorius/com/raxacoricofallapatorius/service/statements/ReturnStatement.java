package com.raxacoricofallapatorius.service.statements;

import com.raxacoricofallapatorius.service.Token;

public class ReturnStatement extends Statement {
	private Token[] tokens;
	public ReturnStatement(Token[] tokens){
		this.tokens = tokens;
	}
	public Token[] getTokens() {
		return tokens;
	}
}
