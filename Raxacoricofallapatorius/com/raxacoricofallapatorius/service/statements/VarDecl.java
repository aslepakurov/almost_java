package com.raxacoricofallapatorius.service.statements;
public class VarDecl extends Statement {
	private Variable var;
	public VarDecl(Variable var){
		this.var = var;
	}
	public Variable getVar(){
		return var;
	}
}
