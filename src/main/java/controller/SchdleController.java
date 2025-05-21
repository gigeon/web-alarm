package controller;

import com.BaseMap;
import com.Dao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SchdleService;

import java.io.IOException;
import java.util.List;

@WebServlet("/schdle")
public class SchdleController extends HttpServlet {
    private SchdleService schdleService;

    @Override
    public void init() {
        Dao dao= (Dao) getServletContext().getAttribute("dao");
        schdleService = new SchdleService(dao);
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

        List<BaseMap> list = schdleService.readSchdle(body);
        BaseMap result = new BaseMap().set("list",list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result.toJson());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // 세션 확인(Redis 사용 하지 않아 주석)
//        HttpSession session = request.getSession();
//        if(
//            !request.getHeader("X-Session-Id").equals(session.getAttribute("sessionId"))
//            && !request.getHeader("X-Session-User").equals(session.getAttribute("userId"))
//        ) {
//            return;
//        }

        BaseMap body = new BaseMap().setOtMap(request);

        schdleService.updateSchdle(body, request);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // 세션 확인(Redis 사용 하지 않아 주석)
//        HttpSession session = request.getSession();
//        if(
//            !request.getHeader("X-Session-Id").equals(session.getAttribute("sessionId"))
//            && !request.getHeader("X-Session-User").equals(session.getAttribute("userId"))
//        ) {
//            return;
//        }

        BaseMap body = new BaseMap().setOtMap(request);

        schdleService.deleteSchdle(body, request);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // 세션 확인(Redis 사용 하지 않아 주석)
//        HttpSession session = request.getSession();
//        if(
//            !request.getHeader("X-Session-Id").equals(session.getAttribute("sessionId"))
//            && !request.getHeader("X-Session-User").equals(session.getAttribute("userId"))
//        ) {
//            return;
//        }

        BaseMap body = new BaseMap().setOtMap(request);

        schdleService.createSchdle(body, request);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }
}
