package dao;

import com.BaseMap;
import com.Dao;

import java.util.List;

public class DateDao {
    private Dao dao;

    public DateDao(Dao dao) {
        this.dao = dao;
    }

    public List<BaseMap> selectDateAll(BaseMap body) {
        String date = body.getString("year") + "-" + body.getString("month") + "-" + body.getString("day");

        String query =
            "select \n" +
                "\t TS.SCHDLE_ID, \n" +
                "\t TSD.SCHDLE_DTL_ID, \n" +
                "\t TS.SCHDLE_TTL, \n" +
                "\t TS.SCHDLE_CN \n" +
            "from \n" +
                "\t TB_SCHDLE TS \n" +
            "join \n" +
                "\t TB_SCHDLE_DTL TSD \n" +
                "\t ON TS.SCHDLE_ID = TSD.SCHDLE_ID \n" +
            "where \n" +
                "\t TSD.USE_YN = 1 \n" +
                "\t AND TSD.START_DT < '" + date + "' \n" +
                "\t AND TSD.END_DT > '" + date + "'";

        return dao.selectAll(query);
    }

    public BaseMap selectDate(BaseMap body) {
        String query =
            "select \n" +
                "\t TS.SCHDLE_ID, \n" +
                "\t TSD.SCHDLE_DTL_ID, \n" +
                "\t TS.SCHDLE_TTL, \n" +
                "\t TS.SCHDLE_CN,\n" +
                "\t TSD.START_DT,\n" +
                "\t TSD.END_DT \n" +
            "from \n" +
                "\t TB_SCHDLE TS \n" +
            "join \n" +
                "\t TB_SCHDLE_DTL TSD \n" +
                "\t ON TS.SCHDLE_ID = TSD.SCHDLE_ID \n" +
            "where \n" +
                "\t TSD.USE_YN = 1 \n" +
                "\t AND TSD.SCHDLE_DTL_ID = '" + body.getString("schdleDtlId") + "'";

        return dao.select(query);
    }

    public void updateDtlDate(BaseMap body) {
        String query =
            "UPDATE TB_SCHDLE_DTL \n" +
            "SET \n" +
                "\t START_DT = '" + body.getString("startDt") + "', \n" +
                "\t END_DT = '" + body.getString("endDt") + "', \n" +
                "\t MODF_DT = '" + body.getString("modfDt") + "', \n" +
                "\t MODF_ID = '" + body.getString("modfId") + "' \n" +
            "WHERE \n" +
                "\t SCHDLE_DTL_ID = '" + body.getString("schdleDtlId") + "'";

        dao.update(query);
    }

    public void deleteDate(BaseMap body) {
        String query =
            "UPDATE TB_SCHDLE_DTL \n" +
            "SET \n" +
                "\t USE_YN = 0, \n" +
                "\t MODF_DT = '" + body.getString("modfDt") + "', \n" +
                "\t MODF_ID = '" + body.getString("modfId") + "' \n" +
            "WHERE \n" +
                "\t SCHDLE_DTL_ID = '" + body.getString("schdleDtlId") + "'";

        dao.delete(query);
    }

    public void insertDate(BaseMap body) {
        String query =
            "INSERT \n" +
                "INTO TB_SCHDLE (\n" +
                "\t SCHDLE_ID,\n" +
                "\t SCHDLE_TTL,\n" +
                "\t SCHDLE_CN,\n" +
                "\t CREATE_DT,\n" +
                "\t CREATE_ID,\n" +
                "\t MODF_DT,\n" +
                "\t MODF_ID\n" +
            ") VALUES (\n" +
                "\t NEXTVAL(SQ_SCHDLE), \n" +
                "\t '" + body.getString("schdleTtl") + "', \n" +
                "\t '" + body.getString("schdleCn") + "', \n" +
                "\t '" + body.getString("createDt") + "', \n" +
                "\t '" + body.getString("createId") + "', \n" +
                "\t '" + body.getString("modfDt") + "', \n" +
                "\t '" + body.getString("modfId") + "' \n" +
            ")";

        dao.insert(query);
    }

    public void insertDateDtl(BaseMap body) {
        String query =
            "INSERT \n" +
                "INTO TB_SCHDLE_DTL (\n" +
                "\t SCHDLE_DTL_ID,\n" +
                "\t SCHDLE_ID,\n" +
                "\t START_DT,\n" +
                "\t END_DT,\n" +
                "\t USE_YN,\n" +
                "\t CREATE_DT,\n" +
                "\t CREATE_ID,\n" +
                "\t MODF_DT,\n" +
                "\t MODF_ID\n" +
            ") VALUES (\n" +
                "\t NEXTVAL(SQ_SCHDLE_DTL), \n" +
                "\t (SELECT MAX(SCHDLE_ID) FROM TB_SCHDLE), \n" +
                "\t '" + body.getString("startDt") + "', \n" +
                "\t '" + body.getString("endDt") + "', \n" +
                "\t 1, \n" +
                "\t '" + body.getString("createDt") + "', \n" +
                "\t '" + body.getString("createId") + "', \n" +
                "\t '" + body.getString("modfDt") + "', \n" +
                "\t '" + body.getString("modfId") + "'\n" +
            ")";

        dao.insert(query);
    }

    public void updateDate(BaseMap body) {
        String query = "";

        dao.update(query);
    }



}
