package com.raxacoricofallapatorius.service.statements;
import java.util.ArrayList;
import com.raxacoricofallapatorius.service.Token;
public class IfStatement extends Statement {
	private ArrayList<Token> expres;
	public IfStatement(){
		expres = new ArrayList<Token>();
	}
	public void addExpres(Token token){
		expres.add(token);
	}
}
