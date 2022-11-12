package calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();
        String result = calc(input);
        System.out.println("Ответ: " + result);
    }

    private static boolean isRome(String input) {
        String romePattern = "^[IVX]+[*\\-+/][IVX]+";
        return input.toUpperCase().matches(romePattern);
    }

    private static boolean isArabic(String input) {
        String arabPattern = "^\\d0?[*\\-+/]\\d0?";
        return input.matches(arabPattern);
    }

    public static String calc(String input) {
        String result = "";
        if (isArabic(input)) {
            String[] values = parse(input);
            char sign = getChar(values[0].length(), input);
            result = String.valueOf(getResult(values, sign));
        } else if (isRome(input)) {
            String[] values = parse(input);
            char sign = getChar(values[0].length(), input);
            convertToArabic(values);
            int res = getResult(values, sign);
            if (res < 0) throwException();
            result = convertToRome(String.valueOf(res));
        } else {
            throwException();
        }
        return result;
    }

    private static String convertToRome(String value) {
        switch (value) {
            case "1":
                value = "I";
                break;
            case "2":
                value = "II";
                break;
            case "3":
                value = "III";
                break;
            case "4":
                value = "IV";
                break;
            case "5":
                value = "V";
                break;
            case "6":
                value = "VI";
                break;
            case "7":
                value = "VII";
                break;
            case "8":
                value = "VIII";
                break;
            case "9":
                value = "IX";
                break;
            case "10":
                value = "X";
                break;
            default:
                throwException();
        }
        return value;
    }

    private static void convertToArabic(String[] values) {
        for (int i = 0; i < values.length; i++) {
            String value = values[i].toUpperCase();
            switch (value) {
                case "I":
                    value = "1";
                    break;
                case "II":
                    value = "2";
                    break;
                case "III":
                    value = "3";
                    break;
                case "IV":
                    value = "4";
                    break;
                case "V":
                    value = "5";
                    break;
                case "VI":
                    value = "6";
                    break;
                case "VII":
                    value = "7";
                    break;
                case "VIII":
                    value = "8";
                    break;
                case "IX":
                    value = "9";
                    break;
                case "X":
                    value = "10";
                    break;
                default:
                    throwException();
            }
            values[i] = value;
        }
    }

    public static void throwException() {
        throw new RuntimeException("Неправильный фомат ввода");
    }

    private static int getResult(String[] values, char sign) {
        int a = Integer.parseInt(values[0]);
        int b = Integer.parseInt(values[1]);
        int result = 0;
        switch (sign){
            case '/':
                result = a / b;
                break;
            case '*':
                result = a * b;
                break;
            case '-':
                result = a - b;
                break;
            case '+':
                result = a + b;
                break;
        }
        return result;
    }

    private static char getChar(int length, String input) {
        return input.charAt(length);
    }

    private static String[] parse(String input) {
        return input.split("[/*\\-+]");
    }
}
