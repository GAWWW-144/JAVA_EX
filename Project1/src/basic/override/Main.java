package basic.override;

public class Main {

	public static void main(String[] args) {
		Sort sort = new Sort();
		sort.sort();
		
		AdSort adSort = new AdSort();
		adSort.calc();
		adSort.sort();
	}

}
