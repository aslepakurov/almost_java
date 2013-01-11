package com.raxacoricofallapatorius.statements;

import com.raxacoricofallapatorius.expression.Expression;

public class IfStatement extends Statement {
	private Expression expr;
	private BlockStmt thenPart;
	private BlockStmt elsePart;
	public IfStatement(Expression expr,BlockStmt thenPart,BlockStmt elsePart){
		this.expr = expr;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}
	public Expression getExpr() {
		return expr;
	}
	public BlockStmt getThenPart() {
		return thenPart;
	}
	public BlockStmt getElsePart() {
		return elsePart;
	}
}
