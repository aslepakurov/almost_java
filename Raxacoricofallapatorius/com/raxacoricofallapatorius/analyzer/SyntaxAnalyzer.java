package com.raxacoricofallapatorius.analyzer;

import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;

public class SyntaxAnalyzer {
	private Token[] tokens;
	private int nextIndex = 0;

	public SyntaxAnalyzer(Token[] tokens) {
		this.tokens = tokens;
	}

	public Function[] parse() throws SyntaxException {
		ArrayList<Function> functions = new ArrayList<Function>();

		while (look().getType() == TokenType.TK_K_FUNC
				&& look().getType() != TokenType.TK_EOP) {
			functions.add(parseFunction());
		}

		if (functions.size() == 0)
			throw new SyntaxException(
					"Function declaration missed at the begining of source file");

		Function[] result = new Function[functions.size()];
		result = functions.toArray(result);
		return result;
	}

	/**
	 * get next token
	 * 
	 * @return next token
	 */
	private Token look() {
		return tokens[nextIndex];
	}

	/**
	 * increase token index
	 */
	private void consume() {
		nextIndex++;
	}

	/**
	 * parse function method
	 * 
	 * @return object of function class
	 * @throws SyntaxException
	 */
	private Function parseFunction() throws SyntaxException {
		consume();
		if (look().getType() != TokenType.TK_ID)
			throw new SyntaxException("function name expected at "
					+ look().getLine() + ":" + look().getColumn());
		String funcName = look().getName();
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		// next step : parsing function arguments
	}

	private void parseFuncArguments(Function func) {
		// later
	}
}
