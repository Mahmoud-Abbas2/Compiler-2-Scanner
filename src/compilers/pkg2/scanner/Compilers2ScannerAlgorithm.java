/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilers.pkg2.scanner;

import static compilers.pkg2.scanner.Compilers2ScannerAlgorithm.lexeme;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abbas
 */
public class Compilers2ScannerAlgorithm {
    int freeze = 0,freeze2=0;
    static String assignmentOperator = "=";
    static String accessOperator = ".";
    static String brace = ",";
    static String inclusion = "Using";
    static String Class = "Divisio";
    static String inheritance = "InferedFrom";
    static String integer = "Ire";
    static String sInteger = "Sire";
    static String character = "Clo";
    static String string = "SetOfClo";
    static String Float = "FBU";
    static String sFloat = "SFBU";
    static String Void = "NoneValue";
    static String Break = "TerminateThisNow";
    static String Loop = "RingWhen";
    static String Return = "BackedValue";
    static String Struct = "STT";
    static String startStatement = "Beginning";
    static String endStatement = "End";
    //static String singleLineComment = "/-";
    
    
    static String lexeme = "";
    static Scanner code;
    
    
    static List logicOperators = new ArrayList();
    static List relationalOperators = new ArrayList();
    static List arithmaticOperations = new ArrayList();
    static List quotationMarks = new ArrayList();
    static List delimiters = new ArrayList();
    static List conditions = new ArrayList();
    static List Switch = new ArrayList();
    static List brackets = new ArrayList();
    static List errorLines = new ArrayList();
    
    
    
    static int errors = 0;
        int lexemeNumber = 0;
        static String token = "";
        int line = 1;
        static String matchability = "Matched";
        int isMultipleLinesComment = 0;
        int isSingleLineComment = 0;
        int currentLine = 1;
        int previousLine = 1;
        int newLine = 0;
        char c =' ';
        
        
    
    
    public void Scanner(JTextArea ta, JTable t, String codeFilePath, JLabel l) throws FileNotFoundException, IOException{
        int x = 0;
        int allow = 1;
        DefaultTableModel model = (DefaultTableModel) t.getModel();
        logicOperators.add("&&");
        logicOperators.add("||");
        logicOperators.add("~");
        
        relationalOperators.add("==");
        relationalOperators.add("!=");
        relationalOperators.add(">=");
        relationalOperators.add("<=");
        relationalOperators.add(">");
        relationalOperators.add("<");
        
        arithmaticOperations.add("+");
        arithmaticOperations.add("-");
        arithmaticOperations.add("*");
        arithmaticOperations.add("/");
        
        quotationMarks.add("\"");
        quotationMarks.add("\'");
        
        delimiters.add("@");
        delimiters.add(";");
        
        conditions.add("Whether");
        conditions.add("Do");
        conditions.add("Else");
        
        Switch.add("Check");
        Switch.add("CaseOf");
       
        brackets.add("{");
        brackets.add("}");
        brackets.add("[");
        brackets.add("]");
        brackets.add("(");
        brackets.add(")");
        
        int possibleError = 0;
        
        String lex = "";
        code = new Scanner(new File(codeFilePath));
        int counter = -1;
        int allowArithmatic = 1;
        
        LineNumberReader r = new LineNumberReader(new FileReader(codeFilePath));
        String Line="";
        lexeme = "";
        lex = "";
        while ((Line = r.readLine()) != null) {
            lexemeNumber=0;
            lexeme = "";
            lex = "";
            System.out.println("while1");
            
            
            Scanner code = new Scanner(Line);
            
            while (code.hasNext()) {
                lexeme = "";
                lex="";
                System.out.println("while2");
                
                
                int i = -1;
                int sliceFlag = 0;
                
                lex = code.next();
               
                StringBuilder s = new StringBuilder(lex);
                s.append("ْ");
                lex = String.valueOf(s);
                
                currentLine = r.getLineNumber();
                if(currentLine>previousLine){
                    isSingleLineComment = 0;
                    allow = 1;
                }
                
                while(!lex.isEmpty()){
                    
                    i++;
            
                    if(sliceFlag!=1){
                
                        lexeme = lexeme + lex.charAt(0);
                        StringBuilder sbd = new StringBuilder(lex);
                        sbd.delete(0, 1);
                        lex = String.valueOf(sbd);
                        System.out.println("lex: "+lex);
                        System.out.println("lexeme: "+lexeme);
                        if(freeze == 1){
                            if(!((int)lexeme.charAt(lexeme.length()-1)>=65 && (int)lexeme.charAt(lexeme.length()-1)<=90) && !((int)lexeme.charAt(lexeme.length()-1)>=97 && (int)lexeme.charAt(lexeme.length()-1)<=122) && (int)lexeme.charAt(lexeme.length()-1) != 95 && !((int)lexeme.charAt(lexeme.length()-1)>=48&&(int)lexeme.charAt(lexeme.length()-1)<=57)){
                                freeze=0;
                            }
                            
                        }
                        if(freeze2 == 1){
                            if(!((int)lexeme.charAt(lexeme.length()-1)>=48 && (int)lexeme.charAt(lexeme.length()-1)<=57)){
                                    freeze2=0;
                            }
                        }
                    }
                    
                    sliceFlag = 0;
                    
                    if(isMultipleLinesComment!=1 && isSingleLineComment!=1){
                        
                        if('/'==lexeme.charAt(lexeme.length()-1) && lex.charAt(0) == '-'){
                            
                            isSingleLineComment = 1;
                            previousLine = r.getLineNumber();
                            counter++;
                            lexemeNumber++;
                            model.insertRow(counter, new Object[]{String.valueOf(r.getLineNumber()), "/-", "Comment", String.valueOf(lexemeNumber), "Matched"});
                            
                        }
                        else if('/'==lexeme.charAt(lexeme.length()-1) && lex.charAt(0) == '#'){
                            isMultipleLinesComment = 1;
                            counter++;
                            lexemeNumber++;
                            model.insertRow(counter, new Object[]{String.valueOf(r.getLineNumber()), "/#", "Comment", String.valueOf(lexemeNumber), "Matched"});
                            
                            System.out.println("is:"+isMultipleLinesComment);
                        }
                        else if(logicOperators.contains(lexeme)){
                            token = "Logic Operator";
                            
                        }
                        else if(relationalOperators.contains(lexeme)){
                            token = "Relational Operator";
                            

                        }
                        else if(arithmaticOperations.contains(lexeme)){
                            
                            token = "Arithmatic Operation";
                            
                        }
                        
                        else if(quotationMarks.contains(lexeme)){
                            token = "Quotation Mark";
                            
                        }
                        else if(delimiters.contains(lexeme)){
                            token = "Delimiter";
                            
                        }
                        else if(brackets.contains(lexeme)){
                            token = "Brackets";
                            
                        }
                        else if(conditions.contains(lexeme)){
                            token = "Condition";
                            
                        }
                        else if(Switch.contains(lexeme)){
                            token = "Switch";
                            
                        }

                        else if(isNumber(lexeme) == 1 || isNumber(lexeme) == 2){
                            token = "Constant";
                            
                            //possibleError = 1;
                        }
                        else if(assignmentOperator.equals(lexeme)){
                            token = "Assignment Operator";
                            
                        }
                        else if(accessOperator.equals(lexeme)){
                            token = "Access Operator";
                            System.out.println((int)lex.charAt(0));
                            if( (int)lex.charAt(0)>=48 && (int)lex.charAt(0)<=57){
                                token = "Error";
                                freeze2 = 1;
                                errorLines.add(r.getLineNumber());
                                errors++;
                                matchability = "Not Matched";
                            }
                        }

                        else if(brace.equals(lexeme)){
                            token = "Brace";
                            
                            
                        }
                        else if(inclusion.equals(lexeme)){
                            token = "Inclusion";
                            
                        }
                        else if(Class.equals(lexeme)){
                            token = "Class";
                            
                        }
                        else if(inheritance.equals(lexeme)){
                            token = "Inheritance";
                            
                        }
                        else if(integer.equals(lexeme)){
                            token = "Integer";
                            
                        }
                        else if(sInteger.equals(lexeme)){
                            token = "SInteger";
                            
                        }
                        else if(character.equals(lexeme)){
                            token = "Character";
                            
                        }
                        else if(string.equals(lexeme)){
                            token = "String";
                            
                        }else if(Float.equals(lexeme)){
                            token = "Float";
                            
                        }
                        else if(sFloat.equals(lexeme)){
                            token = "SFloat";
                            
                        }
                        else if(Void.equals(lexeme)){
                            token = "Void";
                            
                        }
                        else if(Loop.equals(lexeme)){
                            token = "Loop";
                            
                        }
                        else if(Return.equals(lexeme)){
                            token = "Return";
                            
                        }
                        else if(Struct.equals(lexeme)){
                            token = "Struct";
                            
                        }
                        else if(startStatement.equals(lexeme)){
                            token = "Start Statement";
                            
                        }
                        else if(endStatement.equals(lexeme)){
                            token = "End Statement";
                            
                        }
                        else if(Break.equals(lexeme)){
                            token = "Break";
                            
                        }
                        else if(isIdentifier(lexeme) == 1){
                            token = "IDENTIFIER";
                        }
                        else{
                            if(i!=0){
                                if(isSingleLineComment!=1 && isMultipleLinesComment!=1){
                                    
                                    int Ascii_sbl = (int)lexeme.charAt(lexeme.length()-1);
                                    
                                    if("".equals(token)){
                                        token = "Error";
                                        errorLines.add(r.getLineNumber());
                                        errors++;
                                        matchability = "Not Matched";
                                    }
                                    else if("Constant".equals(token)){
                                        if((Ascii_sbl>=65 && Ascii_sbl<=90) || (Ascii_sbl>=97 && Ascii_sbl<=122) || Ascii_sbl == 95 ){
                                            token = "Error";
                                            freeze = 1;
                                            errorLines.add(r.getLineNumber());
                                            errors++;
                                            matchability = "Not Matched";
                                            
                                        }
                                        
                                    }
                                    
                                    
                                    if(freeze !=1 && freeze2 !=1){
                                        matchability = "Matched";
                                        if("Error".equals(token)){
                                            matchability = "Not Matched";
                                        }
                                        StringBuilder sbl = new StringBuilder(lexeme);
                                        sbl.delete(lexeme.length()-1, lexeme.length());
                                        lexemeNumber++;
                                        counter++;
                                        
                                        model.insertRow(counter, new Object[]{String.valueOf(r.getLineNumber()), sbl, token, String.valueOf(lexemeNumber), matchability});
                                        lexeme = String.valueOf(lexeme.charAt(lexeme.length()-1));


                                        l.setText(String.valueOf(errors));
                                        sliceFlag = 1;
                                        token = "";
                                        i = -1;
                                    }
                                    
                                    
                                    

                                    
                                    
                                }
                            }
                        }
                        
                    }
                    if(isMultipleLinesComment == 1)
                        if('#'==lexeme.charAt(lexeme.length()-1) && lex.charAt(0) == '/'){
                            isMultipleLinesComment = 0;
                            lex = "";
                            
                            lexemeNumber++;
                            counter++;
                            model.insertRow(counter, new Object[]{String.valueOf(r.getLineNumber()), "#/", "Comment", String.valueOf(lexemeNumber), "Matched"});
                                
                        }
                }
            }
        }
    }

    static int isNumber(String s)
    {
       int res, i = 0, chAscii, flagFloat = 0, fractCounter = 0;
       char ch ;
       while (i < s.length())
       {
           ch = s.charAt(i);
           chAscii = (int) ch;
           if(i != 0)
           {
               if((chAscii >=48 && chAscii <= 57) || chAscii == 46)  
               {
                    if (chAscii == 46) {
                        flagFloat = 1;
                        fractCounter++; // Must be one fractional point
                    }
                }
                else
                    return 0; // Not Number
           }
           else
           {
               if(chAscii >=48 && chAscii <= 57)  
               {
                    if (chAscii == 46) {
                        flagFloat = 1;
                        fractCounter++; // Must be one fractional point
                    }
                }
                else
                    return 0; // Not Number
           }
           
           i++;
       }
       
       if(flagFloat == 1) {
           if(fractCounter == 1)
               res = 1; // Float
           else
               res = 0; // Not Valid
       }
           
       else
            res = 2; // Int
       
       return res;
   }
    
    static int isIdentifier(String lex){
        int chAscii0 = (int)lex.charAt(0), chAsciiR=0;
        int firstCharacter=0;
        int restOfCharacters=0;
        int isIdentifier = 0;
        
        if(lex.length() == 1){
            if( (chAscii0>=65 && chAscii0<=90) || (chAscii0>=97 && chAscii0<=122) || chAscii0 == 95 || (chAsciiR>=48&&chAsciiR<=57)){
                firstCharacter =1;
                restOfCharacters =1;
            }
        }
        else{
            if( (chAscii0>=65 && chAscii0<=90) || (chAscii0>=97 && chAscii0<=122) || chAscii0 == 95 || (chAsciiR>=48&&chAsciiR<=57)){
                firstCharacter =1;
                restOfCharacters =1;
            }
            for(int i = 1;i<lex.length();i++){
            chAsciiR = (int)lex.charAt(i);
            if( (chAsciiR>=65 && chAsciiR<=90) || (chAsciiR>=97 && chAsciiR<=122) || chAsciiR == 95 || (chAsciiR>=48&&chAsciiR<=57) ){
                restOfCharacters = 1;
            }
            else{
                restOfCharacters = 0;
                break;
            }
        }
            
        }
        
        
        if(firstCharacter == 1 && restOfCharacters == 1){
            isIdentifier = 1;
        }
        return isIdentifier;
    }

    
   }
    
                

    
   



