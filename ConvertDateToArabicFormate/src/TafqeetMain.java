public class TafqeetMain {

    public TafqeetMain() {
    }

    final private static String[] units = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    final private static String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };

    private static String convert(Integer number) {
        if (number < 20) {
            return units[number];
        }
        if (number < 100) {
            return tens[number / 10] + ((number % 10 > 0) ? " " + convert(number % 10) : "");
        }
        if (number < 1000) {
            return units[number / 100] + " Hundred" + ((number % 100 > 0) ? " and " + convert(number % 100) : "");
        }
        if (number < 1000000) {
            return convert(number / 1000) + " Thousand " + ((number % 1000 > 0) ? " " + convert(number % 1000) : "");
        }
        return convert(number / 1000000) + " Million "
                + ((number % 1000000 > 0) ? " " + convert(number % 1000000) : "");
    }

    public static String convertNumberToEnglishWords(String number) throws NumberFormatException {
        Integer i = Integer.parseInt(number);
        return convert(i);

    }

    public static String convertNumberToArabicWords(String number) throws NumberFormatException {

        // check if the input string is number or not
        Double.parseDouble(number);

        // check if its floating point number or not
        if (number.contains(".")) { // yes
            // the number
            String theNumber = number.substring(0, number.indexOf('.'));
            // the floating point number
            String theFloat = number.substring(number.indexOf('.') + 1);
            // check how many digits in the number 1:x 2:xx 3:xxx 4:xxxx 5:xxxxx
            // 6:xxxxxx
            switch (theNumber.length()) {
            case 1:
                return convertOneDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            case 2:
                return convertTwoDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            case 3:
                return convertThreeDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            case 4:
                return convertFourDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            case 5:
                return convertFiveDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            case 6:
                return convertSixDigits(theNumber) + " ›«’·… " + convertTwoDigits(theFloat);
            default:
                return "";
            }
        } else {
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
    }

    // -------------------------------------------

    private static String convertOneDigits(String oneDigit) {
        switch (Integer.parseInt(oneDigit)) {
        case 1:
            return "Ê«Õœ";
        case 2:
            return "≈À‰«‰";
        case 3:
            return "À·«ÀÂ";
        case 4:
            return "√—»⁄Â";
        case 5:
            return "Œ„”Â";
        case 6:
            return "” Â";
        case 7:
            return "”»⁄Â";
        case 8:
            return "À„«‰ÌÂ";
        case 9:
            return " ”⁄Â";
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
                if (getIntVal(twoDigits.charAt(1)) == 1) {
                    return "≈ÕœÏ ⁄‘—";
                }
                if (getIntVal(twoDigits.charAt(1)) == 2) {
                    return "≈À‰Ï ⁄‘—";
                } else {
                    return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " " + "⁄‘—";
                }
            }
            case 2: // 2x x:not 0
                returnAlpha = "⁄‘—Ê‰";
                break;
            case 3: // 3x x:not 0
                returnAlpha = "À·«ÀÊ‰";
                break;
            case 4: // 4x x:not 0
                returnAlpha = "√—Ì⁄Ê‰";
                break;
            case 5: // 5x x:not 0
                returnAlpha = "Œ„”Ê‰";
                break;
            case 6: // 6x x:not 0
                returnAlpha = "” Ê‰";
                break;
            case 7: // 7x x:not 0
                returnAlpha = "”»⁄Ê‰";
                break;
            case 8: // 8x x:not 0
                returnAlpha = "À„«‰Ê‰";
                break;
            case 9: // 9x x:not 0
                returnAlpha = " ”⁄Ê‰";
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
            return convertOneDigits(String.valueOf(twoDigits.charAt(1))) + " Ê " + returnAlpha;
        }
    }

    private static String convertThreeDigits(String threeDigits) {

        // check the first digit x00
        switch (getIntVal(threeDigits.charAt(0))) {
        //case 0:
        case 1: { // 100 - 199
            if (getIntVal(threeDigits.charAt(1)) == 0) { // 10x
                if (getIntVal(threeDigits.charAt(2)) == 0) { // 100
                    return "„«∆Â";
                } else { // 10x x: is not 0
                    return "„«∆Â" + " Ê " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                }
            } else {// 1xx x: is not 0
                return "„«∆Â" + " Ê " + convertTwoDigits(threeDigits.substring(1, 3));
            }
        }
        case 2: { // 200 - 299
            if (getIntVal(threeDigits.charAt(1)) == 0) { // 20x
                if (getIntVal(threeDigits.charAt(2)) == 0) { // 200
                    return "„«∆ Ì‰";
                } else { // 20x x:not 0
                    return "„«∆ Ì‰" + " Ê " + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                }
            } else { // 2xx x:not 0
                return "„«∆ Ì‰" + " Ê " + convertTwoDigits(threeDigits.substring(1, 3));
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
                    return convertOneDigits(String.valueOf(threeDigits.charAt(1) + "„«∆Â"));
                } else { // x0x x:not 0
                    return convertOneDigits(String.valueOf(threeDigits.charAt(0))) + "„«∆Â" + " Ê "
                            + convertOneDigits(String.valueOf(threeDigits.charAt(2)));
                }
            } else { // xxx x:not 0
                return convertOneDigits(String.valueOf(threeDigits.charAt(0))) + "„«∆Â" + " Ê "
                        + convertTwoDigits(threeDigits.substring(1, 3));
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
                        return "√·›";
                    } else { // 100x x:not 0
                        return "√·›" + " Ê " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                    }
                } else { // 10xx x:not 0
                    return "√·›" + " Ê " + convertTwoDigits(fourDigits.substring(2, 3));
                }
            } else { // 1xxx x:not 0
                return "√·›" + " Ê " + convertThreeDigits(fourDigits.substring(1, 4));
            }
        }
        case 2: { // 2000 - 2999
            if (getIntVal(fourDigits.charAt(1)) == 0) { // 20xx
                if (getIntVal(fourDigits.charAt(2)) == 0) { // 200x
                    if (getIntVal(fourDigits.charAt(3)) == 0) { // 2000
                        return "√·›Ì‰";
                    } else { // 200x x:not 0
                        return "√·›Ì‰" + " Ê " + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                    }
                } else { // 20xx x:not 0
                	convertTwoDigits(fourDigits.substring(2, 3));
                    return "√·›Ì‰" + " Ê " + convertTwoDigits(fourDigits.substring(2, 3));
                }
            } else { // 2xxx x:not 0
                return "√·›Ì‰" + " Ê " + convertThreeDigits(fourDigits.substring(1, 4));
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
                        return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " √·«›";
                    } else { // x00x x:not 0
                        return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " √·«›" + " Ê "
                                + convertOneDigits(String.valueOf(fourDigits.charAt(3)));
                    }
                } else { // x0xx x:not 0
                    return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " √·«›" + " Ê "
                            + convertTwoDigits(fourDigits.substring(2, 3));
                }
            } else { // xxxx x:not 0
                return convertOneDigits(String.valueOf(fourDigits.charAt(0))) + " √·«›" + " Ê "
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
            return convertTwoDigits(fiveDigits.substring(0, 2)) + " √·› ";
        } else { // xxxxx x:not 0
            return convertTwoDigits(fiveDigits.substring(0, 2)) + " √·›« " + " Ê "
                    + convertThreeDigits(fiveDigits.substring(2, 5));
        }
    }

    private static String convertSixDigits(String sixDigits) {

        if (convertThreeDigits(sixDigits.substring(2, 5)).length() == 0) { // xxx000
                                                                           // x:not
                                                                           // 0
            return convertThreeDigits(sixDigits.substring(0, 3)) + " √·› ";
        } else { // xxxxxx x:not 0
            return convertThreeDigits(sixDigits.substring(0, 3)) + " √·›« " + " Ê "
                    + convertThreeDigits(sixDigits.substring(3, 6));
        }
    }

    private static int getIntVal(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    // ----------------------------------------------------------

    public static void main(String[] args) {
      //  System.out.println(TafqeetMain.convertNumberToEnglishWords("123456"));

        System.out.println(TafqeetMain.convertNumberToArabicWords("2014"));
    }
}