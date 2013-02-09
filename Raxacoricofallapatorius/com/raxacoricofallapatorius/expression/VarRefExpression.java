package com.raxacoricofallapatorius.expression;
import com.raxacoricofallapatorius.service.VarDecl;
public class VarRefExpression extends Expression {
	private VarDecl vd;
	public VarRefExpression(VarDecl vd){
		this.vd = vd;
	}
	public VarDecl getVd() {
		return vd;
	}
}
