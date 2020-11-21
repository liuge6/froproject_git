package com.fandrproject.frpro.osanddivide.service.impl;

import com.fandrproject.frpro.osanddivide.bean.ExceptionDataBean;
import com.fandrproject.frpro.osanddivide.service.ExceptionDataService;
import com.fandrproject.frpro.osanddivide.dao.ExceptionDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 异常数据统计impl
 * Created by sml
 * 2020/11/21 23:53
 */
@Service
public class ExceptionDataServiceImpl implements ExceptionDataService {

    @Autowired
    private ExceptionDataDao exceptionDataDao;

    @Override
    public boolean saveExceptionData(List<ExceptionDataBean> list) {

        for (int i = 0; i < list.size(); i++) {
            ExceptionDataBean bean = list.get(i);
            exceptionDataDao.saveException(bean);
        }
        return true;
    }
}
