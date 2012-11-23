package com.raxacoricofallapatorius.service.statements;
import java.util.ArrayList;
import com.raxacoricofallapatorius.service.Token;
public class IfStatement extends Statement {
	ArrayList<Token> expres;
	Block block;
	public IfStatement(){
		expres = new ArrayList<Token>();
		block = null;
	}
	public void addExpres(Token token){
		expres.add(token);
	}
}
