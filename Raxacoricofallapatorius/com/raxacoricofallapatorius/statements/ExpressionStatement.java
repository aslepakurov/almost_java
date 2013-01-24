package com.raxacoricofallapatorius.statements;

import java.util.ArrayList;

import com.raxacoricofallapatorius.expression.Expression;

public class ExpressionStatement {
	private ArrayList<Expression> expr;
	public ExpressionStatement(ArrayList<Expression> expr) {
		this.expr = expr;
	}
}
