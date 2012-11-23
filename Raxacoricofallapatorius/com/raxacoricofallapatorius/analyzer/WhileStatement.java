package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.statements.Statement;

public class WhileStatement extends Statement {
	ArrayList<Token> expres = null;
	public WhileStatement() {
		expres = new ArrayList<Token>();
	}
	public void addExprs(Token token) {
		expres.add(token);
	}
}
