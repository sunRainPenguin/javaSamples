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
	 * ��ʼ��Բ��λ��
	 */
	private static void init(int n){
		for(int i =n;i>0;i--){
			initialPos.push(i);
		}
	}
	
	/**
	 * ��һ��Բ�̴�fromPeg�ƶ���toPeg
	 * @param fromPeg
	 * @param toPeg
	 */
	private static void moveDisk(Position fromPeg,Position toPeg){
		Stack<Integer> fromStack = new Stack<Integer>();
		Stack<Integer> toStack = new Stack<Integer>();
		// ���fromStack��toStack
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
			
			// ����ʵ�������ϵ�Բ����
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
	 * ��������auxPeg����n��Բ�̴�fromPeg�ƶ���toPeg
	 * @param n
	 * @param fromPeg
	 * @param auxPeg
	 * @param toPeg
	 */
	private static void towersOfHanoi(int n, Position fromPeg,Position auxPeg, Position toPeg){
		// ���ֻ��һ��Բ�̣���ֱ���ƶ�
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
		// ��n-1��disk������toPeg����fromPeg�ƶ���auxPeg
		towersOfHanoi(n-1, fromPeg,toPeg,auxPeg);
		
		// ��ʣ���1��disk��fromPeg�ƶ���toPeg
		try {
			buff.write(("From peg:" + fromPeg + "\t To peg:" + toPeg + "\r\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moveDisk(fromPeg, toPeg);
		
		// ��������auxPeg�ϵ�disk������fromPeg���ƶ���toPeg
		towersOfHanoi(n-1, auxPeg, fromPeg, toPeg);
	}
	
	/**
	 * ÿ�ƶ�һ�Σ���ӡһ��
	 */
	private static void printTowers(){
		Stack initialTemp = (Stack)initialPos.clone();
		Stack auxTemp = (Stack)auxPos.clone();
		Stack desTemp = (Stack)desPos.clone();
		int temp;
		// ��ӡinitialPos
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
		
		// ��ӡinitialPos
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
		// ��ӡdesPos
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
	 * main����
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
		
		// �ر�buff
		try {
			buff.flush();
			buff.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
