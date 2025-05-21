package dao;

import com.BaseMap;
import com.Dao;

import java.util.List;

public class SchdleDao {
    private Dao dao;

    public SchdleDao(Dao dao) {
        this.dao = dao;
    }

    public List<BaseMap> selectSchdle(BaseMap body) {
        String query =
            "SELECT \n" +
                "\t SCHDLE_ID, \n" +
                "\t SCHDLE_TTL, \n" +
                "\t SCHDLE_CN, \n" +
                "\t CREATE_DT \n" +
            "FROM \n" +
                "\t TB_SCHDLE";
        return dao.selectAll(query);
    }

    public void insertSchdle(BaseMap body) {
        String query = "";
        dao.insert(query);
    }

    public void updateSchdle(BaseMap body) {
        String query = "";
        dao.update(query);
    }

    public void deleteSchdle(BaseMap body) {
        String query = "";
        dao.delete(query);
    }

    public BaseMap selectSchdleDetail(BaseMap body) {
        String query = "";
        return dao.select(query);
    }
}
