package com.raxacoricofallapatorius.service;


import java.util.ArrayList;

import com.raxacoricofallapatorius.statements.BlockStmt;

/**
 * Function class
 * 
 * @author insane
 * 
 */
public class Function {
	/**
	 * name of function
	 */
	private String name;
	/**
	 * function return type
	 */
	private TokenType returnType;
	/**
	 * block of statements
	 */
	private BlockStmt block;
	/**
	 * input parameters
	 */
	private ArrayList<VarDecl> param;
	public Function(String name, TokenType returnType,ArrayList<VarDecl> param,BlockStmt block) {
		this.name = name;
		this.returnType = returnType;
		this.param = param;
		this.block = block;
	}
	

	/**
	 * getter for function's name
	 * 
	 * @return name of function
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter of function return type
	 * 
	 * @return
	 */
	public TokenType getReturnType() {
		return returnType;
	}

	/**
	 * getter of function statements
	 * 
	 * @return function's statements
	 */
	public BlockStmt getBlock() {
		return block;
	}
	public ArrayList<VarDecl> getParameters(){
		return param;
	}
	/**
	 * equals method
	 * 
	 * compares two functions for names and input parameters
	 * 
	 * @param some
	 *            object
	 * @return true if functions has the same names, false otherwise
	 */
	public boolean equals(Object other) {
		if (other == null)
			return false;
		return ((Function) other).getName().equalsIgnoreCase(name);
	}
}
