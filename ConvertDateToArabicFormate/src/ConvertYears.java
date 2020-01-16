import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ConvertYears {

	
	public static String ConvertDateToArabicWords(String dateInString) {
		//dateInString = "31/07/2014";
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		//ParsePosition parsePosition = new ParsePosition(0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateFormatter.parse(dateInString, new ParsePosition(0)));
		DateFormat format2 = new SimpleDateFormat("MMMM",new Locale("ar","ar-EG"));

		int day = cal.get(Calendar.DATE);
		String strDateToWords = convertNumberToArabicWords(Integer.toString(day));
		strDateToWords += " �� " + format2.format(cal.getTime());

		
		int year = cal.get(Calendar.YEAR);
		strDateToWords += " ��� " + convertNumberToArabicWords(Integer.toString(year));
		return strDateToWords;
	}
	
	private static String convertNumberToArabicWords(String number) throws NumberFormatException {

		switch (number.length()) {
		case 1:
			return convertOneDigits(number);
		case 2:
			return convertTwoDigits(number);
		case 3:
			return convertThreeDigits(number);
		case 4:
			return convertFourDigits(number);
		case 5:
			return convertFiveDigits(number);
		case 6:
			return convertSixDigits(number);
		default:
			return "";
		}

	}

	// -------------------------------------------

	private static String convertOneDigits(String oneDigit) {
		switch (Integer.parseInt(oneDigit)) {
		case 1:
			return "����";
		case 2:
			return "�����";
		case 3:
			return "�����";
		case 4:
			return "�����";
		case 5:
			return "����";
		case 6:
			return "���";
		case 7:
			return "����";
		case 8:
			return "������";
		case 9:
			return "����";
		default:
			return "";
		}
	}

	private static String convertTwoDigits(String twoDigits) {
		String returnAlpha = "00";
		// check if the first digit is 0 like 0x
		if (twoDigits.charAt(0) == '0' && twoDigits.charAt(1) != '0') { // yes
			// convert two digits to one
			return convertOneDigits(String.valueOf(twoDigits.charAt(1)));
		} else { // no
			// check the first digit 1x 2x 3x 4x 5x 6x 7x 8x 9x
			switch (getIntVal(twoDigits.charAt(0))) {
			case 1: { // 1x
				if (getIntVal(twoDigits.charAt(1)) == 0) {
					return "����";
				}
				if (getIntVal(twoDigits.charAt(1)) == 1) {
					return "��� ���";
				}
				if (getIntVal(twoDigits.charAt(1)) == 2) {
					return "���� ���";
				} else { 
					return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " " + "���";
				}
			}
			case 2: // 2x x:not 0
				returnAlpha = "�����";
				break;
			case 3: // 3x x:not 0
				returnAlpha = "������";
				break;
			case 4: // 4x x:not 0
				returnAlpha = "������";
				break;
			case 5: // 5x x:not 0
				returnAlpha = "�����";
				break;
			case 6: // 6x x:not 0
				returnAlpha = "����";
				break;
			case 7: // 7x x:not 0
				returnAlpha = "�����";
				break;
			case 8: // 8x x:not 0
				returnAlpha = "������";
				break;
			case 9: // 9x x:not 0
				returnAlpha = "�����";
				break;
			default:
				returnAlpha = "";
				break;
			}
		}

		// 20 - 99
		// x0 x:not 0,1
		if (convertOneDigits(String.valueOf(twoDigits.charAt(1))).length() == 0) {
			return returnAlpha;
		} else { // xx x:not 0
			return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " � " + returnAlpha;
		}
	}

	private static String convertThreeDigits(String threeDigits) {

		// check the first digit x00
		switch (getIntVal(threeDigits.charAt(0))) {
		// case 0:
		case 1: { // 100 - 199
			if (getIntVal(threeDigits.charAt(1)) == 0) { // 10x
				if (getIntVal(threeDigits.charAt(2)) == 0) { // 100
					return "����";
				} else { // 10x x: is not 0
					return "����" + " � " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
				}
			} else {// 1xx x: is not 0
				return "����" + " � " + convertTwoDigits(threeDigits.substring(1, 3));
			}
		}
		case 2: { // 200 - 299
			if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
				if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
					return "�����";
				} else { // 20x x:not 0
					return "�����" + " � " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
				}
			} else { // 2xx x:not 0
				return "�����" + " � " + convertTwoDigits(threeDigits.substring(1, 3));
			}
		}
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9: { // 300 - 999
			if (getIntVal(threeDigits.charAt(1)) == 0) { // x0x x:not 0
				if (getIntVal(threeDigits.charAt(2)) == 0) { // x00 x:not 0
					return convertOneDigits(String.valueOf(threeDigits.charAt(1) + "����"));
				} else { // x0x x:not 0
					return convertOneDigits(String.valueOf(threeDigits.charAt(0))) + "����" + " � "
							+ convertOneDigits(String.valueOf(threeDigits.charAt(2)));
				}
			} else { // xxx x:not 0
				String s = convertOneDigits(String.valueOf(threeDigits.charAt(0)));
				if(threeDigits.charAt(0)=='8') {
					return s.substring(0, s.length() - 2) + "����" + " � "+ convertTwoDigits(threeDigits.substring(1, 3));
				}else
				return s.substring(0, s.length() - 1) + "����" + " � "+ convertTwoDigits(threeDigits.substring(1, 3));
			}
		}

		case 0: { // 000 - 099
			if (threeDigits.charAt(1) == '0') { // 00x
				if (threeDigits.charAt(2) == '0') { // 000
					return "";
				} else { // 00x x:not 0
					return convertOneDigits(String.valueOf(threeDigits.charAt(2)));
				}
			} else { // 0xx x:not 0
				return convertTwoDigits(threeDigits.substring(1, 3));
			}
		}
		default:
			return "";
		}
	}

	private static String convertFourDigits(String fourDigits) {
		// xxxx
		switch (getIntVal(fourDigits.charAt(0))) {

		case 1: { // 1000 - 1999
			if (getIntVal(fourDigits.charAt(1)) == 0) { // 10xx x:not 0
				if (getIntVal(fourDigits.charAt(2)) == 0) { // 100x x:not 0
					if (getIntVal(fourDigits.charAt(3)) == 0) { // 1000
						return "���";
					} else { // 100x x:not 0
						return "���" + " � " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
					}
				} else { // 10xx x:not 0
					return "���" + " � " + convertTwoDigits(fourDigits.substring(2, 3));
				}
			} else { // 1xxx x:not 0
				return "���" + " � " + convertThreeDigits(fourDigits.substring(1, 4));
			}
		}
		case 2: { // 2000 - 2999
			if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
				if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
					if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
						return "�����";
					} else { // 200x x:not 0
						return "�����" + " � " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
					}
				} else { // 20xx x:not 0
					String s  = convertTwoDigits(fourDigits.substring(2, 4));
					return "�����" + " � "+s;
				}
			} else { // 2xxx x:not 0
				return "�����" + " � " + convertThreeDigits(fourDigits.substring(1, 4));
			}
		}
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9: { // 3000 - 9999
			if (getIntVal(fourDigits.charAt(1)) == 0) { // x0xx x:not 0
				if (getIntVal(fourDigits.charAt(2)) == 0) { // x00x x:not 0
					if (getIntVal(fourDigits.charAt(3)) == 0) { // x000 x:not 0
						return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ����";
					} else { // x00x x:not 0
						return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ����" + " � "
								+ convertOneDigits(String.valueOf(fourDigits.charAt(3)));
					}
				} else { // x0xx x:not 0
					return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ����" + " � "
							+ convertTwoDigits(fourDigits.substring(2, 3));
				}
			} else { // xxxx x:not 0
				return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " ����" + " � "
						+ convertThreeDigits(fourDigits.substring(1, 4));
			}
		}

		default:
			return "";
		}
	}

	private static String convertFiveDigits(String fiveDigits) {
		if (convertThreeDigits(fiveDigits.substring(2, 5)).length() == 0) { // xx000
																			// x:not
																			// 0
			return convertTwoDigits(fiveDigits.substring(0, 2)) + " ��� ";
		} else { // xxxxx x:not 0
			return convertTwoDigits(fiveDigits.substring(0, 2)) + " ���� " + " � "
					+ convertThreeDigits(fiveDigits.substring(2, 5));
		}
	}

	private static String convertSixDigits(String sixDigits) {

		if (convertThreeDigits(sixDigits.substring(2, 5)).length() == 0) { // xxx000
																			// x:not
																			// 0
			return convertThreeDigits(sixDigits.substring(0, 3)) + " ��� ";
		} else { // xxxxxx x:not 0
			return convertThreeDigits(sixDigits.substring(0, 3)) + " ���� " + " � "
					+ convertThreeDigits(sixDigits.substring(3, 6));
		}
	}

	private static int getIntVal(char c) {
		return Integer.parseInt(String.valueOf(c));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ConvertYears.ConvertDateToArabicWords("30/07/1993"));
	}
	
	

}
