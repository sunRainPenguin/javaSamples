package decodeWays;

public class Solution {
	/**
	 * 按照‘A’-1，'B'-2，'C'-3...的对应关系，看一个数字字符串能够有多少解析方式
	 * @param s
	 * @return
	 */
	public static int[] numDecodings(String s) {
		int n = s.length();
		if(n==0)return null;
		int[] memo = new int[n+1];
		memo[n]=1;
		memo[n-1]=s.charAt(n-1)!='0'?1:0;
		
		for(int i=n-2;i>=0;i--) {
			if(s.charAt(i)=='0')continue;
			else memo[i]= (Integer.parseInt(s.substring(i, i+2))<=26)?memo[i+1]+memo[i+2]:memo[i+1];
		}
		return memo;
	}
	
	
	public static void main(String[] args) {
		int[] result = numDecodings("123");
		for (int i : result) {
			System.out.print(i + " ");
		}
		
	}
}
