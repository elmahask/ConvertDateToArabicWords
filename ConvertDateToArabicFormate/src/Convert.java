
public class Convert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int num = 2135;
		fun(num);
		
		int one = num % 10;
		int ten = num % 100;
		int hundred = num % 1000;
		int thousand = num - (hundred);
		System.out.println(one);
		System.out.println(ten);
		System.out.println(hundred-ten);
		System.out.println(thousand);
		
		
		
	}
	
	private static void fun(int number) {
		do {
			int n = number % 100;
			if (n != 0) {
				//System.out.println(n);
				convertLessThanOneThousand(n);
			}
			//place++;
			number /= 100;
		} while (number > 0);
		//return 0;
	}
	
	private static void convertLessThanOneThousand(int number) {
		String current;

		if (number % 100 < 20) {
			System.out.println("specialNamesMonthDay "+number%100);
			//current = specialNamesMonthDay[number % 100];
			number /= 100;
		} else {
			//current = numNames[number % 10];
			System.out.println("numNames "+number%100);
			number /= 10;
			//current = tensNames[number % 10] + current;
			System.out.println("tensNames "+number%10);
			number /= 10;
		}
		//if (number == 0) {
		//	return current;
		//}
		//return numNames[number] + " hundred" + current;
	}
}
