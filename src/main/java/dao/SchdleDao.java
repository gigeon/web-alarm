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

    public List<BaseMap> selectSchdleDetail(BaseMap body) {
        String query =
            "SELECT \n" +
                "\t SCHDLE_DTL_ID, \n" +
                "\t START_DATE, \n" +
                "\t END_DATE, \n" +
                "\t CREATE_DT  \n" +
            "FROM \n" +
                "\t tb_schdle_dtl \n" +
            "WHERE \n" +
                "\t SCHDLE_ID = '" + body.getString("schdleId") + "' \n" +
                "\t and USE_YN = 1";
        return dao.selectAll(query);
    }

    public BaseMap selectSchdleBySchdleId(BaseMap body) {
        String query =
                "SELECT \n" +
                    "\t SCHDLE_ID, \n" +
                    "\t SCHDLE_TTL, \n" +
                    "\t SCHDLE_CN, \n" +
                    "\t CREATE_DT \n" +
                "FROM \n" +
                    "\t TB_SCHDLE \n" +
                "WHERE \n" +
                    "\t SCHDLE_ID = '" + body.getString("schdleId") + "'";
        return dao.select(query);
    }
}
