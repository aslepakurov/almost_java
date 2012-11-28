package com.raxacoricofallapatorius.analyzer;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.statements.Statement;

public class ReturnStatement extends Statement {
	private Token[] tokens;
	public ReturnStatement(Token[] tokens){
		this.tokens = tokens;
	}
	public Token[] getTokens() {
		return tokens;
	}
}
