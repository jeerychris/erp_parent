package cn.itcast.erp.test.dao;

import cn.itcast.erp.dao.IDepDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        IDepDao depDao = (IDepDao) applicationContext.getBean("depDao");
        System.out.println(depDao.getList());
    }

}
