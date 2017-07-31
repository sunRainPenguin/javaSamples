package inffix2Suffix;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InffixToSuffix {
//	String patternOperator = "(\\*|\\-|\\+|/)";
	private static HashMap<Character, Integer> priMap = new HashMap<Character,Integer>(){
		{
		put('(',0);
		put('+',1);
		put('-',1);
		put('*',2);
		put('/',2);
		}
	};
	
	/**
	 * 中缀转后缀
	 * @param input
	 * @return
	 */
	private static String inffixToSuffix(String input) {
		Stack<Character> stack = new Stack<Character>();
		StringBuffer strBuff = new StringBuffer();
		char[] chArr = input.toCharArray();
		for (int i = 0; i < chArr.length; i++) {
			char ch = chArr[i];
			if(Character.isDigit(ch) || '.'==ch) {
				strBuff.append(ch);
			}
			else if('('==ch) {
				stack.push(ch);
			}
			else if(')'==ch) {
				// 弹出栈元素，直至遇到‘(’
				while(stack.size()>0 && stack.peek()!='(') {
					strBuff.append(stack.pop());
				}
				if(!stack.isEmpty() && stack.peek()=='(') {
					stack.pop();
				}
			}
			else if('+'==ch || '-'==ch || '*'==ch || '/'==ch) {
				if(stack.isEmpty()) {
					stack.push(ch);
				}
				else if(priMap.get(ch)>priMap.get(stack.peek())) {
					stack.push(ch);
				}
				else {
					while(!stack.isEmpty() && priMap.get(ch)<=priMap.get(stack.peek())) {
						 strBuff.append(stack.pop());
					}
					stack.push(ch);
				}
			}
		}
		while(!stack.isEmpty()) {
			strBuff.append(stack.pop());
		}
		return strBuff.toString();
	}
	
	/**
	 * main函数
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "1+2*3+(4*5+6)*7";
		System.out.println(inffixToSuffix(input));
	}
	
}
