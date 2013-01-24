package com.raxacoricofallapatorius.expression;
import com.raxacoricofallapatorius.service.VarDecl;
public class VarRefExpression extends Expression {
	private String name;
	private VarDecl vd;
	public VarRefExpression(String name, VarDecl vd){
		this.name =name;
		this.vd = vd;
	}
	public String getName() {
		return name;
	}
	public VarDecl getVd() {
		return vd;
	}
}
