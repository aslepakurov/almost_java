package com.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalizer {
	public int curLine = 1;
	public int curChar = 1;
	ArrayList<Token> tokens = new ArrayList<Token>();
	File file = null;
	public LexicalAnalizer(File file){
		this.file = file;
	}
	public void parse(){
		BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(file));
			String string;
			String buf;
			while((string=bf.readLine())!=null){
				for(char ch:string.toCharArray()){
					curChar++;
					switch(ch){
						case 'a':
			            case 'b':
			            case 'c':
			            case 'd':
			            case 'e':
			            case 'f':
			            case 'g':
			            case 'h':
			            case 'i':
			            case 'j':
			            case 'k':
			            case 'l':
			            case 'm':
			            case 'n':
			            case 'o':
			            case 'p':
			            case 'q':
			            case 'r':
			            case 's':
			            case 't':
			            case 'u':
			            case 'v':
			            case 'w':
			            case 'x':
			            case 'y':
			            case 'z':
			            case '\n':{
			            	curLine++;
			            	curChar = 0;
			            	break;
			            }
			            case ' ':{
			            	
			            	break;
			            }
					}
				}
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.printf("Problem at %d,%d",curLine,curChar);
		}
	}
	public static void main(String[] args) {
		
	}
}
