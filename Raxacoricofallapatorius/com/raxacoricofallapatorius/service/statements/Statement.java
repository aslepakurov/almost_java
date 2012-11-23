package com.raxacoricofallapatorius.service.statements;

import java.util.ArrayList;

public class Statement {
//	private ArrayList<Statement> statements;
	Block block;
	public Statement() {
		block = null;
//		statements = new ArrayList<Statement>();
	}
//	public void addStatment() {
//		
//	}
	public void setBlock(Block block){
		this.block = block;
	}
}
