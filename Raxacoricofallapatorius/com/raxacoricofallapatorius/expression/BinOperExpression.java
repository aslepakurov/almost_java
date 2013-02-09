package com.raxacoricofallapatorius.expression;
public class BinOperExpression extends Expression {
	private Expression opd1;
	private Expression opd2;
	private Operation oper;
	public BinOperExpression(Expression opd1, Expression opd2, Operation oper) {
		super();
		this.opd1 = opd1;
		this.opd2 = opd2;
		this.oper = oper;
	}
	public Expression getOpd1() {
		return opd1;
	}
	public Expression getOpd2() {
		return opd2;
	}
	public Operation getOper() {
		return oper;
	}
}
