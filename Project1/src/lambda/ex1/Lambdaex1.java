package lambda.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
public class Lambdaex1 {

	public static void main(String[] args) {
		int [] arr = {1, 2, 3, 4, 5};
		IntStream.of(arr).map(n -> n * n).forEach(x  -> System.out.println(x));
		IntStream.of(arr).sorted().forEach(System.out::println);
		
		new ArrayList<Integer>(List.of(1,5,2,4,2)).forEach(System.out::println);
		
		System.out.println("================================");
		
		
		
		
		
		
		List<Integer> nums = new ArrayList<Integer>(List.of(1,4,5,3,2));
		//nums.stream().forEach(n -> System.out.println(n));	
		//nums.forEach(n -> System.out.println(n));	
		nums.forEach(System.out::println);
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.forEach(s -> System.out.println(s)); //람다식 
		
		
		for (String s : list) {
			System.out.println(s);
			
		}

	}

}
