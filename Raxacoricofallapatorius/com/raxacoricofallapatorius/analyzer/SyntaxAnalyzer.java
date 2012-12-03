package com.raxacoricofallapatorius.analyzer;
import java.util.ArrayList;

import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.raxacoricofallapatorius.service.statements.Block;
import com.raxacoricofallapatorius.service.statements.ForStatement;
import com.raxacoricofallapatorius.service.statements.IfStatement;
import com.raxacoricofallapatorius.service.statements.InputVarStatement;
import com.raxacoricofallapatorius.service.statements.ReturnStatement;
import com.raxacoricofallapatorius.service.statements.Statement;
import com.raxacoricofallapatorius.service.statements.VarDecl;
import com.raxacoricofallapatorius.service.statements.VarInit;
import com.raxacoricofallapatorius.service.statements.Variable;
//Solve the FREAKIN ARRAY TROUBLE 2012!!!!
//Add arrays!!!!!
//Don't like warnings
//Maybe reorganize block parse - add to block to function!
import com.raxacoricofallapatorius.service.statements.WhileStatement;

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
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		ArrayList<Statement> funcstmt = new ArrayList<Statement>();
		ArrayList<Variable> funcvar = new ArrayList<Variable>();
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
			}else if(look().getType()==TokenType.TK_K_DEF){
				Variable var = parseVarDecl();
				funcstmt.add(new VarDecl(var));
				funcvar.add(var);
			}else if(look().getType()==TokenType.TK_K_RETURN){
				ReturnStatement funcret = parseReturn();
				funcstmt.add(funcret);
			}else if(look().getType()==TokenType.TK_K_CALL){
//				CallStatement funccall = parseCall();
//				funcstmt.add(funccall);
			}else{														//whole new a lot too add
				throw new SyntaxException(
						"Invalid statement at " + look().getLine()
										+ ":" + look().getColumn());
			}
			consume();
			
		}
		return new Function(funcName, funcReturn,inputVarStatement);
	}

//	private CallStatement parseCall() {
//		ArrayList<Token> tokenarr = new ArrayList<Token>();
//		consume();
//		while(look().getType()!=TokenType.TK_S_SEMICOLON){
//			tokenarr.add(look());
//			consume();
//		}
//		
//		Token[] tokens = tokenarr.;
//		CallStatement callstmt = new CallStatement(tokens);
//		return callstmt;
//	}

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
	private Block blockParse() throws SyntaxException {
		consume();
		ArrayList<Statement> blockstmt = new ArrayList<Statement>();
		ArrayList<Variable> blockvar = new ArrayList<Variable>();
		while(look().getType()!=TokenType.TK_S_RIGHT_BRACE){
			if(look().getType()==TokenType.TK_K_IF){
				IfStatement ifstmt = parseIF();
					blockstmt.add(ifstmt);
			}else if(look().getType()==TokenType.TK_K_FOR){
				ForStatement forstmt = parseFOR();
				blockstmt.add(forstmt);
			}else if(look().getType()==TokenType.TK_K_WHILE){
				WhileStatement whilestmt = parseWHILE();
				blockstmt.add(whilestmt);
			}else if(look().getType()==TokenType.TK_ID){
				VarInit varstmt = parseVarInit();
				blockstmt.add(varstmt);
			}else if(look().getType()==TokenType.TK_K_DEF){
				Variable var = parseVarDecl();
				blockstmt.add(new VarDecl(var));
				blockvar.add(var);
			}else if(look().getType()==TokenType.TK_K_RETURN){
				ReturnStatement funcret = parseReturn();
				blockstmt.add(funcret);
			}else if(look().getType()==TokenType.TK_K_CALL){
//				CallStatement funccall = parseCall();
//				blockstmt.add(funccall);
			}else{														//whole new a lot too add
				throw new SyntaxException(
						"Invalid statement at " + look().getLine() + 
											":" + look().getColumn());
			}
			consume();
		}
		return new Block((Statement[])blockstmt.toArray(),(Variable[])blockvar.toArray());
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
			if (type != TokenType.TK_K_INT && type != TokenType.TK_K_FLOAT
					&& type != TokenType.TK_K_STR
					&& type != TokenType.TK_K_BOOL)
				throw new SyntaxException("Variable type expected at "
						+ look().getLine() + ":" + look().getColumn());
			types.add(look());
			consume();
			if (look().getType() != TokenType.TK_ID)
				throw new SyntaxException("Variable id expected at "
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
}