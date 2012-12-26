package com.raxacoricofallapatorius.statements;

import com.raxacoricofallapatorius.expression.Expression;

public class ExpressionStatement {
	private Expression expr;
	public ExpressionStatement(Expression expr) {
		this.expr = expr;
	}
}
