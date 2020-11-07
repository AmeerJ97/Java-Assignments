
public class In2pJ {
static class Element{ //default linked list 
	String info;
	Element next;
}
static class Stack{ //stack
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
			return elem;
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
static class Queue{//default queue
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
	
}
public static boolean isOperator(String check){//to check if the given element is an operator hence the name isOperator
	return (
			   check.equals("+") 
			|| check.equals("-") 
			|| check.equals("x")
			|| check.equals("/")
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
	return (prec(s1) >= prec(s2));//using the prec method
	
}
public static void feedIn(String[] args, Queue inputQ){ //feeding strings from args to input queue
	int i = 0;
	for (i = 0; i < args.length; i++){
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
	while (!opStack.empty()){//while the opStack is not empty
		outputQ.enqueue(opStack.top());//add top of the opStack to the outputQ
		opStack.pop();//remove an element from the opStack
	}
}
public static void printOut(Queue outputQ){//method to print output
	String exp;//declaration
	while ((exp = outputQ.dequeue()) != ""){//while the exp string (which is fed from the outputQ as the outputQ is dequeued) is not empty
		System.out.print(exp + " ");//print out exp and a space between printed elements
	}
	System.out.print("\n");//newline
} 
	public static void main(String[] args) {//main function
		Queue inputQ = new Queue();//declaration of the inputQ
		Queue outputQ = new Queue();//declaration of the outputQ
		Stack opStack = new Stack();//declaration of the opStack
		feedIn(args,inputQ);//using the feedIn method to feed inputQ from args
		shuntingYard(inputQ,opStack,outputQ,args.length);//using the shunting yard algorithm 
		printOut(outputQ);//using the print method
	}

}
