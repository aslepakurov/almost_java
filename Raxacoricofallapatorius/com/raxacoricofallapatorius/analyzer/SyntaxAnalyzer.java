package com.raxacoricofallapatorius.analyzer;
import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.raxacoricofallapatorius.service.VarDecl;
import com.raxacoricofallapatorius.service.statements.InputVarStatement;
//Solve the FREAKIN ARRAY TROUBLE 2012!!!!
//Add arrays!!!!!
//Don't like warnings
//Maybe reorganize block parse - add to block to function!
import com.raxacoricofallapatorius.statements.BlockStmt;
import com.raxacoricofallapatorius.statements.IfStatement;
import com.raxacoricofallapatorius.statements.ReturnStatement;
import com.raxacoricofallapatorius.statements.Statement;
import com.raxacoricofallapatorius.statements.WhileStatement;

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
		//function return type
		TokenType funcReturn = look().getType();
		if(funcReturn!=TokenType.TK_K_BOOL && funcReturn!=TokenType.TK_K_FLOAT && 
			funcReturn!=TokenType.TK_K_INT && funcReturn!=TokenType.TK_K_VOID && funcReturn!=TokenType.TK_K_STR)
			throw new SyntaxException("function return type expected at "
					+ look().getLine() + ":" + look().getColumn());
		consume();
		//function id
		if (look().getType() != TokenType.TK_ID)
			throw new SyntaxException("function name expected at "
					+ look().getLine() + ":" + look().getColumn());
		String funcName = look().getName();
		//function parameters
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		ArrayList<VarDecl> funcParam = parseFuncParameters();
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		BlockStmt funcblock = parseBlock();
		return new Function(funcName, funcReturn,funcParam,funcblock);
	}
	//TODO: next time start her e
	private BlockStmt parseBlock() throws SyntaxException {
		consume();
		ArrayList<Statement> blockstmt = new ArrayList<Statement>();
		ArrayList<VarDecl> blockvar = new ArrayList<VarDecl>();
		while(look().getType()!=TokenType.TK_S_RIGHT_BRACE){
			if(look().getType()==TokenType.TK_K_IF){
				//TODO: check if
//				IfStatement ifstmt = parseIF();
//				blockstmt.add(ifstmt);
			}else if(look().getType()==TokenType.TK_K_FOR){
				//TODO: check for
//				ForStatement forstmt = parseFOR();
//				blockstmt.add(forstmt);
			}else if(look().getType()==TokenType.TK_K_WHILE){
				//TODO: check while
//				WhileStatement whilestmt = parseWHILE();
//				blockstmt.add(whilestmt);
			}else if(look().getType()==TokenType.TK_ID){
				//TODO: check (change to) varref/stmt
//				VarInit varstmt = parseVarInit();
//				blockstmt.add(varstmt);
			}else if(look().getType()==TokenType.TK_K_RETURN){
				//TODO: check return
//				ReturnStatement funcret = parseReturn();
//				blockstmt.add(funcret);
			//TODO: variable declaration
//			}else if(){
			}else{														//whole new a lot too add
				throw new SyntaxException(
						"Invalid statement at " + look().getLine()
										+ ":" + look().getColumn());
			}
			//TODO: get a return check, if return type not void
			consume();
		}
		return new BlockStmt(blockstmt,blockvar);
	}

	private ReturnStatement parseReturn() {
		consume();
		ArrayList<Token> tokenarr = new ArrayList<Token>();
		while(look().getType()!=TokenType.TK_S_SEMICOLON){
			tokenarr.add(look());
			consume();
		}
		Token[] tokens = (Token[]) tokenarr.toArray();
		ReturnStatement retStmt = new ReturnStatement(tokens);
		return retStmt;
	}

	private Variable parseVarDecl() throws SyntaxException {
		String name = "";
		consume();
		if(look().getType() != TokenType.TK_ID)
			throw new SyntaxException("Variable name expected at " + look().getLine()
					+ ":" + look().getColumn());
		name = look().getName();
		consume();
		if(look().getType() != TokenType.TK_S_SEMICOLON)
			throw new SyntaxException("':' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		TokenType type = look().getType();
		if (type != TokenType.TK_STRING || type != TokenType.TK_INT
				|| type != TokenType.TK_FLOAT
				|| type != TokenType.TK_K_BOOL)
			throw new SyntaxException(
					"Variable return type expected at "
							+ look().getLine() + ":" + look().getColumn());
		consume();
		if(look().getType()==TokenType.TK_S_INIT){
			consume();
			Token value = null;
			if(look().getType()==TokenType.TK_INT || look().getType()==TokenType.TK_FLOAT ||
					look().getType()==TokenType.TK_STRING || look().getType()==TokenType.TK_K_TRUE ||
					look().getType()==TokenType.TK_K_FALSE){
				value = look();
			}
			else
				throw new SyntaxException(
						"Variable value expected at "
								+ look().getLine() + ":" + look().getColumn());
			return  new Variable(name, type, true, value);
		}
		else{
			return new Variable(name, type, false, null);
		}
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
		BlockStmt block = parseBlock();
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
		BlockStmt block = parseBlock();
		ifstmt.setBlock(block);
		return ifstmt;
	}


	/**
	 * parse function's arguments method
	 * OK
	 * @return object of function's arguments class
	 * @throws SyntaxException
	 */
	private ArrayList<VarDecl> parseFuncParameters() throws SyntaxException {
		ArrayList<VarDecl> vardecl = new ArrayList<VarDecl>();
		TokenType type;
		while (true) {
			VarDecl vd = new VarDecl();
			type = look().getType();
			if (type != TokenType.TK_K_INT && type != TokenType.TK_K_FLOAT
					&& type != TokenType.TK_K_STR
					&& type != TokenType.TK_K_BOOL)
				throw new SyntaxException("Variable type expected at "
						+ look().getLine() + ":" + look().getColumn());
			vd.setVarType(look().getType());
			consume();
			if (look().getType() != TokenType.TK_ID)
				throw new SyntaxException("Variable id expected at "
						+ look().getLine() + ":" + look().getColumn());
			vd.setName(look().getName());
			vardecl.add(vd);
			consume();
			if (look().getType() == TokenType.TK_S_COMMA) {
				consume();
				continue;
			}
			if (look().getType() == TokenType.TK_S_RIGHT_PARENT) {
				return vardecl;
			}
		}
	}
}