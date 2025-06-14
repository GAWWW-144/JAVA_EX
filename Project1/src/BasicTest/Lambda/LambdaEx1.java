package BasicTest.Lambda;

import java.util.ArrayList;
import java.util.List;
public class LambdaEx1 {
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		
		list1.add("aaa");
		list1.add("bbb");
		list1.add("ccc");
		list1.add("ddd");
		
		System.out.println(list1);
		
		for (String str : list1) {
			System.out.println(str);
		}
		
		new ArrayList<Integer>(List.of(111,222,333,444))
		.forEach( System.out::println) ;
			
	
	}

}
