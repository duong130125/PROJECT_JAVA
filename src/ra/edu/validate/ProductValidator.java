//package ra.edu.validate;
//
//public class ProductValidator {
//    public static String validateProductId(Scanner scanner, String message, String regex) {
//        System.out.println(message);
//        do {
//            String inputString = scanner.nextLine();
//            if (Pattern.matches(regex, inputString)) {
//                return inputString;
//            }
//            System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
//        } while (true);
//    }
//
//    public static String isProductExist(Scanner scanner, String value, String type) {
//        do {
//            boolean isExist = false;
//            switch (type) {
//                case "catalogId":
//                    for (int i = 0; i < ShopManagement.currentProductIndex; i++) {
//                        if (ShopManagement.arrProducts[i].getProductId().equals(value)) {
//                            isExist = true;
//                            break;
//                        }
//                    }
//                    break;
//                case "catalogName":
//                    for (int i = 0; i < ShopManagement.currentProductIndex; i++) {
//                        if (ShopManagement.arrProducts[i].getProductName().equals(value)) {
//                            isExist = true;
//                            break;
//                        }
//                    }
//                    break;
//            }
//            if (!isExist) {
//                return value;
//            }
//            System.err.println("Dữ liệu bị trùng lặp, vui lòng nhập lại");
//            value = scanner.nextLine();
//        } while (true);
//
//    }
//}
