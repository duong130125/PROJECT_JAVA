package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    // Biểu thức chính quy kiểm tra email và số điện thoại
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(0[3|5|7|8|9])[0-9]{8}$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Validate số nguyên
    public static int validateInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số nguyên.");
            }
        }
    }

    // Validate số thực float
    public static float validateFloat(Scanner scanner) {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số thực (float).");
            }
        }
    }

    // Validate số thực double
    public static double validateDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập số thực (double).");
            }
        }
    }

    // Validate kiểu boolean (true/false)
    public static boolean validateBoolean(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập true hoặc false.");
        }
    }

    // Validate chuỗi không được rỗng và nằm trong khoảng độ dài
    public static String validateString(Scanner scanner, int min, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && input.length() >= min && input.length() <= max) {
                return input;
            }
            System.out.println("Dữ liệu không hợp lệ. Vui lòng nhập chuỗi có độ dài từ " + min + " đến " + max + " ký tự.");
        }
    }

    // Validate định dạng email
    public static String validateEmail(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (EMAIL_PATTERN.matcher(input).matches()) {
                return input;
            }
            System.out.println("Email không hợp lệ. Vui lòng nhập đúng định dạng email.");
        }
    }

    // Validate số điện thoại Việt Nam
    public static String validatePhone(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (PHONE_PATTERN.matcher(input).matches()) {
                return input;
            }
            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại di động Việt Nam.");
        }
    }


    // Validate ngày theo định dạng dd/MM/yyyy
    public static LocalDate validateDate(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập đúng định dạng dd/MM/yyyy.");
            }
        }
    }
}
