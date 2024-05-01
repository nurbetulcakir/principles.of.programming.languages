import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class LexicalAnalyser {

	public static boolean isValidDelimiter(char char1) {
		if(char1 == ' ' ||char1 == '+' || char1 == '-' || char1 == '*' ||
		   char1 == '/' || char1 == ';' || char1 == '>' || char1 == '\n' || 
		    char1 == '=' || char1 == '(' || char1 == ')' ||
		   char1 == '[' || char1 == ']' || char1 == '{' || char1 == '}') {
			return true;
		}
		return false;
	}
	
	
	public static boolean isValidOperator(char ch) {
		boolean a = false;
		if(ch == '+' || ch == '-' || ch=='|'||ch == '*' || ch == '/' ||
				ch == '>' || ch=='%' || ch == '<' || ch=='^' ||ch == '=') {
			a= true;
		}
		a= false;
		return a;
	}
	
	
	public static boolean isvalidIdentifier(String str){  // everything except numbers and newline and things starting with a delimiter
		if (str.charAt(0) == '0' || str.charAt(0) == '1' || str.charAt(0) == '2' ||
		str.charAt(0) == '3' || str.charAt(0) == '4' || str.charAt(0) == '5' ||
		str.charAt(0) == '6' || str.charAt(0)== '7' || str.charAt(0) == '8' ||
		str.charAt(0) == '9' || str.charAt(0)== '\n' || isValidDelimiter(str.charAt(0)) == true) {
			return (false);
		}
		   
		return (true);
	}
	
	
	public static boolean isValidKeyword(String str) {
		if(str.contains("if") || str.contains("begin") || str.contains("cond") ||
				str.contains("let") || str.contains("define") ) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidBoolean(String str)  {  //****NEW****
		if(str.contains("true") || str.contains("false") ) {
			return true;
		}
		return false;
	}
	
	public static boolean isValidComment(String str)  {  
		if(str.charAt(0) == '~') {  // except it's a character or a string
			return true;
		}
		return false;
	}
	public static boolean isValidString(String str)  {//****NEW****
		
		
		return true;
	}
	public static boolean isValidCharacter(String str)  {  //****NEW****
		return true;
	}
	
	public static boolean isValidNumber(String str) {
		   int i, len = str.length();
		   
		   if (str.length() == 1) {
			   return false;
		   }
		   
		   for (i = 0; i < len-1; i++) {
		      
			   
			   
			   if (Character.isDigit(str.charAt(i)) == false && str.charAt(i) != '-' && str.charAt(i) != '+' && str.charAt(i) != 'e' && str.charAt(i) != 'E' && str.charAt(i) != '.') {
				 
				   return false;
			   }
			   
		      
		   }
		   return true;
		}

	
	public static String subString(String str, int left, int right) {
		String subStr="";
		for(int i = left; i <= right; i++) {
			String a;
			a=subStr.substring(0, i-left)+str.charAt(i);
			subStr=a;
		}
		String bString;
		bString=subStr.substring(0, right-left+1)+'\0';
		
		subStr=bString;
		
		return subStr;
	}
	
	
	static void detectTokens(String str) {
		   int left = 0, right = 0, columnNumber = 1, rowNumber = 1;
		   int len = str.length();
		   while (right < len && left <= right) {
			   
			   if (str.charAt(right) == '\n') {
			    	  
			    	  rowNumber++;
			    	  columnNumber = 1;
			    	  
			      }
			   if (str.charAt(right) == ' ') {
			    	  
			    	  columnNumber++;
			    	  
			      }
			   
		      if (isValidDelimiter(str.charAt(right)) == false) {
		    	  
		    	  right++;
		    	  
		      }
		      
		      
		      String subStr = subString(str, left, right - 1);
		      if (isValidDelimiter(str.charAt(right)) == true && left == right) {
		    	  if(str.charAt(right)!=' ' && str.charAt(right)!='\n' && isValidOperator(str.charAt(right))==false)
		    		 
		    		  if(str.charAt(right)=='(') {
		    			  
		    			  System.out.println("LEFTPAR " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    		  else if(str.charAt(right)==')') {
		    			  
		    			  System.out.println("RIGHTPAR " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    		  else if(str.charAt(right)=='[') {
		    			  
		    			  System.out.println("LEFTSQUAREB " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    		  else if(str.charAt(right)==']') {
		    			  
		    			  System.out.println("RIGHTSQUAREB " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    		  else if(str.charAt(right)=='{') {
		    			  
		    			  System.out.println("LEFTCURLYB " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    		  else if(str.charAt(right)=='}') {
		    			 
		    			  System.out.println("RIGHTCURLYB " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		    	  	
		    		  else if (isValidNumber(subStr) == true ) {
				        	 
				        	 System.out.println("NUMBER " + rowNumber + ":" + columnNumber);
				        	 columnNumber += (subStr.trim()).length();
				         }
		    		  else {
		    			  System.out.println("IDENTIFIER " + rowNumber + ":" + columnNumber);
		    			  columnNumber++;
		    		  }
		         
		    	  if (isValidOperator(str.charAt(right)) == true && str.length() == 1) {  // Operator
			        	 columnNumber++;
			        	 System.out.println("IDENTIFIER " + rowNumber + ":" + columnNumber);
			         }
		    	  
		    	  
		         
		         right++;
		         int a = right;
		         left = a;
		         //columnNumber = left + 1;
		         
		      } 
		      
		      else if (isValidDelimiter(str.charAt(right)) == true && left != right || (right == len && left != right)) {
		   
		         subStr = subString(str, left, right - 1);

		         
		         if (isValidKeyword(subStr) == true && isValidOperator(str.charAt(right))==false && isValidDelimiter(str.charAt(right))==true) {
		        	 
		        	 System.out.println(subStr.toUpperCase() + rowNumber + ":" + columnNumber);
		        	 columnNumber += (subStr.trim()).length();
		         }
		         else if (isValidNumber(subStr) == true) {
		        	 
		        	 System.out.println("NUMBER " + rowNumber + ":" + columnNumber);
		        	 columnNumber += (subStr.trim()).length();
		         }
		         else if (isValidComment(subStr) == true) {
		        	 
		        	 columnNumber = 1;
		 
		        	 for(int i = 0; i < str.length(); i++) {
		        		 right++;
		        		 if (str.charAt(right) == '\n') {
		        			 break;
		        		 }
		        	 }
		        	 
		        	 left = right;
		        	 
		        	 
		        	
		         }
		         
		         else if (isValidBoolean(subStr) == true) {  // ****NEW**** 
		        	 
		        	 System.out.println("BOOLEAN " + rowNumber + ":" + columnNumber);
		        	 columnNumber += (subStr.trim()).length();
		         }
		         else if (isvalidIdentifier(subStr) == true && isValidDelimiter(str.charAt(right-1)) == false) {
		        	 // identifier but not delimiter
		        	 
		        	 System.out.println("IDENTIFIER " + rowNumber + ":" + columnNumber);
		        	 columnNumber += (subStr.trim()).length();
		        	 
		         }
		         else if (isvalidIdentifier(subStr) == false && isValidDelimiter(str.charAt(right-1)) == false) {
		        	 
		        	 System.out.println("INVALID "+subStr);
		        	 columnNumber += (subStr.trim()).length();
		         }
		         left = right;
		         //columnNumber = left + 1;
		      }
		   }
		   return;
		}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File inputFile = new File("input.txt");
		Scanner lexicalAnalyzer = new Scanner(inputFile);
		
		String str ="";
		
		while(lexicalAnalyzer.hasNextLine()) {
			 str += lexicalAnalyzer.nextLine() + "\n";
		}
		
		PrintStream out = new PrintStream(new File("output.txt"));
		PrintStream console = System.out;
		System.setOut(out);
		LexicalAnalyser.detectTokens(str);
		System.setOut(console);
		LexicalAnalyser.detectTokens(str);
		

	}
	
}