package BasicTest.Lambda;

public class MyFuncTest {
	public static void main(String[] args) {
		MyFunc func = (a,b) -> a > b ? a : b;
		
	
		
		int val = func.max(10, 20);
		System.out.println(val);
	}

}
