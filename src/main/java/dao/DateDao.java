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
                "\t TS.SCHDLE_TITLE, \n" +
                "\t TS.SCHDLE_CN \n" +
            "from \n" +
                "\t TB_SCHDLE TS \n" +
            "join \n" +
                "\t TB_SCHDLE_DTL TSD \n" +
                "\t ON TS.SCHDLE_ID = TSD.SCHDLE_ID \n" +
            "where \n" +
                "\t TSD.USE_YN = 1 \n" +
                "\t AND TSD.START_DATE < '" + date + "' \n" +
                "\t AND TSD.END_DATE > '" + date + "'";

        return dao.selectAll(query);
    }


}
