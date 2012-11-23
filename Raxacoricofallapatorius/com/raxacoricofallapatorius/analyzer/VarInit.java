package com.raxacoricofallapatorius.analyzer;
import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.statements.Statement;
public class VarInit extends Statement {
	private ArrayList<Token> stmt;	
	public VarInit() {
		stmt = new ArrayList<Token>();
	}
	public void addStmt(Token token){
		stmt.add(token);
	}
}
