package ex2;

public class main {

	public static void main(String[] args) {
		
		
		Shape shape = new Shape();
		shape.draw();
		
		shape = new Rect();
		shape.draw();  //컴파일시에는 부모 -> 부모부분만 접근함 (업캐스팅)  런타임시 동적바인딩되서 자식 draw() 가 찍힘 
		//rect.draw();
		
		
		shape = new Circle();
		shape.draw();
		
		shape = new Line();
		shape.draw();
		


	}

}
