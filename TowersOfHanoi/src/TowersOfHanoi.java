import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

public class TowersOfHanoi {
	private static Stack<Integer> initialPos = new Stack<Integer>();
	private static Stack<Integer> auxPos = new Stack<Integer>();
	private static Stack<Integer> desPos = new Stack<Integer>();
	private static FileOutputStream fout;
	private static BufferedOutputStream buff;
	
	private enum Position{
		initial,aux,des;
	}
	
	/**
	 * 初始化圆盘位置
	 */
	private static void init(int n){
		for(int i =n;i>0;i--){
			initialPos.push(i);
		}
	}
	
	/**
	 * 将一个圆盘从fromPeg移动到toPeg
	 * @param fromPeg
	 * @param toPeg
	 */
	private static void moveDisk(Position fromPeg,Position toPeg){
		Stack<Integer> fromStack = new Stack<Integer>();
		Stack<Integer> toStack = new Stack<Integer>();
		// 获得fromStack和toStack
		switch (fromPeg) {
		case initial:
			fromStack = initialPos;
			break;
		case aux:
			fromStack = auxPos;
			break;
		case des:
			fromStack = desPos;
			break;
		default:
			break;
		}
		switch (toPeg) {
		case initial:
			toStack = initialPos;
			break;
		case aux:
			toStack = auxPos;
			break;
		case des:
			toStack = desPos;
			break;
		default:
			break;
		}
		
		if(!fromStack.isEmpty()){
			int movingDisk = fromStack.pop();
			toStack.push(movingDisk);
			
			// 更新实际柱子上的圆盘数
			switch (fromPeg) {
			case initial:
				initialPos = fromStack;
				break;
			case aux:
				auxPos = fromStack;
				break;
			case des:
				desPos = fromStack;
				break;
			default:
				break;
			}
			switch (toPeg) {
			case initial:
				initialPos = toStack;
				break;
			case aux:
				auxPos = toStack;
				break;
			case des:
				desPos = toStack;
				break;
			default:
				break;
			}
			printTowers();
		}
		else{
			System.out.println("Stack empty!");
		}
	}
	
	/**
	 * 借助柱子auxPeg，将n个圆盘从fromPeg移动到toPeg
	 * @param n
	 * @param fromPeg
	 * @param auxPeg
	 * @param toPeg
	 */
	private static void towersOfHanoi(int n, Position fromPeg,Position auxPeg, Position toPeg){
		// 如果只有一个圆盘，则直接移动
		if(n==1){
			try {
				buff.write(("From peg:" + fromPeg + "\t To peg:" + toPeg + "\r\n").getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveDisk(fromPeg, toPeg);
			return;
		}
		// 将n-1个disk，借助toPeg，从fromPeg移动到auxPeg
		towersOfHanoi(n-1, fromPeg,toPeg,auxPeg);
		
		// 将剩余的1个disk从fromPeg移动到toPeg
		try {
			buff.write(("From peg:" + fromPeg + "\t To peg:" + toPeg + "\r\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moveDisk(fromPeg, toPeg);
		
		// 将辅助的auxPeg上的disk，借助fromPeg，移动到toPeg
		towersOfHanoi(n-1, auxPeg, fromPeg, toPeg);
	}
	
	/**
	 * 每移动一次，打印一次
	 */
	private static void printTowers(){
		Stack initialTemp = (Stack)initialPos.clone();
		Stack auxTemp = (Stack)auxPos.clone();
		Stack desTemp = (Stack)desPos.clone();
		int temp;
		// 打印initialPos
		try {
			buff.write("initialPos:".getBytes());
			while(!initialTemp.isEmpty()){
				buff.write((initialTemp.pop()+" ").getBytes());
			}
			buff.write("\r\n".getBytes());
			buff.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 打印initialPos
		try {
			buff.write("auxPos:".getBytes());
			while(!auxTemp.isEmpty()){
				buff.write((auxTemp.pop()+" ").getBytes());
			}
			buff.write("\r\n".getBytes());
			buff.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 打印desPos
		try {
			buff.write("desPos:".getBytes());
			while(!desTemp.isEmpty()){
				buff.write((desTemp.pop()+" ").getBytes());
			}
			buff.write("\r\n".getBytes());
			buff.write("\r\n".getBytes());
			buff.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * main函数
	 * @param args
	 */
	public static void main(String[] args){
		try {
			fout = new FileOutputStream("out.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buff = new BufferedOutputStream(fout);
		
		init(11);
		printTowers();
		towersOfHanoi(11,Position.initial,Position.aux,Position.des);
		
		// 关闭buff
		try {
			buff.flush();
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
