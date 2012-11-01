package com.raxacoricofallapatorius.service;

public enum TokenType {
	TK_ID, 			//identifier token
	TK_STRING,		//string token
	TK_INT,			//int num token
	TK_FLOAT,		//float num token
	
	//-------Keywords--------
	
	TK_K_INT,		//int
	TK_K_FLOAT,		//float
	TK_K_STR,		//str
	TK_K_VOID,		//void
	TK_K_BOOL,		//bool
	TK_K_TRUE,		//true
	TK_K_FALSE,		//false
	TK_K_FUNC,		//func
	TK_K_FOR,		//for
	TK_K_WHILE,		//while
	TK_K_IF,		//if
	TK_K_ELSE,		//else
	TK_K_CONTINUE,		//continue
	TK_K_BREAK,		//break
	TK_K_CONST,		//const
	TK_K_DEF,		//def
	TK_K_DIV,		//div
	
	//--------Separators-----------
	
	TK_S_QUOT,			//  "
	TK_S_LEFT_PARENT,	//  (
	TK_S_RIGHT_PARENT,	//  )
	TK_S_LEFT_BRACE,	//  {
	TK_S_RIGHT_BRACE,	//  }
	TK_S_AND,			//  &
	TK_S_OR,			//  |
	TK_S_MULTI,			//  *
	TK_S_DIVIDE,		//  /
	TK_S_ADD,			//  +
	TK_S_SUBTRACT,		//  -
	TK_S_GREATER,		//	>
	TK_S_LESS,			//	<
	
	
	//--------Separator chx2--------
	
	TK_S_NOT,			//	!=
	TK_S_EQUAL,			//  ==
	TK_S_EQUAL_OR_GREATER,//>=
	TK_S_EQUAL_OR_LESS	//<=
	;
}
