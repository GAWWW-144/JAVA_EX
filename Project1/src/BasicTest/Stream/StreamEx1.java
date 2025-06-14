package BasicTest.Stream;

import java.util.stream.Stream;

public class StreamEx1 {
	public static void main(String[] args) {
		
		String[] strArr = {"aa","bb","cc","dd"};
		Stream<String> strStream = Stream.of(strArr);
		
		
		
		strStream.forEach(System.out::println);
		
		
		
		//strStream.filter(null)
		
		
		strStream = Stream.of(strArr);
		long cnt = strStream.count();
		System.out.println(cnt);
		
		

	}
}
