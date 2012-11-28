package com.raxacoricofallapatorius.analyzer;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.statements.Statement;
public class CallStatement extends Statement {
	private Token[] tokens;
	public CallStatement(Token[] tokens){
		this.tokens = tokens;
	}
	public Token[] getTokens(){
		return tokens;
	}
}
