package dao;

import com.BaseMap;
import com.Dao;

public class UserDao {
    private Dao dao;

    public UserDao(Dao dao) {
        this.dao = dao;
    }

    public BaseMap selectByUserId(BaseMap body) {
        BaseMap result =  dao.select(
                "SELECT * FROM TB_USER"
        );

        return result;
    }
}
