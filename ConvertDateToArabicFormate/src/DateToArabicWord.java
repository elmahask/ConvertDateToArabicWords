import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class DateToArabicWord {

	private static final String[] specialNamesMonthDay = {
			"", "ÇáÇæá", "ÇáËÇäí", "ÇáËÇáË", "ÇáÑÇÈÚ", "ÇáÎÇãÓ",
			"ÇáÓÇÏÓ", "ÇáÓÇÈÚ", "ÇáËÇãä", "ÇáÊÇÓÚ", "ÇáÚÇÔÑ", "ÇáÍÇÏí ÚÔÑ", "ÇáËÇäí ÚÔÑ", "ÇáËÇáË ÚÔÑ", "ÇáÑÇÈÚ ÚÔÑ",
			"ÇáÎÇãÓ ÚÔÑ", "ÇáÓÇÏÓ ÚÔÑ", "ÇáÓÇÈÚ ÚÔÑ", "ÇáËÇãä ÚÔÑ", "ÇáÊÇÓÚ ÚÔÑ", "ÇáÚÔÑæä", "ÇáÍÇÏí æÇáÚÔÑæä",
			"ÇáËÇäí æÇáÚÔÑæä", "ÇáËÇáË æÇáÚÔÑæä", "ÇáÑÇÈÚ æÇáÚÔÑæä", "ÇáÎÇãÓ æÇáÚÔÑæä", "ÇáÓÇÏÓ æÇáÚÔÑæä",
			"ÇáÓÇÈÚ æÇáÚÔÑæä", "ÇáËÇãä æÇáÚÔÑæä", "ÇáÊÇÓÚ æÇáÚÔÑæä", "ÇáËáÇËæä", "ÇáÍÇÏí æÇáËáÇËæä" };

	private static final String[] specialNamesYear = { "", "Ãáİ", "ÃáİÇä", "ÂáÇİ"};
	private static final String[] numNames = { "", "æÇÍÏ", "ÇËäÇä", "ËáÇËÉ", "ÃÑÈÚÉ", "ÎãÓÉ", "ÓÊÉ", "ÓÈÚÉ",
			"ËãÇäíÉ", "ÊÓÚÉ", "ÚÔÑÉ", "ÃÍÏ ÚÔÑ", "ÇËäÇ ÚÔÑ", "ËáÇËÉ ÚÔÑ", "ÃÑÈÚÉ ÚÔÑ", "ÎãÓÉ ÚÔÑ", "ÓÊÉ ÚÔÑ",
			"ÓÈÚÉ ÚÔÑ", "ËãÇäíÉ ÚÔÑ", "ÊÓÚÉ ÚÔÑ" };
	private static final String[] tensNames = { "","ÚÔÑÉ", "ÚÔÑæä","ËáÇËæä","ÃÑÈÚæä","ÎãÓæä","ÓÊæä","ÓÈÚæä","ËãÇäæä","ÊÓÚæä"};
	private static final String[] hundredsNames = { "","ãÇÆÉ", "ãÆÊÇä","ËáÇËãÇÆÉ","ÃÑÈÚãÇÆÉ","ÎãÓãÇÆÉ","ÓÊãÇÆÉ","ÓÈÚãÇÆÉ","ËãÇäãÇÆÉ","ÊÓÚãÇÆÉ"};
	
	public static boolean validateDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			sdf.setLenient(false);
			sdf.parse(date);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static String convert(int number) {

		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		String current = "";
		int place = 0;

		if (number >= 1 && number < 2000) {
			do {
				int n = number % 100;
				if (n != 0) {
					String s = convertLessThanOneThousand(n);
					current = s + current;
				}
				place++;
				number /= 100;
			} while (number > 0);
		} else {
			do {
				int n = number % 1000;
				if (n != 0) {
					String s = convertLessThanOneThousand(n);
					current = s + specialNamesYear[place] + current;
				}
				place++;
				number /= 1000;
			} while (number > 0);
		}

		return (prefix + current).trim();
	}

	private static String convertLessThanOneThousand(int number) {
		String current;

		if (number % 100 < 20) {
			current = numNames[number % 100];
			number /= 100;
			System.out.println("1 "+number);
		} else {
			current = numNames[number % 10];
			number /= 10;
			System.out.println("2 "+number);
			current = tensNames[number % 10] + current;
			number /= 10;
			System.out.println("3 "+number);
		}
		if (number == 0) {
			return current;
		}
		return numNames[number] + " hundred" + current;
	}

	public static String getMonthDay(int day) {
		return specialNamesMonthDay[day];
	}

	public static void main(String[] args) throws ParseException {
		String date = "20/10/2010";
		if (validateDate(date)) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			ParsePosition parsePosition = new ParsePosition(0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormatter.parse(date, new ParsePosition(0)));
			DateFormat format2 = new SimpleDateFormat("MMMM",new Locale("ar","ar-EG"));

			int day = cal.get(Calendar.DATE);
			String strDateToWords = getMonthDay(day);
			strDateToWords += " ãä " + format2.format(cal.getTime());

			int year = cal.get(Calendar.YEAR);
			strDateToWords += " " + convert(year);
			System.out.println(strDateToWords/*.toLowerCase()*/);
		} else {
			System.out.println("Wrong input! Please enter date in dd/mm/yyyy format");
		}
	}

	public static String convertDate(String str) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date d = formatter.parse("14/01/2020");
		System.out.println(d.toLocaleString());
		Date date = new Date();
		System.out.println(d.getMonth());
		System.out.println(d.getYear());
		System.out.println(d.getDay());
		// SimpleDateFormat finalDateFormat = new
		// SimpleDateFormat("MM/dd/yyyy",Locale.US);
		String finalDate = formatter.format(date);
		// formatter.getCalendar().
		// System.out.println(finalDate);
		return "";
	}

}
