package com.raxacoricofallapatorius.service;

import java.util.ArrayList;

import com.raxacoricofallapatorius.statements.Statement;

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
	 * array of input variables types
	 */
	private ArrayList<Statement> statements;
	private ArrayList<VarDecl> variables;
	
	public Function(String name, TokenType returnType,ArrayList<Statement> statement,
			ArrayList<VarDecl> variables) {
		this.name = name;
		this.returnType = returnType;
		this.statements = statement;
		this.variables = variables;
	}
	
	
	public ArrayList<VarDecl> getVariables() {
		return variables;
	}


	public void setVariables(ArrayList<VarDecl> variables) {
		this.variables = variables;
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
	 * setter of function statements
	 * 
	 * @param statements
	 */
	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}

	/**
	 * getter of function statements
	 * 
	 * @return function's statements
	 */
	public ArrayList<Statement> getStatements() {
		return statements;
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
