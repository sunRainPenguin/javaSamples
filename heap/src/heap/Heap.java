package heap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Heap {
	public int[] array;
	public int count;
	public int capacity;
	public Heap(int capacity) {
		this.count = 0;
		this.capacity = capacity;
		this.array = new int[capacity];
	}
	
	/**
	 * 获得双亲结点
	 * @param i
	 * @return
	 */
	public int parent(int i) {
		if(i<=0 || i>=this.count) {
			return -1;
		}
		else {
			return (i-1)/2;
		}
	}
	
	/**
	 * 获得左孩子
	 * @param i
	 * @return
	 */
	public int leftChild(int i) {
		int left = 2*i+1;
		if(left>this.count) {
			return -1;
		}
		else {
			return left;
		}
	}
	
	/**
	 * 获得右孩子
	 * @param i
	 * @return
	 */
	public int rightChild(int i) {
		int right = 2*i+2;
		if(right>this.count) {
			return -1;
		}
		else {
			return right;
		}
	}
	
	/**
	 * 堆化当前元素
	 * @param i
	 */
	public void percolateDown(int i) {
		int l,r,max,temp;
		l = leftChild(i);
		r = rightChild(i);
		if(l!=-1 && this.array[l]>this.array[i]) {
			max = l;
		}
		else {
			max = i;
		}
		if(r!=-1 && this.array[r]>this.array[max]) {
			max = r;
		}
		if(max!=i) {
			temp = this.array[i];
			this.array[i] = this.array[max];
			this.array[max] = temp;
			percolateDown(max);
		}
	}
	
	/**
	 * 插入元素
	 * @param data
	 */
	public void insert(int data) {
		int i;
		if(this.count == this.capacity) {
			resizeHeap();
		}
		this.count++;
		i = this.count-1;
		while(data>this.array[(i-1)/2]) {
			this.array[i] = this.array[(i-1)/2];
		}
		this.array[i] = data;
	}
	
	/**
	 * 扩大堆的容量
	 */
	public void resizeHeap() {
		int[] array_old = new int[this.capacity];
		System.arraycopy(this.array, 0, array_old, 0, this.count);
		this.array = new int[this.capacity*2];
		for(int i=0; i<this.capacity;i++) {
			this.array[i] = array_old[i];
		}
		this.capacity = this.capacity*2;
		array_old = null;
	}
	
	/**
	 * 从数组建堆
	 * @param h
	 * @param arr
	 * @param n
	 */
	public static void buildHeap(Heap h, int arr[], int n) {
		if(h==null) {
			return;
		}
		while(n>h.capacity) {
			h.resizeHeap();
		}
		for(int i=0;i<n;i++) {
			h.array[i] = arr[i];
		}
		h.count = n;
		for(int i = (h.count-1)/2;i>=0;i--) {
			h.percolateDown(i);
		}
	}

	/**
	 * 删除堆中的最大元素
	 * @return
	 */
	public int deleteMax() {
		if(this.count==0) {
			return -1;
		}
		int data = this.array[0];
		this.array[0] = this.array[this.count-1];
		this.count--;
		this.percolateDown(0);
		return data;
	}
	
	/**
	 * 堆排序
	 * @param arr
	 * @param n
	 * @throws IOException 
	 */
	public static void heapSort(int arr[], int n) throws IOException {
		Heap h = new Heap(10);
		buildHeap(h, arr, n);
		printHeap(h, "heap.png");
		for(int i = 0; i<n; i++) {
			arr[i] = h.deleteMax();
		}
	}
	
	/**
	 * 打印树
	 * @param root
	 * @throws IOException 
	 */
	private static void printHeap(Heap h, String pngName) throws IOException {  
		File file = new File("graph.dot");
		Writer writer = new FileWriter(file);
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("digraph g{\n");
		stringBuffer.append(traverseHeap(h,0));
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
	private static String traverseHeap(Heap h, int i) {
		if(h==null) {
			return "";
		}
		StringBuffer stringBuffer = new StringBuffer();
		if(h.leftChild(i)>0 && h.leftChild(i)<h.count) {
			stringBuffer.append(h.array[i] + "->" + h.array[h.leftChild(i)] + "[color=red]\n");
			stringBuffer.append(traverseHeap(h, h.leftChild(i)));
		}
		if(h.rightChild(i)>0 && h.rightChild(i)<h.count) {
			stringBuffer.append(h.array[i] + "->" + h.array[h.rightChild(i)] + "\n");
			stringBuffer.append(traverseHeap(h, h.rightChild(i)));
		}
		return stringBuffer.toString();
	}
	
	/**
	 * main函数
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int[] arr = {31,10,9,8,3,1,5,7,16,14,15,19};
		heapSort(arr, arr.length);
	}
}
