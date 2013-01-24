package com.raxacoricofallapatorius.statements;

import java.util.ArrayList;

import com.raxacoricofallapatorius.expression.Expression;

public class AssignmentStatement extends Statement {
	private ArrayList<Expression> LHS;
	private ArrayList<Expression> RHS;
	public AssignmentStatement(ArrayList<Expression> LHS,ArrayList<Expression> RHS){
		this.LHS = LHS;
		this.RHS = RHS;
	}
	public ArrayList<Expression> getLHS() {
		return LHS;
	}
	public ArrayList<Expression> getRHS() {
		return RHS;
	}
	
}
