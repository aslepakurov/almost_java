package com.raxacoricofallapatorius.service.statements;
import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;
public class VarInit extends Statement {
	private ArrayList<Token> stmt;	
	public VarInit() {
		stmt = new ArrayList<Token>();
	}
	public void addStmt(Token token){
		stmt.add(token);
	}
}
