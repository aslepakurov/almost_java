package com.raxacoricofallapatorius.statements;

import com.raxacoricofallapatorius.expression.Expression;

public class WhileStatement extends Statement {
	private Expression expr;
	private BlockStmt block;
	public WhileStatement(Expression expr, BlockStmt block) {
		this.expr = expr;
		this.block = block;
	}
	public Expression getExpr() {
		return expr;
	}
	public BlockStmt getBlock() {
		return block;
	}
}
