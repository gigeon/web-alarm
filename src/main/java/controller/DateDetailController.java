package controller;

import com.BaseMap;
import com.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DateService;

import java.io.IOException;

@WebServlet("/date/detail")
public class DateDetailController extends HttpServlet {
    private DateService dateService;

    @Override
    public void init() {
        Dao dao= (Dao) getServletContext().getAttribute("dao");
        dateService = new DateService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // 세션 확인(Redis 사용 하지 않아 주석)
//        HttpSession session = request.getSession();
//        if(
//            !request.getHeader("X-Session-Id").equals(session.getAttribute("sessionId"))
//            && !request.getHeader("X-Session-User").equals(session.getAttribute("userId"))
//        ) {
//            return;
//        }

        BaseMap body = new BaseMap().setMap(request.getParameterMap());

        BaseMap result = dateService.readDate(body);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result.toJson());

    }
}
