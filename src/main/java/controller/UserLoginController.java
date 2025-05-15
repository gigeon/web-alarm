package controller;

import com.BaseMap;
import com.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/user/login")  // ✅ 올바른 위치
public class UserLoginController extends HttpServlet {  // ✅ 클래스명 대문자 시작 권장
    private UserService userService;

    @Override
    public void init() throws ServletException {
        Dao dao = (Dao) getServletContext().getAttribute("dao");
        userService = new UserService(dao);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BaseMap body = new BaseMap().setMap(request.getParameterMap());

        BaseMap result = userService.login(body, request);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result.toJson());
    }
}

