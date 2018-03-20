
package stack;// to package stack
import java.util.*;//import util.*

/**
 * Stack class(generic type)
 */
class Stack<T>{ //Generic Stack
   	private int size;
    	private T[] arr;
    	private int top; // top of stack
 
    	/**
     	* Constructor for initializing Array.
     	*/
    	@SuppressWarnings("unchecked")
    	
	public Stack(int size){
          	this.size = size;
           	arr = (T[])new Object[size]; //Creation of Generic Stack Array
           	top = -1; // initialize Stack to with -1
    	}
	
	public void display(){
        	System.out.println("Stack = ");
        
		if (top == -1){
            		System.out.println("Empty");
            	return ;
        	}
        
		for (int i = top; i >= 0; i--)
            		System.out.print(arr[i]+" ");
        	System.out.println();
    	}

 
    	/**
     	* Push items in stack, it will put items on top of Stack.
     	*/
    
	public void push(T value){
           	if(isFull()){
			throw new IndexOutOfBoundsException("Overflow Exception");           
		}
           
		arr[++top] = value;
    	}
 
    	/**
     	* Pop items in stack, it will remove items from top of Stack.
     	*/
    
	public T pop(){
         	if(isEmpty()){
        		throw new NoSuchElementException("Underflow Exception");   
		}
           	return arr[top--]; // remove item and decrement top as well.
    	}
	
	public T peek(){
          	if(isEmpty()){
        		throw new NoSuchElementException("Underflow Exception");   
		}
           	return arr[top]; // remove item and decrement top as well.
    	}

 
    	/**
     	* @return true if Stack is empty
     	*/
    	
	public boolean isEmpty(){
          	return (top == -1);
    	}
 
    	/**
     	* @return true if stack is full
     	*/
    
    	public boolean isFull(){
           	return (top == size - 1);
    	}
 
}
