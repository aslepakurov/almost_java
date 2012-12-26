package com.raxacoricofallapatorius.statements;


public class WhileStatement extends Statement {
	private BlockStmt block;
	public WhileStatement() {
		block = new BlockStmt();
	}
	public void addStmt(Statement stmt) {
		block.addStatement(stmt);
	}
}
