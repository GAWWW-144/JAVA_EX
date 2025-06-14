package basic;

public class UpCastEx {

	public static void main(String[] args) {
		Person p;
		Student s = new Student("이승훈");
		p = s;
		System.out.println(p.name);
		
		Person s1 = new Person("홍길동");
		
	//	Student s1 = (Student)p1;  //컴파일땐 문제없지만 메모리에 실제로 person만큼의 메모리공간에 접근하면 런타임오류 발생
	

	}

}
