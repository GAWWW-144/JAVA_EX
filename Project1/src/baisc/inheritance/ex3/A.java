package baisc.inheritance.ex3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class A extends Object{
	private int a;
	
//	public A() {}
	public A(int a) {
		super();
		this.a = a;
		System.out.println("생성자 A");
	}
	
	

}
