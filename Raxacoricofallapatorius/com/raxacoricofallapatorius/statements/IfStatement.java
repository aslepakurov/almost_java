package com.raxacoricofallapatorius.statements;
public class IfStatement extends Statement {
	//TODO: add expression
	private BlockStmt thenPart;
	private BlockStmt elsePart;
	public IfStatement(){
		thenPart = new BlockStmt();
		elsePart = new BlockStmt();
	}
	public void addThen(Statement stmt){
		thenPart.addStatement(stmt);
	}
	public void addEkse(Statement stmt){
		elsePart.addStatement(stmt);
	}
}
