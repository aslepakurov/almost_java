package com.raxacoricofallapatorius.expression;

import com.raxacoricofallapatorius.service.Function;

public class FunCallExpression extends Expression {
	private Function func;
	public FunCallExpression(Function func) {
		this.func = func;
	}
	public Function getFunc() {
		return func;
	}
s}
