package com.raxacoricofallapatorius.statements;
public class IfStatement extends Statement {
	//TODO: add expression
	private BlockStmt thenPart;
	private BlockStmt elsePart;
	public IfStatement(){
	}
	public void addThen(BlockStmt block){
		thenPart = block;
	}
	public void addElse(BlockStmt block){
		elsePart = block;
	}
}
