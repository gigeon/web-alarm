package service;

import com.BaseMap;
import com.Dao;
import dao.DateDao;
import dao.UserDao;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class DateService {
    private DateDao dateDao;

    public DateService(Dao dao) {
        dateDao = new DateDao(dao);
    }

    public List<BaseMap> readDateAll(BaseMap body) {
        return dateDao.selectDateAll(body);
    }

    public BaseMap readDate(BaseMap body) {
        return dateDao.selectDate(body);
    }

    public void updateDateAll(BaseMap body, HttpServletRequest request) {
        body = body.setUpdate(request);
        updateDtlDate(body);
    }

    public void updateDtlDate(BaseMap body) {
        dateDao.updateDtlDate(body);
    }

    public void updateDate(BaseMap body) {
        dateDao.updateDate(body);
    }

    public void deleteDate(BaseMap body, HttpServletRequest request) {
        body = body.setUpdate(request);
        dateDao.deleteDate(body);
    }

    public void createDate(BaseMap body, HttpServletRequest request) {
        body = body.setUpdate(request);
        dateDao.insertDate(body);
        dateDao.insertDateDtl(body);
    }
}
