package com.raxacoricofallapatorius.service.statements;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;

public class WhileStatement extends Statement {
	ArrayList<Token> expres = null;
	public WhileStatement() {
		expres = new ArrayList<Token>();
	}
	public void addExprs(Token token) {
		expres.add(token);
	}
}
