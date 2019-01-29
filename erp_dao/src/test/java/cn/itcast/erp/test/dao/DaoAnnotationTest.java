package cn.itcast.erp.test.dao;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:application*.xml")
public class DaoAnnotationTest {

    @Resource(name = "depDao")
    IDepDao depDao;

    @Test
    public void listTest() {
        Dep dep = new Dep();
        dep.setTele("5");
        System.out.println(depDao.getList(dep));
    }
}
