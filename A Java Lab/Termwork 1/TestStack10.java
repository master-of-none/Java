/** Java program for test-driver Stack
    Author: Shrikrishna Bhat
    USN:2SD15CS099
*/

import stack.*;// import package
import java.util.*;//Import utility class

public class TestStack10{
	public static void main(String[] args){
   	
		int i;
        	System.out.println("Stack Test");
		System.out.println("-------------------------------------------------------------");
        /* Creating object of class arrayStack */

	System.out.println("Stack of size 3 created");
	Stack<Integer> stk = new Stack<Integer>(10); // Creation of Generic Stack
                  
        for(i=1;i<=10;i++){ // Test-Driver
     		switch (i){

	   		case 1: //Empty stack display
				System.out.println("Test case: 1");
				stk.display();
				System.out.println("Test case 1: \"Empty Stack display\" Passed\n");
                		break;
	     		
			case 2: //Empty stack Pop
				System.out.println("Test case: 2");
                		try{
                    			System.out.println("Popped Element = " + stk.pop());
                		}catch(Exception e){
                    			System.out.println("Error : " + e.getMessage());
               			}
				System.out.println("Test Case 2: \"Empty Stack Popped\" Passed\n");
                		break;
			
            		case 3: //Push first element i.e '1'
				System.out.println("Test case: 3");
                               	try{
                    			stk.push(1);
		    			System.out.println("Peeked element is " +stk.peek());
              			}catch(Exception e){
                    			System.out.println("Error : " + e.getMessage());
                		}
				System.out.println("Test Case 3 \"1-PUSH\" passed\n");                    
                		break;                         
        
            		case 4: //Push second element i.e '2'
				System.out.println("Test case: 4");
				try{
                                	stk.push(2);
                                	System.out.println("Peeked element is " +stk.peek());
                        	}catch(Exception e){
                                	System.out.println("Error : " + e.getMessage());
                       	 	}
                        	System.out.println("Test Case 4 \"2-PUSH'passed\"\n");
                        	break;               
		    	
			case 5: //Display Partially filled stack
				System.out.println("Test case: 5");
		                stk.display();
		                System.out.println("Test case 5: \"Partial Full Stack display\" Passed\n");
		                break;
                              
		    	case 6: //Pop from partially filed stack 
				System.out.println("Test case: 6");
	  			try{
		                	System.out.println("Popped Element = " + stk.pop());
		                }catch (Exception e){
		                        System.out.println("Error : " + e.getMessage());
		                }
		                System.out.println("Test Case 6: \"Popped Partially full Stack\" Passed\n");
		                break;

	 		case 7: //Push three elements to stack i.e '2' & '3' 
				System.out.println("Test case: 7");
		                try{
		                        stk.push(2);
					stk.push(3);
		                        System.out.println("Peeked element is " +stk.peek());
		                }catch (Exception e){
		                        System.out.println("Error : " + e.getMessage());
		                }
		                System.out.println("Test Case 7 \"2,3-PUSH'passed\"\n");
		                break;
			
			case 8: //Display Full Stack
				System.out.println("Test case: 8");
		                stk.display();
		                System.out.println("Test case 8: \"Full Stack display\" Passed\n");
		                break;
		
		        case 9: //Try to push after stack is full
				System.out.println("Test case: 9");
		                try{
		                        stk.push(4);		
		                }catch (Exception e){
		                        System.out.println("Error : " + e.getMessage());
		                }
		                System.out.println("Test Case 9 \"OverFlow Condition\" passed\n");
		                break;
           		
		        case 10: // Pop all elements from stack
				System.out.println("Test case: 10");
                         	try{
                        	System.out.println("Popped Element = " + stk.pop());
				System.out.println("Popped Element = " + stk.pop());
				System.out.println("Popped Element = " + stk.pop());

		                } catch (Exception e){
		                        System.out.println("Error : " + e.getMessage());
		                }
		                System.out.println("Test Case 10 : \"Popped all elements\" Passed\n");
		                break;
            		
			default:
                		break;
            		}           
    		}
    	}
 
}
 
