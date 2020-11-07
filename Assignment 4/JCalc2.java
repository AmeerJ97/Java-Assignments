public class JCalc {
static class Element{ //ListNode.Java
	String info;
	Element next;
}
static class Stack{ // Stack.Java Submission
	Element stackbuffer=null;
	public void push(String add){//method to (push function) add a string to stack
		Element newnode=new Element();
		newnode.next=stackbuffer;
		newnode.info=add;	
		stackbuffer=newnode;
	}
	public String pop(){//method to remove a string from the stack
		if (stackbuffer!=null){
			String elem= stackbuffer.info;
			stackbuffer=stackbuffer.next;
			return elem	;
		}else
			return "";
	}
	public String top(){//method to check top of the stack (returns the last element in the stack)
		if (stackbuffer!=null){
			String elem= stackbuffer.info;
			return elem;
		}else
			return "";
	}
	public boolean empty(){//method to check if the stack is empty
		return (stackbuffer == null);
	}
	
}
static class Queue{// Queue.Java Submission
	Element queuebuffer=null;
	Element prear=null;
	public void enqueue(String add){//method to add elements (strings) to queue
		Element newnode = new Element();
		newnode.info = add;
		newnode.next = null;
		if (prear != null){
			prear.next=newnode;
		} 
		else {
			queuebuffer=newnode;
		}
		prear=newnode;		
	}
	public String dequeue(){ //method to remove elements (strings) from queue
		if (queuebuffer != null){
			String elem = queuebuffer.info;
			queuebuffer = queuebuffer.next;
			if (queuebuffer == prear){
				prear = null;
			}
			return elem;
		}
		else{
			return "";
		}
	}
	public boolean isempty(){
		return (queuebuffer == null);
	}
}
public static boolean isOperator(String check){//to check if the given element is an operator hence the name isOperator
	return (
			   check.equals("+") //checks if the input string called check is addition
			|| check.equals("-") //checks if the input string called check is subtraction
			|| check.equals("x")//checks if the input string called check is multiplication
			|| check.equals("/")//checks if the input string called check is division
			);
}
public static int prec(String check){//to set precedence of multiplication and division higher than addition and multiplication
	if (check.equals("x") || check.equals("/")){
		return 1;
	}
	else if (check.equals("-") || check.equals("+")){
		return 0;
	}
	else 
		return -1;
}
public static boolean hasHigherPrec(String s1, String s2){ //to check precedence of input strings
	return (prec(s1) >= prec(s2));//using the prec method to check if string 1(s1) has higher or equal precedence than string 2(s2)
	
}
public static void feedIn(String[] args, Queue inputQ){ //feeding strings from args to input queue
	int i = 0;//declaration of int i for the for loop
	for (i = 0; i < args.length; i++){//forloop that runs to add elements from args(input) to inputQ
		inputQ.enqueue(args[i]);
	}
}
public static void shuntingYard(Queue inputQ,Stack opStack, Queue outputQ,int length){//shunting yard algorithm 
	int i=0;
	String exp;
	for (i=0; i < length; i++ ){
		exp=inputQ.dequeue();// exp is fed strings from inputQ then is dequeued from inputQ
		if (!isOperator(exp)){//if exp is not an operator hence is an operand
			outputQ.enqueue(exp);//then add exp to the output queue
		}
		else {
			while (!opStack.empty() && hasHigherPrec(opStack.top(),exp))//if the opStack is not empty and the top of opStack has higher or equal to precedence as exp
			{
				outputQ.enqueue(opStack.top()); //then add the top of the opStack to the outputQ
				opStack.pop();//remove an element from the opStack
			}
			opStack.push(exp);//add exp to the opStack
		}
	}
	while (!opStack.empty()){//while the opStack is not empty -->
		outputQ.enqueue(opStack.top());//add top of the opStack to the outputQ
		opStack.pop();//remove the element(the one added to the output queue) from the opStack
	}
}
public static void printOut(Queue outputQ){//method to print output
	String exp;//declaration of string exp
	while ((exp = outputQ.dequeue()) != ""){//while the exp string (which is fed from the outputQ as the outputQ is dequeued) is not empty
		System.out.print(exp + " ");//print out exp and a space between printed elements
	}
	System.out.print("\n");//newline
} 
public static String calculate(Queue outputQ){//method to calculate postfix(static since calculator is being used only once)
	Stack ansStack = new Stack();//creating a stack to hold the answer
	while (!outputQ.isempty()){//checking if the output queue is not emtpy (and if its not empty then -->)
		String token = outputQ.dequeue();//creating a string called token to hold output queue via dequeue method
		if (!isOperator(token)){ //if its not an operator then its a number
			ansStack.push(token);//then add the number to the token string
		}
		else {
			String op2 = ansStack.pop();//removing the 2nd number from the ansStack and onto op2
			String op1 = ansStack.pop();//removing the 1st number from the ansStack and onto op1	
			ansStack.push(evaluate (token, op1, op2));//using the evaluate method to calculate and then adding the answer to the ansStack
		}
	}
return (ansStack.top());//return the answer which is ontop of the ansStack
}
public static String evaluate (String token, String op1, String op2){//evaluate method to test the operator 'token' is holding and to perform the corresponding calculation
	float op1f = Float.parseFloat(op1);//declaring a new variable called op1f which is the float version of the op1 
	float op2f = Float.parseFloat(op2);//declaring a new variable called op2f which is the float version of the op2 
	if (token.equals("+")){//if the token is addition then 
		return (Float.toString(op1f + op2f));//then perform addition on op1f and op2f
	}
	else if (token.equals("-")){//if the token is subtraction
		return (Float.toString(op1f - op2f));//else then perform subtraction on op1f and op2f
		}
	else if (token.equals("/")){//if the token is division
		return (Float.toString(op1f / op2f));//else then perform division on op1f and op2f
	}
	else if (token.equals("x")){//if the token is x( not multiplication as required by the assignment)
		return (Float.toString(op1f * op2f));//then perform multiplication on op1f and op2f(note that its not x but rather a * since that is what java interpretes as multiplication
	}
	else 
		return "";//if its not multiplication, division, addition, or subtraction else return null (ie unidentified operator such as '^' or '(' )
	
}
	public static void main(String[] args) {//main function
		Queue inputQ = new Queue();//declaration of the inputQ to get input from args through the feedin method 
		Queue outputQ = new Queue();//declaration of the outputQ to hold postfix
		Stack opStack = new Stack();//declaration of the opStack to hold operators 
		feedIn(args,inputQ);//using the feedIn method to feed inputQ from args
		shuntingYard(inputQ,opStack,outputQ,args.length);//using the shunting yard algorithm 
		for (int i=0;i<args.length;i++){//for loop to print out the infix through args	
			System.out.print(args[i] + " ");//printing out all the input one by one with spaces inbetween
		}
		System.out.println("= " + calculate (outputQ));//print out an equal sign before the printing of the value so that ''infix=answer'' is printed
		
		
	}

}
