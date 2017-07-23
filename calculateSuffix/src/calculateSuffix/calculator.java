/**
 * 
 */
package calculateSuffix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author Haiping
 *
 */
public class calculator {
	/**
	 * 判断某个输入是操作符
	 * @param input
	 * @return
	 */
	private static boolean isValidOperator(String input) {
		if("+".equals(input) || "-".equals(input) || "*".equals(input) || "/".equals(input)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 判断某个输入是否为操作数
	 * @param input
	 * @return
	 */
	private static boolean isValidOperand(String input) {
		String patternOperand = "[0-9]+";
		return Pattern.matches(patternOperand, input);
	}
	
	/**
	 * 计算后缀表达式
	 * @param args
	 * @return
	 */
	private static int calculateSuffix(String[] args) {
		Stack<String> stack = new Stack<String>();
		int arg1;
		int arg2;
		int binaryResult = -1;
		for (String arg : args) {
			if(isValidOperand(arg)) {
				stack.push(arg);
			}
			else if(isValidOperator(arg)){
				if(stack.isEmpty()) {
					return -1;
				}
				else {
					arg1 = Integer.parseInt(stack.pop());
				}
				if(stack.isEmpty()) {
					return -1;
				}
				else {
					arg2 = Integer.parseInt(stack.pop());
				}
				binaryResult = calculateBinary(arg1, arg2, arg);
				stack.push(Integer.toString(binaryResult));
			}
		}
		stack.clear();
		return binaryResult;
	}
	
	/**
	 * 计算两个数之间的加减乘除
	 * @param arg1
	 * @param arg2
	 * @param operator
	 * @return
	 */
	private static int calculateBinary(int arg1, int arg2, String operator) {
		switch (operator) {
		case "+":
			return arg1+arg2;
		case "-":
			return arg1-arg2;
		case "*":
			return arg1*arg2;
		case "/":
			return arg1/arg2;
		default:
			break;
		}
		return -2;
	}
	
	/**
	 * main函数
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the expression(example:6 5 2 3 + 8 * + 3 + *):");
		String inputExpression = br.readLine();
		String[] expElements = inputExpression.split(" ");
		for (int i = 0; i < expElements.length; i++) {
			expElements[i] = expElements[i].trim();
		}
		System.out.println("The result of the suffix expression is :\n");
		System.out.println(calculateSuffix(expElements)); 
	}
}
