import com.Dao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartListener implements ServletContextListener {

    private  Dao dao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        dao = new Dao();
        dao.db_conn();

        sce.getServletContext().setAttribute("dao", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (dao != null) {
            dao.db_close();
        }
    }
}