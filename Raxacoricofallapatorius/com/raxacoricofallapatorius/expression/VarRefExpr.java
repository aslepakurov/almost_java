package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.VarDecl;

public class VarRefExpr extends Expression {
	private String name;
	private VarDecl vd;
	public VarRefExpr(){
		name= new String();
		vd = new VarDecl(); 
	}
	public VarRefExpr(String name, VarDecl vd){
		this.name =name;
		this.vd = vd;
	}
}
