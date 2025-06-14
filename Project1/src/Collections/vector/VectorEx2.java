package Collections.vector;

import java.util.Vector;

public class VectorEx2 {

	public static void main(String[] args) {
		Vector<String> sVec = new Vector<String>();
		sVec.add("AAA");
		sVec.add("BBB");
		sVec.add("111");
		sVec.add("CCC");
		sVec.add(2, "DDD");
		System.out.println(sVec.capacity());
		System.out.println(sVec.size());
		System.out.println(sVec.contains("111"));
		System.out.println(sVec.get(0));
		
		
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		for (String str : sVec) {
			System.out.println(str);
		}
	}

}
