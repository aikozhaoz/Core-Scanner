# Core-Scanner

### How to run
* ```cd``` to the ```Core-Scanner``` folder
* To compile, run ```javac Main.java ``` in your terminal
* To run the actual program, run ```javac Main.java testfile```

### Project Description
* A scanner for a version of the Core language, a pretend language. The scanner takes a text file as input and returns a stream of “tokens” from the Core.java enumeration.

### Functionality
* Scanner:
    * The class constructor takes as input the name of the input file and finds the first token (the current token)
* tokenize:
    * This method should return a tokens list. 
* currentToken: 
    * This method should return the token the scanner is currently on, without consuming that token.
* nextToken:
    * This method should advance the scanner to the next token in the stream (the next token becomes the current token).
* getID: 
    * If the current token is ID, then this method should return the string value of the identifier.
* getCONST: 
    * If the current token is CONST, then this method should return the value of the constant.
