package ra.edu.presentation;

public class CustomerUI {
    public static void displayCustomerMenu() {
        do {
            System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Hiển thị danh sách khách hàng");
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhật thông tin khách hàng");
            System.out.println("4. Xóa khách hàng theo ID");
            System.out.println("5. Quay lại menu chính");
            System.out.println("=======================================");
            System.out.print("Nhập lựa chọn của bạn: ");
        } while (true);
    }
}
