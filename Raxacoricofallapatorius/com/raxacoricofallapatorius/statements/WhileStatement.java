package com.raxacoricofallapatorius.statements;

public class WhileStatement extends Statement {
	private BlockStmt block;
	public WhileStatement() {
	}
	public void addBlock(BlockStmt block) {
		this.block = block;
	}
}
