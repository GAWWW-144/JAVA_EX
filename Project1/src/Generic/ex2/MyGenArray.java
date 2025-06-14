package Generic.ex2;

public class MyGenArray<T> {
	
	
	
	
	private T[] arr;  //배열은 사이즈 가 결정되어야함
	
	
	
	public MyGenArray(int size) {
		super();
		arr = (T[]) new Object[size];  //편법 arr =  t[] new arr 안되는이유 
	}
	
	
	public T getArr (int index) {
		return arr[index];
		
	}
	public void setArr(int index, T value) {
		arr[index] = value;
	}
	
	public int length() {
		return arr.length;
		
		
	}

}
