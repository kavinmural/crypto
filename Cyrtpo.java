//Author: Kavin Muralitharan
//Date: November 10, 2016
//Purpose: To create a cryptography program
//Input: input file
//Output: .cyp file and .pln file
import java.awt.*;
import hsa.Console;
import hsa.*;

public class Cyrtpo
{
    static Console c;           //  The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	String fileName;
	Vigenere vigenere = new Vigenere (c);
	c.print("Input A File Name: ");
	fileName = c.readLine();
	vigenere.encrypt(fileName);
	vigenere.decrypt(fileName);
	c.println("Done!");
    } // main method
} // Cyrtpo class

/*
Author: Kavin Muralitharan
Date: November 12, 2016
Purpose: To create a vigenere class
Data Elements/Fields:
    key - the key
Methods:
    encrypt - to encrypt the input file and write it to the .cyp file
    encryptLine - to encrypt a line
    decrypt - To decrypt the and write to .pln file
    decryptLine - To decrypt a line
*/
class Vigenere
{
    protected String key = "";

    public Vigenere(Console c)
    {
	c.println("Enter The Key:");
	this.key = c.readLine();
    }

    //Author: Kavin Muralitharan
    //Date: November 11, 2016
    //Purpose: To encrypt the input file and write it to the .cyp file
    //Input/Parameters: inputFile
    //Output/Returns: inputfile(String)
    public String encrypt(String inputFile)
    {
	String line;
	TextInputFile plainFile = new TextInputFile(inputFile + ".txt");
	TextOutputFile cypFile = new TextOutputFile(inputFile + ".cyp");
	
	while(!plainFile.eof())
	{
	    line = plainFile.readLine();
	    line = this.encryptLine(line);
	    cypFile.println(line);
	    
	}
	cypFile.close();
	plainFile.close();
	
	return inputFile + ".cyp";
    }
    
    //Author: Kavin Muralitharan
    //Date: November 11, 2016
    //Purpose: to encrypt a line
    //Input/Parameters: line
    //Output/Returns: Cipher(String)
    public String encryptLine(String line)
    {
	String key = this.key;
	String cipher = "";
	char [] charCipher = new char[line.length()];
	char [] charPlain = line.toCharArray();
	int result = 0;
	
	while(key.length() < charPlain.length)
	    key = key + key;
	 
	char[]position = key.toCharArray(); 
	for(int count = 0; count<position.length; count++)
	    position[count] = (char)((int)position[count] - 32);
	for(int count = 0;count<charCipher.length;count++)
	{
	    result = charPlain[count] + position[count];
	    if(result>126)
		result = result - 95;
	    charCipher[count] = (char)result;
	}
	for(int counter = 0; counter < charCipher.length; counter++)
	    cipher = cipher + charCipher[counter];
	    
	return cipher;
    }
    
    //Author: Kavin Muralitharan
    //Date: November 11, 2016
    //Purpose: To decrypt the and write to .pln file
    //Input/Parameters: file
    //Output/Returns: file(String)
    public String decrypt (String file)
    {
	String strLine;
	TextInputFile cypFile = new TextInputFile (file + ".cyp");
	TextOutputFile plainFile = new TextOutputFile (file + ".pln");
	while (!cypFile.eof())
	{
	    strLine = cypFile.readLine();
	    strLine = this.decryptLine (strLine);
	    plainFile.println(strLine);
	}
	cypFile.close();
	plainFile.close();
	return file + ".pln";
    }
    
    //Author: Kavin Muralitharan
    //Date: November 11, 2016
    //Purpose: To decrypt a line
    //Input/Parameters: line
    //Output/Returns: strPlain
    public String decryptLine (String line)
    {
	String strPlain = "";
	String key2 = this.key;
	int intResult;        
	char [] charPlain = new char [line.length()];
	char [] charCipher = line.toCharArray();
	
	while (key2.length() < charCipher.length)
	    key2 = key2 + key2;
	char [] charOffset = key2.toCharArray();
	for (int intCounter = 0; intCounter < charOffset.length; intCounter++)
	    charOffset[intCounter] = (char)((int)charOffset[intCounter] - 32);
	for (int intCounter = 0; intCounter < charPlain.length; intCounter++)
	{
	    if (charCipher[intCounter] + 95 - charOffset[intCounter] > 126)
		intResult = charCipher[intCounter] - charOffset[intCounter];
	    else
		intResult = charCipher[intCounter] + 95 - charOffset[intCounter];
	    charPlain[intCounter] = (char)intResult;
	}
	for (int intCounter = 0; intCounter < charPlain.length; intCounter++)
	    strPlain = strPlain + charPlain[intCounter];
	
	return strPlain;
    }
    
	
}//Vigenere Class
