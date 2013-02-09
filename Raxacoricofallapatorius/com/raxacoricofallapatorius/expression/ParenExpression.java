package com.raxacoricofallapatorius.expression;
public class ParenExpression extends Expression {
	private Expression child;
	public ParenExpression(Expression expr){
		child = expr;
	}
	public Expression getExpr(){
		return child;
	}
}
