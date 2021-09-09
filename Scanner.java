import java.io.*; 
import java.util.*;
import java.io.BufferedReader;

class Scanner {	
	// Declare the tokens list to store all the tokens from the file.
	public ArrayList<String> tokens = new ArrayList<String>(); 
	// Declare the chars list to store all the chars from the file.
	public ArrayList<Character> chars = new ArrayList<Character>();
	private String special= ";(),=!<+-*";
	private String current = "";
	public String errortoken = "";

	// Constructor should open the file and find the first token
	Scanner(String filename) {
		String currentLine= null;
		try {
			// Read input stream via filename.
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			// Read through the file and store all valid chars into chars list.
			while ((currentLine = reader.readLine()) != null) {
				for(int i = 0; i < currentLine.length(); i++){
					char currentChar = currentLine.charAt(i);
					this.chars.add(currentChar);
				}
			}
			this.tokenize(this.chars);
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// Tokenizer: Form tokens(words) from parsing through chars list
	public void tokenize(ArrayList<Character> chars){
		int anchor = 0;
		for(int i = 0; i<chars.size(); i++){
			char c = chars.get(i);
			// Start to form tokens only if the current char is not a whitespace
			if(!Character.isWhitespace(c)){
				String currentword = ""+c;
				// Case: when current character is a letter
				if(Character.isLetter(c)){
					// Continue adding next characters to currentword until it hits either a white space or a special character
					for(int j = i+1; j<chars.size(); j++){
						anchor = j;
						char nextc = chars.get(j);
						if (!(Character.isWhitespace(nextc))&&(special.indexOf(nextc)!=-1)){
							currentword+=nextc;
						}else{
							j = chars.size();
						}
					}
				}
				// Case: when current character is a digit
				else if(Character.isDigit(c)){
					// Continue adding next characters to currentword until it hits either a white space or a special character
					for(int j = i+1; j<chars.size(); j++){
						anchor = j;
						char nextc = chars.get(j);
						if (!(Character.isWhitespace(nextc))&&(special.indexOf(nextc)!=-1)&&(Character.isLetter(nextc))){
							currentword+=nextc;
						}else{
							j = chars.size();
						}
					}
				}
				// Case: when current character is a special character
				else if(special.indexOf(c)!=-1){
					// Check if next index is out of bound.
					int j = i+1;
					if(j<chars.size()){
						char nextc = chars.get(j);
						if(c=='='&&nextc=='='){
							currentword+=nextc;
						}else if (c=='<'&&nextc=='='){
							currentword+=nextc;
						}
					}
				}else{
					this.errortoken+=c;
					currentword = "error";
				}
				this.tokens.add(currentword);
			}
			anchor--;
			// Update i with anchor
			i = anchor;	
		}
	}

	// nextToken should advance the scanner to the next token
	public void nextToken() {
		this.tokens.remove(0);
	}

	// currentToken should return the current token
	public Core currentToken() {
		this.current = this.tokens.get(0);
		switch(current){
			// Check if the current token is a keyword/not
			case "program":
				return Core.PROGRAM;
			case "begin":
				return Core.BEGIN;
			case "end":
				return Core.END;
			case "new":
				return Core.NEW;
			case "define":
				return Core.DEFINE;
			case "extends":
				return Core.EXTENDS;
			case "class":
				return Core.CLASS;
			case "endclass":
				return Core.ENDCLASS;
			case "int":
				return Core.INT;
			case "endfunc":
				return Core.ENDFUNC;
			case "if":
				return Core.IF;
			case "then":
				return Core.THEN;
			case "else":
				return Core.ELSE;
			case "while":
				return Core.WHILE;
			case "endwhile":
				return Core.ENDWHILE;
			case "endif":
				return Core.ENDIF;
			case "input":
				return Core.INPUT;
			case "output":
				return Core.OUTPUT;
			case "or":
				return Core.OR;
			case "ref":
				return Core.REF;
		
			// Check if the current token is a special character/not
			case ";":
				return Core.SEMICOLON;
			case "(":
				return Core.LPAREN;
			case ")":
				return Core.RPAREN;
			case ",":
				return Core.COMMA;
			case "=":
				return Core.ASSIGN;
			case "!":
				return Core.NEGATION;
			case "==":
				return Core.EQUAL;
			case "<":
				return Core.LESS;
			case "<=":
				return Core.LESSEQUAL;
			case "+":
				return Core.ADD;
			case "-":
				return Core.SUB;
			case "*":
				return Core.MULT;

			// Check if the current token is an error or not
			case "error":
				return Core.ERROR;
			// case "eof":
			// return Core.EOF;
			default:
				// If the current word starts with digits, it will always result in const.
				if(Character.isDigit(current.charAt(0))){
					return Core.CONST;
				}else{
					return Core.ID;
				}
		}
		
		// char currentChar = chars.get(0);
		// // Case 1: If the start char is a letter:
		// if(Character.isLetter(currentChar)){
		// 	String current = ""+currentChar;
		// 	int i = 1;
		// 	while(i<chars.size()&&Character.isLetterOrDigit(chars.get(i))){
		// 		currentChar = chars.get(i);
		// 		if(Character.isUpperCase(currentChar)||Character.isDigit(currentChar)){
		// 			return Core.ID;
		// 		}else{
		// 			current+=currentChar;
		// 		}
		// 		i++;	
		// 	}
		// 	for(int i = 0; i<Core.values().length;i++){
		// 		if (Core.values()[i].toString().equals(String word)){
		// 			return Core.values()[i];
		// 		}
		// 	}
		// 	return Core.ID;




			
		// 	for(int j = 1; j<chars.size();j++){
		// 		char nextChar = chars.get(j);
		// 		if(Character.isLetterOrDigit(nextChar)){
		// 			current+=nextChar;
		// 		}
		// 	}
		// 	// Case 1-1: If the star char also happens to be lower case:
		// 	if(Character.isUpperCase(currentChar)){
				
		// 	}

		// }
		// return 0;
	}

	// If the current token is ID, return the string value of the identifier
	// Otherwise, return value does not matter
	public String getID() {
		return this.current;
	}

	// If the current token is CONST, return the numerical value of the constant
	// Otherwise, return value does not matter
	public int getCONST() {
		return Integer.parseInt(this.current);
	}

}