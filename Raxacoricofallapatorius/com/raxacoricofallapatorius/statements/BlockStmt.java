package com.raxacoricofallapatorius.statements;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.VarDecl;


public class BlockStmt {
	private ArrayList<Statement> statements;
	private ArrayList<VarDecl> decls;
	public BlockStmt(ArrayList<Statement> statements, ArrayList<VarDecl> decls) {
		this.statements = statements;
		this.decls = decls;
	}
	public ArrayList<Statement> getStatements() {
		return statements;
	}
	public ArrayList<VarDecl> getDecls(){
		return decls;
	}
}
