
public class KString {
	private static final int num = 4;
	private static final int kk = 2;
	private static int A[] = new int[num];
	/**
	 * ���ݷ����ɳ���Ϊn������k���ƴ�������ÿλ��ȡֵΪ0..k-1
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
