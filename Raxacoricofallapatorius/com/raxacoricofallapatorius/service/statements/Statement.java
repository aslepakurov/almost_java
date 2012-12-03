package com.raxacoricofallapatorius.service.statements;


public class Statement {
	protected Block block;
	public Statement() {
		block = null;
	}

	public void setBlock(Block block){
		this.block = block;
	}
}
