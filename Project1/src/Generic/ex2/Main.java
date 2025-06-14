package Generic.ex2;


public class Main {

	public static void main(String[] args) {
		MyGenArray<Integer> array2 = new MyGenArray<Integer>(4);
		array2.setArr(0,10);
		array2.setArr(0,20);
		array2.setArr(0,30);		
		array2.setArr(0,40);
		int sum = 0;
		for (int i = 0; i < array2.length(); i++) {
			sum = sum + array2.getArr(i);
			
			
		}
		System.out.println("점수" +sum);
		
		
		
		
		
		
		
		MyGenArray<String> array = new MyGenArray<String>(3);
		array.setArr(0, "apple");
		array.setArr(1, "Banana");
		array.setArr(2, "cherry");
		System.out.println(array);
		for (int i = 0; i < array.length(); i++) {
			
		}
		

	}

}
