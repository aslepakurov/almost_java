package com.raxacoricofallapatorius.service.statements;
import com.raxacoricofallapatorius.service.Token;
public class CallStatement extends Statement {
	private Token[] tokens;
	public CallStatement(Token[] tokens){
		this.tokens = tokens;
	}
	public Token[] getTokens(){
		return tokens;
	}
}
