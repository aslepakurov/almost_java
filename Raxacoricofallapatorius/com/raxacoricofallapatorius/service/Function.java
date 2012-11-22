package com.raxacoricofallapatorius.service;

import com.raxacoricofallapatorius.service.statements.Statement;

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
	private TokenType[] inputVariablesTypes;
	/**
	 * array of input variables names
	 */
	private String[] inputVariablesNames;
	/**
	 * statements of function
	 */
	private Statement[] statements;

	/**
	 * constructor of function
	 * 
	 * @param name
	 *            function name
	 * @param returnType
	 *            function return type
	 * @param inputVariablesTypes
	 *            array of input variables types
	 * @param inputVariablesNames
	 *            array of input variables names
	 */
	public Function(String name, TokenType returnType,
			TokenType[] inputVariablesTypes, String[] inputVariablesNames) {
		this.name = name;
		this.returnType = returnType;
		this.inputVariablesTypes = inputVariablesTypes;
		this.inputVariablesNames = inputVariablesNames;
	}

	/**
	 * getter for function's array of input variables types
	 * 
	 * @return array of input variables types
	 */
	public TokenType[] getInputVariablesTypes() {
		return inputVariablesTypes;
	}

	/**
	 * getter for function's array of input variables names
	 * 
	 * @return array of input variables names
	 */
	public String[] getInputVariablesNames() {
		return inputVariablesNames;
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
