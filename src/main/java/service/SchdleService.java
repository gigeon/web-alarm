package service;

import com.BaseMap;
import com.Dao;
import dao.SchdleDao;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class SchdleService {
    public SchdleDao schdleDao;

    public SchdleService(Dao dao) {
        schdleDao = new SchdleDao(dao);
    }

    public List<BaseMap> readSchdle(BaseMap body) {
        return schdleDao.selectSchdle(body);
    }

    public void createSchdle(BaseMap body, HttpServletRequest request) {
        schdleDao.insertSchdle(body.setUpdate(request));
    }

    public void updateSchdle(BaseMap body, HttpServletRequest request) {
        schdleDao.updateSchdle(body.setUpdate(request));
    }

    public void deleteSchdle(BaseMap body, HttpServletRequest request) {
        schdleDao.deleteSchdle(body.setUpdate(request));
    }

    public BaseMap readSchdleDetail(BaseMap body) {
        BaseMap result = new BaseMap();
        result
            .set("list", schdleDao.selectSchdleDetail(body))
            .set("schdle", schdleDao.selectSchdleBySchdleId(body));
        return result;
    }
}
