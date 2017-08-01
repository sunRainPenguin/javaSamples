package binarySearchTree;

import java.awt.PageAttributes.PrintQualityType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.Principal;

public class BinarySearchTreeNode {
	private int data;
	private BinarySearchTreeNode left;
	private BinarySearchTreeNode right;
	
	public BinarySearchTreeNode(int data) {
		// TODO Auto-generated constructor stub
		this.data = data;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BinarySearchTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinarySearchTreeNode left) {
		this.left = left;
	}
	public BinarySearchTreeNode getRight() {
		return right;
	}
	public void setRight(BinarySearchTreeNode right) {
		this.right = right;
	}

	/**
	 * 打印树
	 * @param root
	 * @throws IOException 
	 */
	public static void printTree(BinarySearchTreeNode root) throws IOException {
		Runtime runtime = Runtime.getRuntime();  
		File file = new File("graph.dot");
		Writer writer = new FileWriter(file);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("digraph g{\n");
		stringBuffer.append(traverseTree(root));
		stringBuffer.append("}\n");
		writer.write(stringBuffer.toString());
		writer.flush();
	}
	
	/**
	 * 遍历树，构造.dot文件
	 * @param root
	 * @return
	 */
	public static String traverseTree(BinarySearchTreeNode root) {
		if(root==null) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		if(root.getLeft()!=null) {
			stringBuffer.append(root.getData() + "->" + root.getLeft().getData() + "[color=\"#DDBCBC\"]\n");
			stringBuffer.append(traverseTree(root.getLeft()));
		}
		if(root.getRight()!=null) {
			stringBuffer.append(root.getData() + "->" + root.getRight().getData() + "\n");
			stringBuffer.append(traverseTree(root.getRight()));
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 查找数据为data的节点，使用递归方法比较简单，因此这里采用非递归
	 * @param root
	 * @param data
	 * @return
	 */
	public static BinarySearchTreeNode find(BinarySearchTreeNode root, int data) {
		if(root==null) {
			return null;
		}
		while(root!=null) {
			if(data<root.getData()) {
				root = root.getLeft();
			}
			else if(data>root.getData()) {
				root = root.getRight();
			}
			else {
				return root;
			}
		}
		return null;
	}
	
	/**
	 * 在二叉搜索树中插入数据
	 * @param root
	 * @param data
	 * @return
	 */
	public static BinarySearchTreeNode insert(BinarySearchTreeNode root, int data) {
		 if(root==null) {
			 root = new BinarySearchTreeNode(data);
			 root.setLeft(null);
			 root.setRight(null);
		 }
		 else {
			 if(data<root.getData()) {
				 root.setLeft(insert(root.getLeft(),data));
			 }
			 else if(data>root.getData()) {
				 root.setRight(insert(root.getRight(),data));
			 }
		 }
		 return root;
	}
	
	/**
	 * main函数
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BinarySearchTreeNode n10 = new BinarySearchTreeNode(10);
		BinarySearchTreeNode n6 = new BinarySearchTreeNode(6);
		BinarySearchTreeNode n16 = new BinarySearchTreeNode(16);
		BinarySearchTreeNode n4 = new BinarySearchTreeNode(4);
		BinarySearchTreeNode n9 = new BinarySearchTreeNode(9);
		BinarySearchTreeNode n13 = new BinarySearchTreeNode(13);
		BinarySearchTreeNode n7 = new BinarySearchTreeNode(7);
		n10.setLeft(n6);
		n10.setRight(n16);
		n6.setLeft(n4);
		n6.setRight(n9);
		n9.setLeft(n7);
		n16.setLeft(n13);
		printTree(n10);
		Runtime runtime = Runtime.getRuntime(); 
		String[] cmd1 = {"D:\\Graphviz2.38\\bin\\dot.exe","graph.dot","-Tpng","-o","example3.png"};
		runtime.exec(cmd1);
		
//		BinarySearchTreeNode result = find(n10,9);
//		System.out.println(result.getData() + " " + result.getLeft().getData() );
//		
		BinarySearchTreeNode result = insert(n10, 5);
		System.out.println(result.getData() + " " + result.getLeft().getData() );
		printTree(n10);
		String[] cmd2 = {"D:\\Graphviz2.38\\bin\\dot.exe","graph.dot","-Tpng","-o","example4.png"};
		runtime.exec(cmd2);
	}
}
