package service;

import com.BaseMap;
import com.Dao;
import dao.DateDao;
import dao.UserDao;

import java.util.List;

public class DateService {
    private DateDao dateDao;

    public DateService(Dao dao) {
        dateDao = new DateDao(dao);
    }

    public List<BaseMap> readDateAll(BaseMap body) {
        return dateDao.selectDateAll(body);
    }
}
