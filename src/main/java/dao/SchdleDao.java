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
        String query =
            "UPDATE TB_SCHDLE \n" +
            "SET \n" +
                "\t SCHDLE_TTL = '" + body.getString("schdleTtl") + "',\n" +
                "\t SCHDLE_CN = '" + body.getString("schdleCn") + "',\n" +
                "\t MODF_DT = '" + body.getString("modfDt") + "',\n" +
                "\t MODF_ID = '" + body.getString("modfId") + "'\n" +
            "WHERE \n" +
                "\t SCHDLE_ID = '" + body.getString("schdleId") + "'";
        dao.update(query);
    }

    public void deleteSchdle(BaseMap body) {
        String query =
            "UPDATE TB_SCHDLE_DTL \n" +
            "SET \n" +
                "\t USE_YN = 0,\n" +
                "\t MODF_DT = '" + body.getString("modfDt") + "',\n" +
                "\t MODF_ID = '" + body.getString("modfId") + "'\n" +
            "WHERE \n" +
                "\t SCHDLE_ID = '" + body.getString("schdleId") + "'";;
        dao.delete(query);
    }

    public List<BaseMap> selectSchdleDetail(BaseMap body) {
        String query =
            "SELECT \n" +
                "\t SCHDLE_DTL_ID, \n" +
                "\t DATE_FORMAT(START_DATE, '%Y-%m-%d %H:%i:%s') AS START_DATE, \n" +
                "\t DATE_FORMAT(END_DATE, '%Y-%m-%d %H:%i:%s') AS END_DATE, \n" +
                "\t USE_YN, \n" +
                "\t CREATE_DT  \n" +
            "FROM \n" +
                "\t TB_SCHDLE_DTL \n" +
            "WHERE \n" +
                "\t SCHDLE_ID = '" + body.getString("schdleId") + "' \n";
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

    public List<BaseMap> selectSchdleAll() {
        String query =
            "SELECT \n" +
                "\t TS.SCHDLE_ID, \n" +
                "\t TSD.SCHDLE_DTL_ID, \n" +
                "\t TS.SCHDLE_TTL, \n" +
                "\t TS.SCHDLE_CN, \n" +
                "\t TSD.START_DATE \n" +
            "FROM \n" +
                "\t TB_SCHDLE TS \n" +
                "JOIN \n" +
                "\t TB_SCHDLE_DTL TSD \n" +
                "\t ON TS.SCHDLE_ID = TSD.SCHDLE_ID \n" +
            "WHERE \n" +
                "\t TSD.USE_YN = 1 \n" +
                "\t AND TSD.START_DATE > NOW()";
        return dao.selectAll(query);
    }
}
