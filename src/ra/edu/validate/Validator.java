package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    // Biểu thức chính quy kiểm tra email, số điện thoại và Date
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";
    private static final Pattern PHONE_PATTERN = Pattern.compile("(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Validate số nguyên lớn hơn hoặc bằng 0
    public static int validateInt(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Vui lòng nhập số nguyên lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số nguyên.");
            }
        }
    }

    // Validate số thực float lớn hơn 0
    public static float validateFloat(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                float value = Float.parseFloat(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Vui lòng nhập số thực (float) lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số thực (float).");
            }
        }
    }

    // Validate số thực double lớn hơn hoặc bằng 0
    public static double validateDouble(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Vui lòng nhập số thực (double) lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số thực (double).");
            }
        }
    }

    // Validate kiểu boolean (true/false)
    public static boolean validateBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập true hoặc false.");
        }
    }

    // Validate chuỗi không được rỗng và nằm trong khoảng độ dài
    public static String validateString(Scanner scanner, String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.length() >= min && input.length() <= max) {
                return input;
            }
            System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập chuỗi có độ dài từ " + min + " đến " + max + " ký tự.");
        }
    }

    // Validate định dạng email
    public static String validateEmail(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (EMAIL_PATTERN.matcher(input).matches()) {
                return input;
            }
            System.out.println("Email không hợp lệ. Vui lòng nhập đúng định dạng email.");
        }
    }

    // Validate số điện thoại Việt Nam
    public static String validatePhone(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (PHONE_PATTERN.matcher(input).matches()) {
                return input;
            }
            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại di động Việt Nam.");
        }
    }

    // Validate ngày theo định dạng dd/MM/yyyy
    public static LocalDate validateDate(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }
    }
}
