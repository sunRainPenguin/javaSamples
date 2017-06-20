
public class KString {
	private static final int num = 4;
	private static final int kk = 2;
	private static int A[] = new int[num];
	/**
	 * 回溯法生成长度为n的所有k进制串，串中每位的取值为0..k-1
	 * @param n
	 * @param k
	 */
	private static void kstring(int n,int k){
		if(n<1){
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i] + "\t");
			}	
			System.out.print("\r\n");
		}
		else{
			for(int j=0;j<k;j++){
				A[n-1] = j;
				kstring(n-1,k);
			}
		}
	}
	
	public static void main(String[] args){
		kstring(num, kk);
	}
	
}
