package ra.edu.business.service.admin;

import ra.edu.business.dao.admin.AdminDao;
import ra.edu.business.dao.admin.AdminDaoImp;

public class AdminServiceImp implements AdminService {
    private final AdminDao adminDao = new AdminDaoImp();

    @Override
    public boolean loginAdmin(String username, String password) {
        return adminDao.checkLogin(username, password);
    }
}
