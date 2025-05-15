package dao;

import com.BaseMap;
import com.Dao;

public class UserDao {
    private Dao dao;

    public UserDao(Dao dao) {
        this.dao = dao;
    }

    public BaseMap selectByUserId(BaseMap body) {
        String query = String.format("SELECT * FROM TB_USER WHERE USER_ID = %s AND USER_PWD = %s", body.get("userId"), body.get("userPwd"));
        return dao.select(query);
    }
}
