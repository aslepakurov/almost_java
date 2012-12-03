package com.raxacoricofallapatorius.service.statements;
public class Block {
	private Statement[] statements;
	private Variable[] localVar;
	public Block(Statement[] statements, Variable[] localVar) {
		this.statements = statements;
		this.localVar = localVar;
	}
	public Statement[] getStatements() {
		return statements;
	}
	public Variable[] getLocalVar() {
		return localVar;
	}
}
