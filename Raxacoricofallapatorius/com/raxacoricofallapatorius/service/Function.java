package com.raxacoricofallapatorius.service;

import com.raxacoricofallapatorius.service.statements.InputVarStatement;
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
	private Statement[] statements;
	private InputVarStatement variables;
	
	public Function(String name, TokenType returnType,
			InputVarStatement variables) {
		this.name = name;
		this.returnType = returnType;
		this.variables = variables;
	}
	
	
	public InputVarStatement getVariables() {
		return variables;
	}


	public void setVariables(InputVarStatement variables) {
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
	public void setStatements(Statement[] statements) {
		this.statements = statements;
	}

	/**
	 * getter of function statements
	 * 
	 * @return function's statements
	 */
	public Statement[] getStatements() {
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
