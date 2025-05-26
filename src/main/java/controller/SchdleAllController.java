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

@WebServlet("/schdle/all")
public class SchdleAllController extends HttpServlet {
    private SchdleService schdleService;

    @Override
    public void init() {
        Dao dao= (Dao) getServletContext().getAttribute("dao");
        schdleService = new SchdleService(dao);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<BaseMap> list = schdleService.readSchdleAll();
        BaseMap result = new BaseMap().set("list",list);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(result.toJson());
    }
}
