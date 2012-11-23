package com.raxacoricofallapatorius.analyzer;

import java.sql.Blob;
import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.raxacoricofallapatorius.service.statements.Block;
import com.raxacoricofallapatorius.service.statements.ForStatement;
import com.raxacoricofallapatorius.service.statements.IfStatement;
import com.raxacoricofallapatorius.service.statements.InputVarStatement;
import com.raxacoricofallapatorius.service.statements.LocalVarStatement;
import com.raxacoricofallapatorius.service.statements.Statement;

public class SyntaxAnalyzer {
	private Token[] tokens;
	private int nextIndex = 0;

	public SyntaxAnalyzer(Token[] tokens) {
		this.tokens = tokens;
	}

	public Function[] parse() throws SyntaxException {
		ArrayList<Function> functions = new ArrayList<Function>();

		while (look().getType() == TokenType.TK_K_FUNC) {
			functions.add(parseFunction());
		}
		if (look().getType() != TokenType.TK_EOP)
			throw new SyntaxException("End of program excepted at "
					+ look().getLine() + ":" + look().getColumn());
		if (functions.size() == 0)
			throw new SyntaxException(
					"Function declaration missed at the begining of source code");

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
		TokenType funcReturn = look().getType();
		if(funcReturn!=TokenType.TK_K_BOOL && funcReturn!=TokenType.TK_K_FLOAT && 
			funcReturn!=TokenType.TK_K_INT && funcReturn!=TokenType.TK_K_VOID && funcReturn!=TokenType.TK_K_STR)
			throw new SyntaxException("function return type expected at "
					+ look().getLine() + ":" + look().getColumn());
		consume();	
		if (look().getType() != TokenType.TK_ID)
			throw new SyntaxException("function name expected at "
					+ look().getLine() + ":" + look().getColumn());
		String funcName = look().getName();
		// looking for input variables
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		InputVarStatement inputVarStatement = parseFuncArguments();
		// looking for declaration of local variables
		consume();
		LocalVarStatement localVarStatement = new LocalVarStatement();
		if (look().getType() == TokenType.TK_K_DEF) {
			consume();
			if (look().getType() != TokenType.TK_S_LEFT_BRACE)
				throw new SyntaxException("'{' expected at " + look().getLine()
						+ ":" + look().getColumn());
			consume();
			localVarStatement = parseLocalVariables();
		}
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		ArrayList<Statement> funcstmt = new ArrayList<Statement>();
		while(look().getType()!=TokenType.TK_S_RIGHT_BRACE){
			if(look().getType()==TokenType.TK_K_IF){
				IfStatement ifstmt = parseIF();
				funcstmt.add(ifstmt);
			}else if(look().getType()==TokenType.TK_K_FOR){
				ForStatement forstmt = parseFOR();
				funcstmt.add(forstmt);
			}else if(look().getType()==TokenType.TK_K_WHILE){
				WhileStatement whilestmt = parseWHILE();
				funcstmt.add(whilestmt);
			}else if(look().getType()==TokenType.TK_ID){
				VarInit varstmt = parseVarInit();
				funcstmt.add(varstmt);
			}
			consume();
		}
		Statement statment = new Statement();
	}

	private VarInit parseVarInit() throws SyntaxException {
		VarInit varinit = new VarInit();
		varinit.addStmt(look());
		consume();
		if(look().getType()!=TokenType.TK_S_INIT)
			throw new SyntaxException("'=' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		while(look().getType()!=TokenType.TK_S_SEMICOLON){
			varinit.addStmt(look());
			consume();
		}
		return varinit;
	}

	private WhileStatement parseWHILE() throws SyntaxException {
		WhileStatement whilestmt = new WhileStatement();
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		while(look().getType()!=TokenType.TK_S_RIGHT_PARENT){
			whilestmt.addExprs(look());
			consume();
		}
		return whilestmt;
	}

	private ForStatement parseFOR() throws SyntaxException {
		ForStatement forstmt = new ForStatement();
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		while(look().getType()!=TokenType.TK_S_SEMICOLON){
			forstmt.addEx1(look());
			consume();
		}
		consume();
		while(look().getType()!=TokenType.TK_S_SEMICOLON){
			forstmt.addExpres(look());
			consume();
		}
		while(look().getType()!=TokenType.TK_S_RIGHT_PARENT){
			forstmt.addEx2(look());
			consume();
		}
		if(look().getType()!=TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		Block block = blockParse();
		forstmt.setBlock(block);
		return forstmt;
	}

	private IfStatement parseIF() throws SyntaxException {
		IfStatement ifstmt = new IfStatement();
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		while(look().getType()!=TokenType.TK_S_RIGHT_PARENT){
			ifstmt.addExpres(look());
			consume();
		}
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		Block block = blockParse();
		ifstmt.setBlock(block);
		return ifstmt;
	}

	//Main thin to be done!!!!!!!!!!!!!!
	private Block blockParse() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * parse function's arguments method
	 * 
	 * @return object of function's arguments class
	 * @throws SyntaxException
	 */
	private InputVarStatement parseFuncArguments() throws SyntaxException {
		ArrayList<Token> types = new ArrayList<Token>();
		ArrayList<Token> ids = new ArrayList<Token>();
		TokenType type;
		while (true) {
			type = look().getType();
			if (type != TokenType.TK_K_INT || type != TokenType.TK_K_FLOAT
					|| type != TokenType.TK_K_STR
					|| type != TokenType.TK_K_BOOL)
				throw new SyntaxException("Variable type expected at "
						+ look().getLine() + ":" + look().getColumn());
			types.add(look());
			consume();
			if (look().getType() != TokenType.TK_ID)
				throw new SyntaxException("Variaple id expected at "
						+ look().getLine() + ":" + look().getColumn());
			ids.add(look());
			consume();
			if (look().getType() == TokenType.TK_S_COMMA) {
				consume();
				continue;
			}
			if (look().getType() == TokenType.TK_S_RIGHT_PARENT) {
				Token[] tokens = new Token[types.size()];
				return new InputVarStatement(types.toArray(tokens),
						ids.toArray(tokens));
			}
		}
	}

	/**
	 * parse function's local variable declarations method
	 * 
	 * @return object of function's local declarations class
	 * @throws SyntaxException
	 */
	private LocalVarStatement parseLocalVariables() throws SyntaxException {
		ArrayList<Token> types = new ArrayList<Token>();
		ArrayList<Token> ids = new ArrayList<Token>();
		ArrayList<Token> initValues = new ArrayList<Token>();
		TokenType type;
		while (true) {
			if ((type = look().getType()) == TokenType.TK_S_RIGHT_BRACE) {
				Token[] tokens = new Token[types.size()];
				return new LocalVarStatement(types.toArray(tokens),
						ids.toArray(tokens), initValues.toArray(tokens));
			}
			if (type != TokenType.TK_K_INT || type != TokenType.TK_K_FLOAT
					|| type != TokenType.TK_K_STR
					|| type != TokenType.TK_K_BOOL)
				throw new SyntaxException("Variable type expected at "
						+ look().getLine() + ":" + look().getColumn());
			types.add(look());
			consume();
			if (look().getType() != TokenType.TK_ID)
				throw new SyntaxException("Variable id expected at "
						+ look().getLine() + ":" + look().getColumn());
			ids.add(look());
			consume();
			if (look().getType() == TokenType.TK_S_SEMICOLON) {
				consume();
				continue;
			}
			if (look().getType() != TokenType.TK_S_INIT)
				throw new SyntaxException("';' expected at " + look().getLine()
						+ ":" + look().getColumn());
			consume();
			type = look().getType();
			if (type != TokenType.TK_STRING || type != TokenType.TK_INT
					|| type != TokenType.TK_FLOAT
					|| type != TokenType.TK_K_TRUE
					|| type != TokenType.TK_K_FALSE)
				throw new SyntaxException(
						"Variable initialization expected at "
								+ look().getLine() + ":" + look().getColumn());
			initValues.add(look());
		}
	}
}
