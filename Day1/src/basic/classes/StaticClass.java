package basic.classes;

public class StaticClass {

	
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		
		StaticClass sc = new StaticClass(); // static 남발 ㄴㄴ혓
		
		int result = sc.add(a,b);
		System.out.print(result);
		System.out.println(result);			
	}
	
	
	private static int add(int a, int b) {
		return a + b;
		
		
	}

}
