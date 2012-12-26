package com.raxacoricofallapatorius.statements;

import java.util.ArrayList;


public class BlockStmt {
	private ArrayList<Statement> statements;
	public BlockStmt() {
		this.statements = new ArrayList<Statement>();
	}
	public void addStatement(Statement stmt){
		statements.add(stmt);
	}
	public ArrayList<Statement> getStatements() {
		return statements;
	}
}
