import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class test {
	
	private static final String[] numNames = {
			"", "æÇÍÏ", "ÇËäÇä", "ËáÇËÉ", "ÃÑÈÚÉ", "ÎãÓÉ", "ÓÊÉ", "ÓÈÚÉ", "ËãÇäíÉ", "ÊÓÚÉ"};
			///*, "ÚÔÑÉ", "ÃÍÏ ÚÔÑ", "ÇËäÇ ÚÔÑ", "ËáÇËÉ ÚÔÑ", "ÃÑÈÚÉ ÚÔÑ", "ÎãÓÉ ÚÔÑ", "ÓÊÉ ÚÔÑ","ÓÈÚÉ ÚÔÑ", "ËãÇäíÉ ÚÔÑ", "ÊÓÚÉ ÚÔÑ" };*/

	private static final String[] specialNamesMonthDay = {
			"", "æÇÍÏ", "ÇËäíä", "ËáÇËÉ", "ÃÑÈÚÉ", "ÎãÓÉ",
			"ÓÊÉ", "ÓÈÚÉ", "ËãÇäíÉ", "ÊÓÚÉ", "ÚÔÑÉ", 
			"ÃÍÏ ÚÔÑ", "ÇËäÇ ÚÔÑ", "ËáÇËÉ ÚÔÑ", "ÃÑÈÚÉ ÚÔÑ",
			"ÎãÓÉ ÚÔÑ", "ÓÊÉ ÚÔÑ", "ÓÈÚÉ ÚÔÑ", "ËãÇäíÉ ÚÔÑ", 
			"ÊÓÚÉ ÚÔÑ", "ÚÔÑæä", "æÇÍÏ æÚÔÑæä","ÇËäíä æÚÔÑæä",
			"ËáÇËÉ æÚÔÑæä", "ÃÑÈÚÉ æÚÔÑæä", "ÎãÓÉ æÚÔÑæä", "ÓÊÉ æÚÔÑæä", 
			"ÓÈÚÉ æÚÔÑæä", "ËãÇäíÉ æÚÔÑæä", "ÊÓÚÉ æÚÔÑæä", "ËáÇËæä", "æÇÍÏ æËáÇËæä" 
			};
	
	private static final String[] tensNames = { "","ÚÔÑÉ", "ÚÔÑæä","ËáÇËæä","ÃÑÈÚæä","ÎãÓæä","ÓÊæä","ÓÈÚæä","ËãÇäæä","ÊÓÚæä"};
	
	private static final String[] hundredsNames = { "","ãÇÆÉ", "ãÆÊÇä","ËáÇËãÇÆÉ","ÃÑÈÚãÇÆÉ","ÎãÓãÇÆÉ","ÓÊãÇÆÉ","ÓÈÚãÇÆÉ","ËãÇäãÇÆÉ","ÊÓÚãÇÆÉ"};
	
	private static final String[] specialNamesYear = { "", "ÃáÝ", "ÃáÝÇä", "ÂáÇÝ"};

	public static String getMonthDay(int day) {
		return specialNamesMonthDay[day];
	}
	
	
	public static String convertYear(int num) {
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	public static String convert(int number) {

		String prefix = " ";

		String current = " ";
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
			current = specialNamesMonthDay[number % 100];
			number /= 100;
		} else {
			current = numNames[number % 10];
			number /= 10;
			current = tensNames[number % 10] + current;
			number /= 10;
		}
		if (number == 0) {
			return current;
		}
		return numNames[number] + " hundred" + current;
	}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
	        String dateInString = "31/07/2014";
	        
	        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			ParsePosition parsePosition = new ParsePosition(0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormatter.parse(dateInString, new ParsePosition(0)));
			DateFormat format2 = new SimpleDateFormat("MMMM",new Locale("ar","ar-EG"));

			int day = cal.get(Calendar.DATE);
			String strDateToWords = getMonthDay(day);
			strDateToWords += " ãä " + format2.format(cal.getTime());

			int year = cal.get(Calendar.YEAR);
			strDateToWords += " ÚÇã " + convert(year);
			convert(year);
			System.out.println(strDateToWords.toLowerCase());
	        
	        
			 
	        
	        
	        /*
	        
	        
	        try {

		            Date date = formatter.parse(dateInString);
		            System.out.println(date);
		            System.out.println(formatter.format(date));
		            
		            Calendar cal = Calendar.getInstance();
		            cal.setTime(date); // don't forget this if date is arbitrary e.g. 01-01-2014

		            int day = cal.get(Calendar.DAY_OF_MONTH);
		            int month = cal.get(Calendar.MONTH)+1; // 5
		            int year = cal.get(Calendar.YEAR); // 2013
		            
		            String s = day+"/"+month+"/"+year;
		            
		            System.out.println("concat -> "+formatter.format(formatter.parse(s)));
		            
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
			
	        
	        
	        
	        
		Date datee = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
        String strDate = dateFormat.format(datee);  
        System.out.println("Converted String: " + strDate);  
		
		
		
		
		
		
		
		SimpleDateFormat fen = new SimpleDateFormat("dd/MMMM/yyyy", new Locale("en"));
	    Locale eg = new Locale("ar","ar-EG");
		SimpleDateFormat ffr = new SimpleDateFormat("MMMM", eg);
	    Date date = new Date();
	    
	    System.out.println(fen.format(date));
	    System.out.println(ffr.format(date));
	    
	    	// intialize simpledateformat
	 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
	 		
	 		// instantiate calendar object
	 		Calendar call = Calendar.getInstance();	
	 		
	 		// Print the current time
	 		System.out.println("Time 1:" + call.getTime());
	 		
	 		// format the calendar date
	 		String formattedDate = sdf.format(call.getTime());
	 		System.out.println("Formatted date:"+formattedDate);		
	 		System.out.println("Formatted date:"+formattedDate);
//	 		System.out.println("Formatted date:"+cal.);

	 		
	 		String actualDate = "20-03-2016";
	 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy", eg);
	 		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("MMMM", eg);
	 		LocalDate ld = LocalDate.parse(actualDate, dtf);
	 		String month_name = dtf2.format(ld);
	 		System.out.println(month_name); // Mar 2016
	 	*/	
	}

}
