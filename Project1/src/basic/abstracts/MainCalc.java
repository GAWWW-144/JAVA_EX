package basic.abstracts;

public class MainCalc {

	public static void main(String[] args) {
		Calc calc = new RealCalc();
		
		int result = calc.sub(10, 20); //구현안되있지만 오버라이딩되어서 부모가 부모부분을 호출했지만 런타임때 동적바인딩 수행됨
		System.out.println(result);
		

	}

}
