package controller;

import com.BaseMap;
import com.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.DateService;
import service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/date")
public class dateController extends HttpServlet {
    private DateService dateService;

    @Override
    public void init() throws ServletException {
        Dao dao = (Dao) getServletContext().getAttribute("dao");
        dateService = new DateService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        if(
//            request.getHeader("X-Session-Id") &&
//            request.getHeader("X-Session-User")
//        ) {
//            return;
//        }

        BaseMap body = new BaseMap().setMap(request.getParameterMap());

        List<BaseMap> list = dateService.readDateAll(body);

        BaseMap result = new BaseMap().set("list",list);

        System.out.println(result.toJson());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result.toJson());
    }
}
