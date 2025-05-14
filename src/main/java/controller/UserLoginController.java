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

        System.out.println(body);
        BaseMap result = userService.login(body, request);
        System.out.println("result");
        System.out.println(result);

        // 로직 처리 후 (예: 로그인 검증)
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}

