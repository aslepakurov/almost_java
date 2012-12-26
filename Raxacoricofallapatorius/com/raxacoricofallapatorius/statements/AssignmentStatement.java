package com.raxacoricofallapatorius.statements;

import com.raxacoricofallapatorius.expression.Expression;

public class AssignmentStatement extends Statement {
	private Expression LHS;
	private Expression RHS;
	public AssignmentStatement(Expression LHS,Expression RHS){
		this.LHS = LHS;
		this.RHS = RHS;
	}
	public Expression getLHS() {
		return LHS;
	}
	public Expression getRHS() {
		return RHS;
	}
	
}
