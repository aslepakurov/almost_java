package com.raxacoricofallapatorius.service.statements;

import com.raxacoricofallapatorius.service.Token;

public class InputVarStatement extends Statement {
	/**
	 * types of input variables
	 */
	private Token[] types;
	/**
	 * id of input variables
	 */
	private Token[] ids;

	/**
	 * default constructor. for functions without input variables
	 */
	public InputVarStatement() {
		this(new Token[0], new Token[0]);
	}

	/**
	 * constructor for functions with input variables
	 * 
	 * @param types
	 *            input types
	 * @param ids
	 *            input ids
	 */
	public InputVarStatement(Token[] types, Token[] ids) {
		this.types = types;
		this.ids = ids;
	}

	/**
	 * get types
	 * 
	 * @return types
	 */
	public Token[] getTypes() {
		return types;
	}

	/**
	 * get ids
	 * 
	 * @return ids
	 */
	public Token[] getIds() {
		return ids;
	}

}
