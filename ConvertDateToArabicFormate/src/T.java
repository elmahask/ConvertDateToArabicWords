
public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 2019;
		fun(num);
	}

	private static void fun(int num) {
		String str = Integer.toString(num);
		System.out.println(str.length());
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 0) {}
		}
	}

}
