package listFileJ;

public class bTree extends bNode {
	bNode root;
	bNode left;
	bNode right;
	
	
	public void addNode(String data1){
		if (root.data == null){
			root = new bNode(data1);
			left = new bTree();
			right =  new bTree();
		}else if (data1.compareTo(root.data)<0)
			{
				((bTree) left).addNode(data1);
			}
		else
		{
				((bTree) right).addNode(data1);
		}
	}
	public void inorder(bNode root){
		if ((bTree)left.root != null)	inorder(root.left);
		System.out.print(root.data);
		((Object) root).push(root.data);
		if (root.right != null)	inorder(root.right);
		
	}
}
