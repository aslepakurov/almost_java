/**
 * 
 */
package com.raxacoricofallapatorius.service.statements;

import com.raxacoricofallapatorius.service.Token;

/**
 * @author insane
 * 
 */
public class LocalVarStatement extends Statement {
	private Token[] types;
	private Token[] ids;
	private Token[] initValues;

	/**
	 * constructor for function without local variables
	 */
	public LocalVarStatement() {
		this(new Token[0], new Token[0], new Token[0]);
	}

	/**
	 * constructor for function with local variables
	 * 
	 * @param types
	 * @param ids
	 * @param initValues
	 */
	public LocalVarStatement(Token[] types, Token[] ids, Token[] initValues) {
		this.types = types;
		this.ids = ids;
		this.initValues = initValues;
	}

	public Token[] getTypes() {
		return types;
	}

	public Token[] getIds() {
		return ids;
	}

	public Token[] getInitValues() {
		return initValues;
	}

}
