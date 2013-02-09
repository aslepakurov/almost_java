package com.raxacoricofallapatorius.statements;


import com.raxacoricofallapatorius.expression.Expression;
import com.raxacoricofallapatorius.expression.VarRefExpression;

public class AssignmentStatement extends Statement {
	private VarRefExpression LHS;
	private Expression RHS;
	public AssignmentStatement(VarRefExpression LHS,Expression RHS){
		this.LHS = LHS;
		this.RHS = RHS;
	}
	public VarRefExpression getLHS() {
		return LHS;
	}
	public Expression getRHS() {
		return RHS;
	}
	
}
