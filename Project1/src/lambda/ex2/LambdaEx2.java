package lambda.ex2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaEx2 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("dd","cc","ff","aa","cc");
		Collections.sort(list, ( o1,  o2) -> o2.compareTo(o1));
			
	
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println(list);
		
		
		list = Arrays.asList("dd","cc","ff","aa","cc");
		Collections.sort(list, Collections.reverseOrder());
		System.out.println(list);
	}

}
