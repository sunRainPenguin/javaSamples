import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;



public class AVLTreeNode {
	private int data;
	private int height;
	private AVLTreeNode left;
	private AVLTreeNode right;
	public AVLTreeNode(int data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public AVLTreeNode getLeft() {
		return left;
	}
	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}
	public AVLTreeNode getRight() {
		return right;
	}
	public void setRight(AVLTreeNode right) {
		this.right = right;
	}
	
	/**
	 * 计算root为根结点的树的高度，一个结点高度为0
	 * @param root
	 * @return
	 */
	private static int Height(AVLTreeNode root) {
		if(root==null) {
			return -1;
		}
		else {
//			int newH = Math.max(Height(root.getLeft()), Height(root.getRight()))+1;
//			root.setHeight(newH);	//在对相应的结点高度变化时，直接在变化的位置使用setHeight
			return root.getHeight();
		}
	}
	
	/**
	 * 打印树
	 * @param root
	 * @throws IOException 
	 */
	private static void printTree(AVLTreeNode root, String pngName) throws IOException {  
		File file = new File("graph.dot");
		Writer writer = new FileWriter(file);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("digraph g{\n");
		stringBuffer.append(traverseTree(root));
		stringBuffer.append("}\n");
		writer.write(stringBuffer.toString());
		writer.flush();
		Runtime runtime = Runtime.getRuntime(); 
		String[] cmd1 = {"D:\\Graphviz2.38\\bin\\dot.exe","graph.dot","-Tpng","-o",pngName};
		runtime.exec(cmd1);
		writer.close();
	}
	
	
	
	/**
	 * 遍历树，构造.dot文件
	 * @param root
	 * @return
	 */
	private static String traverseTree(AVLTreeNode root) {
		if(root==null) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		if(root.getLeft()!=null) {
			stringBuffer.append(root.getData() + "->" + root.getLeft().getData() + "[color=red]\n");
			stringBuffer.append(traverseTree(root.getLeft()));
		}
		if(root.getRight()!=null) {
			stringBuffer.append(root.getData() + "->" + root.getRight().getData() + "\n");
			stringBuffer.append(traverseTree(root.getRight()));
		}
		return stringBuffer.toString();
	}
	
	/**
	 * LL旋转
	 * @param X
	 * @return
	 */
	private static AVLTreeNode singleRotateLeft(AVLTreeNode x) {
		AVLTreeNode w = x.getLeft();
		x.setLeft(w.getRight());
		w.setRight(x);
		
		x.setHeight(Math.max(Height(x.getLeft()), Height(x.getRight()))+1);
		w.setHeight(Math.max(Height(w.getLeft()), Height(w.getRight()))+1);
		return w;
	}
	
	/**
	 * RR旋转
	 * @param w
	 * @return
	 */
	private static AVLTreeNode singleRotateRight(AVLTreeNode w) {
		AVLTreeNode x = w.getRight();
		w.setRight(x.getLeft());
		x.setLeft(w);
		
		x.setHeight(Math.max(Height(x.getLeft()), Height(x.getRight()))+1);
		w.setHeight(Math.max(Height(w.getLeft()), Height(w.getRight()))+1);
		return x;
	}
	
	/**
	 * LR旋转
	 * @param z
	 * @return
	 */
	private static AVLTreeNode doubleRotatewithLeft(AVLTreeNode z) {
		z.setLeft(singleRotateRight(z.getLeft()));
		return singleRotateLeft(z);
	}
	
	/**
	 * RL旋转
	 * @param z
	 * @return
	 */
	private static AVLTreeNode doubleRotatewithRight(AVLTreeNode z) {
		z.setRight(singleRotateLeft(z.getRight()));
		return singleRotateRight(z);
	}
	
	/**
	 * 在根结点为root的树中插入值data
	 * @param root
	 * @param data
	 * @return
	 */
	private static AVLTreeNode insert(AVLTreeNode root,int data) {
		if(root==null) {
			root = new AVLTreeNode(data);
			root.setHeight(0);	// 假设只有一个结点时，树高为0
			root.setLeft(null);
			root.setRight(null);
		}
		else if(data<root.getData()) {
			root.setLeft(insert(root.getLeft(),data));
			if(Height(root.getLeft())-Height(root.getRight()) == 2) {
				 if(data<root.getLeft().getData()) {
					 root = singleRotateLeft(root);
				 }
				 else {
					 root = doubleRotatewithLeft(root);
				 }
			}
		}
		else if(data>root.getData()) {
			root.setRight(insert(root.getRight(),data));
			if(Height(root.getRight())-Height(root.getLeft()) == 2) {
				if(data>root.getRight().getData()) {
					root = singleRotateRight(root);
				}
				else {
					root = doubleRotatewithRight(root);
				}
			}
		}
		root.setHeight(Math.max(Height(root.getLeft()), Height(root.getRight()))+1);
		return root;
	}
	
	/**
	 * main函数
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		AVLTreeNode n6 = new AVLTreeNode(6);
//		AVLTreeNode n5 = new AVLTreeNode(5);
//		AVLTreeNode n3 = new AVLTreeNode(3);
//		AVLTreeNode n8 = new AVLTreeNode(8);
//		AVLTreeNode n7 = new AVLTreeNode(7);
//		AVLTreeNode n9 = new AVLTreeNode(9);
//		n6.setLeft(n5);
//		n5.setLeft(n3);
//		n6.setRight(n8);
//		n8.setLeft(n7);
//		n8.setRight(n9);
// 不能按照以上方式插入树，因为这样就无法更新height
		AVLTreeNode root = insert(null,6);
		root = insert(root,4);
		root = insert(root,2);
		root = insert(root,5);
		root = insert(root,7);
		root = insert(root,8);
		printTree(root, "png1");
	}
}
