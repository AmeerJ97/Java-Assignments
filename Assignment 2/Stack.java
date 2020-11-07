package listFileJ;

public class Stack extends listNode{
	public void run(){
		bNode top;
		}
	
	public void push(String arg){
		listNode node = new listNode();
		node.data = arg;
		node.next = top;
		top = node;
	}
	public String pop(){
		if (top.equals(null)) return null;
		String data = top.data;
		top = top.next;
		return data;

}
	public void inorderS(bNode root){
		while (top != null){
			bNode popNode = root.pop();
			System.out.println(popNode.data+" ");
		}
	
	}
	}