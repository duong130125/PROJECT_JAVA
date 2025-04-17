package ra.edu.business.dao.admin;

import ra.edu.business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AdminDaoImp implements AdminDao {
    @Override
    public boolean checkLogin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean isLogin = false;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call PROC_ADMIN_LOGIN(?, ?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                isLogin = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return isLogin;
    }
}
