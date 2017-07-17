import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeNode {
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	public int getData() {
		return this.data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public BinaryTreeNode getLeft() {
		return this.left;
	}
	
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	
	public BinaryTreeNode getRight() {
		return this.right;
	}
	
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	// 由于使用递归的遍历方式比较简单，所以这里回顾一下三种遍历方式
	/**
	 * 前序遍历
	 * @param root
	 */
	public void preOrderNonRecursive(BinaryTreeNode root) {
		if(root==null) {
			return;
		}
		Stack<BinaryTreeNode> S = new Stack<BinaryTreeNode>();
		while(true) {
			// root先输出，先入栈，接着left入栈，直到没有left位置
			while(root!=null) {
				System.out.println(root.getData());
				S.push(root);
				root = root.left;
			}
			
			// 如果栈为空，则直接返回
			if(S.isEmpty()) {
				break;
			}
			
			// 右子树入栈
			root = S.pop();
			root = root.getRight();
		}
		return;
	}
	
	/**
	 * 中序遍历
	 * @param root
	 */
	public void inOrderNonRecursive(BinaryTreeNode root) {
		if(root==null) return;
		Stack<BinaryTreeNode> S = new Stack<BinaryTreeNode>();
		while(true){
			// root及left先入栈
			while(root!=null) {
				root = root.left;
				S.push(root);
			}
			if(S.isEmpty()) {
				break;
			}
			// 输出当前节点
			root = S.pop();
			System.out.println(root.data);
			// 获得右子树
			root = root.right;
		}
		return;
	}
	
	/**
	 * 后序遍历
	 * @param root
	 */
	public void postOrderNonRecursive(BinaryTreeNode root) {
		Stack<BinaryTreeNode> S = new Stack<BinaryTreeNode>();
		while(true) {
			if(root!=null) {
				root = root.left;
				S.push(root);
			}
			else {// 已经遍历到叶子节点
				if(S.isEmpty()) {
					System.out.println("Stack is empty");
					break;
				}
				else {
					if(S.peek().right==null) {
						// 输出右节点
						root = S.pop();
						System.out.println(root.getData());
						if(root==S.peek().getRight()) {
							System.out.println(S.peek().getData());
							S.pop();
						}
					}
				}
				if(!S.isEmpty()) {
					root = S.peek().getRight();
				}
				else {
					root=null;
				}
			}
		}
		S.clear();
	}
	
	/**
	 * 按照树的层次访问
	 * @param root
	 */
	public void levelOrder(BinaryTreeNode root) {
		BinaryTreeNode temp;
		Queue<BinaryTreeNode> Q = new LinkedList<BinaryTreeNode>();
		if(root==null) {
			return;
		}
		Q.add(root);
		while(!Q.isEmpty()) {
			temp = Q.poll();
			System.out.println(temp.data);
			if(temp.left != null) {
				Q.add(temp.left);
			}
			if(temp.right!=null) {
				Q.add(getRight());
			}
		}
		Q.clear();
	}
}
