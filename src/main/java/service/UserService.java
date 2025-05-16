package service;

import com.BaseMap;
import com.Dao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

public class UserService {
    private UserDao userDao;

    public UserService(Dao dao) {
        userDao = new UserDao(dao);
    }

    public BaseMap login(BaseMap body, HttpServletRequest request) {
        BaseMap result = new BaseMap();
        BaseMap user = userDao.selectByUserId(body);

        HttpSession session = request.getSession();
        String sessionId = UUID.randomUUID().toString();// UUID 추가

        if( !user.isEmpty() ) {
            result.set("flag", "1");
            result.set("sessionId", sessionId);
            session.setAttribute("sessionId", sessionId);
            session.setAttribute("userId", body.get("userId"));
        } else {
            result.set("flag", "0");
        }

        return result;
    }
}
