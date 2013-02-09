package com.raxacoricofallapatorius.analyzer;
import java.util.ArrayList; 
import com.raxacoricofallapatorius.expression.Expression;
import com.raxacoricofallapatorius.expression.VarRefExpression;
import com.raxacoricofallapatorius.service.Function;
import com.raxacoricofallapatorius.service.SyntaxException;
import com.raxacoricofallapatorius.service.Token;
import com.raxacoricofallapatorius.service.TokenType;
import com.raxacoricofallapatorius.service.VarDecl;
import com.raxacoricofallapatorius.statements.AssignmentStatement;
import com.raxacoricofallapatorius.statements.BlockStmt;
import com.raxacoricofallapatorius.statements.IfStatement;
import com.raxacoricofallapatorius.statements.ReturnStatement;
import com.raxacoricofallapatorius.statements.Statement;
import com.raxacoricofallapatorius.statements.WhileStatement;
/**
 * 1st 'n' 2nd todos
 * @see com.raxacoricofallopatorius.analyzer.SyntaxAnalyzer
 * @author Barabashka
 */
//TODO: add arrays as a structure
//TODO: delete FOR statement references
//TODO: delete OK notOK thing in the end!
//TODO: parse expression statement
//TODO: parseExpression - write it bi(ea)ch
//TODO: bool literal
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
	 * OK
	 * @return next token
	 */
	private Token look() {
		return tokens[nextIndex];
	}
	/**
	 * sneak - peak the next Token
	 * OK
	 * @return
	 */
	private Token lookAhead(){
		if(nextIndex+1>=tokens.length) return null;
		else return tokens[nextIndex+1];
	}
	/**
	 * increase token index
	 */
	private void consume() {
		nextIndex++;
	}
	
	/**
	 * parse function method
	 * not OK
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
	/**
	 * parse function's arguments method
	 * OK
	 * @return object of function's arguments class
	 * @throws SyntaxException
	 */
	private ArrayList<VarDecl> parseFuncParameters() throws SyntaxException {
		ArrayList<VarDecl> vardecl = new ArrayList<VarDecl>();
		TokenType type;
		String name;
		while (true) {
			type = look().getType();
			if (type != TokenType.TK_K_INT && type != TokenType.TK_K_FLOAT
					&& type != TokenType.TK_K_STR
					&& type != TokenType.TK_K_BOOL)
				throw new SyntaxException("Variable type expected at "
						+ look().getLine() + ":" + look().getColumn());
			consume();
			if (look().getType() != TokenType.TK_ID)
				throw new SyntaxException("Variable id expected at "
						+ look().getLine() + ":" + look().getColumn());
			name = look().getName();
			vardecl.add(new VarDecl(name, type));
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
	//TODO: return null????? 											
	private BlockStmt parseBlock() throws SyntaxException {
		consume();
		ArrayList<Statement> blockstmt = new ArrayList<Statement>();
		ArrayList<VarDecl> blockvar = new ArrayList<VarDecl>();
		while(look().getType()!=TokenType.TK_S_RIGHT_BRACE){
			if(look().getType()==TokenType.TK_K_IF){
				IfStatement ifstmt = parseIF();
				blockstmt.add(ifstmt);
			}else if(look().getType()==TokenType.TK_K_WHILE){
				WhileStatement whilestmt = parseWHILE();
				blockstmt.add(whilestmt);
			}else if(look().getType()==TokenType.TK_K_INT || look().getType()==TokenType.TK_K_FLOAT 
						|| look().getType()==TokenType.TK_K_STR || look().getType() == TokenType.TK_K_BOOL){
				TokenType vartype = look().getType();
				consume();
				if(look().getType()!=TokenType.TK_ID){
					throw new SyntaxException(
							
							"Variable name missing at: " + look().getLine()
											+ ":" + look().getColumn());
				}
				String varname = look().getName();
				VarDecl vd = new VarDecl(varname, vartype);
				blockvar.add(vd);
				consume();
				VarRefExpression vre = new VarRefExpression(vd);
				AssignmentStatement as = null;
				if(look().getType()==TokenType.TK_S_SEMICOLON){
					as = new AssignmentStatement(vre, null);
				}else if(look().getType()==TokenType.TK_S_INIT){
					Expression expr=parseExpression();
					as = new AssignmentStatement(vre, expr);
				}else{
					throw new SyntaxException(
							"';' or '=' missing: " + look().getLine()
											+ ":" + look().getColumn());
				}
				blockstmt.add(as);
//EXPRESSION STATEMENT
//			}else if(){
			}else if(look().getType()==TokenType.TK_K_RETURN){
				//TODO: check return
				//TODO: get a return check, if return type not void or put into semantic??????
				ReturnStatement funcret = parseReturn();
				blockstmt.add(funcret);
			}else{													
				throw new SyntaxException(
						"Invalid statement at " + look().getLine()
										+ ":" + look().getColumn());
			}
			consume();
		}
		return new BlockStmt(blockstmt,blockvar);
	}
	//TODO: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	private Expression parseExpression() {
		
		return null;
	}
	/**
	 * return statement parse
	 * OK
	 * @return
	 */
	private ReturnStatement parseReturn() {
		consume();
		Expression expr = parseExpression();
		return new ReturnStatement(expr);
	}

	/**
	 * while statement parse
	 * OK
	 * @return
	 * @throws SyntaxException
	 */
	private WhileStatement parseWHILE() throws SyntaxException {
		consume();
		if (look().getType() != TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		Expression expr = parseExpression();
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		BlockStmt block = parseBlock();
		return new WhileStatement(expr, block);
	}
	/**
	 * If statement parse
	 * OK
	 * @return
	 * @throws SyntaxException
	 */
	private IfStatement parseIF() throws SyntaxException {
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_PARENT)
			throw new SyntaxException("'(' expected at " + look().getLine()
					+ ":" + look().getColumn());
		consume();
		Expression expr = parseExpression();
		consume();
		if(look().getType()!=TokenType.TK_S_LEFT_BRACE)
			throw new SyntaxException("'{' expected at " + look().getLine()
					+ ":" + look().getColumn());
		BlockStmt thenp = parseBlock();
		consume();
		BlockStmt elsep = null;
		if(lookAhead().getType()==TokenType.TK_K_ELSE){
			//TODO: double check the double consumation - got it?/ not funny =(
			consume();
			consume();
			if(look().getType()!=TokenType.TK_S_LEFT_BRACE)
				throw new SyntaxException("'{' expected at " + look().getLine()
						+ ":" + look().getColumn());
			elsep = parseBlock();
		}
		return new IfStatement(expr, thenp, elsep);
	}
}