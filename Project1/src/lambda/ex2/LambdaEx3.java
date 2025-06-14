package lambda.ex2;

public class LambdaEx3 {

	public static void main(String[] args) {
		MyFunc mf = new MyFunc() {
			
			@Override
			public int max(int a, int b) {
				
				return a > b ? a : b;
			}
		};
		
		int res = mf.max(10, 20);
		System.out.println(res);
		
		MyFunc mf2 = (a, b)  -> a > b ? a : b;   //람다 익스프레션 express
		int res2 = mf2.max(100, 200);
		System.out.println(res2);
			

	}

}
