package com.raxacoricofallapatorius.statements;

import com.raxacoricofallapatorius.expression.Expression;

public class ReturnStatement extends Statement {
	private Expression expr;
	public ReturnStatement(Expression expr){
		this.expr = expr;
	}
	public Expression getExpr(){
		return expr;
	}
}
