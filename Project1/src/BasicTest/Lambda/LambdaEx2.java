package BasicTest.Lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaEx2 {
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("ccc","bbb","ddd","aaa");
		System.out.println(list1);
		
		Collections.sort(list1, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o2.compareTo(o1);
			}
		});
		System.out.println(list1);
		
		
		List<Integer> list2 =  Arrays.asList(333,222,444,111);
		System.out.println(list2);
		
		Collections.sort(list2,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.compareTo(o1);
			}
			
		});
		System.out.println(list2);
		
		List<Integer> list3 =  Arrays.asList(333,111,222,444);
		Collections.sort(list3,(o1,o2) -> o2.compareTo(o1));
		System.out.println(list3);
	}

}
