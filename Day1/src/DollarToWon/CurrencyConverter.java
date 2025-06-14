package DollarToWon;

 public class CurrencyConverter {
	private static double rate; 
	public static double toDoller(double won) {
		return won/rate;
	}
	public static double toKWR(double doller) {
		return doller * rate;
			
	}
	public static void setRate(double r) {
		rate = r;
		
	}
}
 class StaticMember {
	public static void main(String[] args) {
		CurrencyConverter.setRate(1446);
		System.out.println("백만원은 " + CurrencyConverter.toDoller(10000000) + " 달러입니다.");
		System.out.println("백달러는 " + CurrencyConverter.toKWR(100) + " 원입니다.");
	}
}
