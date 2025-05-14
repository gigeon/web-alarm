package service;

import com.BaseMap;
import com.Dao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

public class UserService {
    private UserDao userDao;

    public UserService(Dao dao) {
        userDao = new UserDao(dao);
    }

    public BaseMap login(BaseMap body, HttpServletRequest request) {
        return userDao.selectByUserId(body);
    }
}
