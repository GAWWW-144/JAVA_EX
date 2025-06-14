package basic.override;

public class AdSort extends Sort{
	public void calc() {
		
	System.out.println("계산중...");
	}
	
	@Override
	public void sort() {  //메소드 덮어씌우기
		System.out.println("1초!!!");
	}
}
