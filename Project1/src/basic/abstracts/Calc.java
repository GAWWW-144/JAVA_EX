package basic.abstracts;

public abstract class Calc {

	private int a;
	private int b;
	
	public int add(int a, int b) {
		return a + b;
	}
	public abstract int sub(int a, int b); //구현부없음 추상메소드
	
	
	

}
