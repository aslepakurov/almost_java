package com.raxacoricofallapatorius.service.statements;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class ForStatement extends Statement{
	ArrayList<Token> expr1;
	ArrayList<Token> expres;
	ArrayList<Token> expr2;
	public ForStatement() {
		expr1 = new ArrayList<Token>();
		expr2 = new ArrayList<Token>();;
		expres = new ArrayList<Token>();;
	}
	public void addEx1(Token token) {
		expr1.add(token);
	}
	public void addEx2(Token token) {
		expr2.add(token);
	}
	public void addExpres(Token token) {
		expres.add(token);
	}
	public void ifEmpty(){
		if(expres.isEmpty()) addExpres(new Token("true", TokenType.TK_K_TRUE, -1, -1));
	}
}
