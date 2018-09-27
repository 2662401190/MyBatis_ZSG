package com.MyBatis.test;

import com.MyBatis.Entity.User;
import com.MyBatis.dao.UserAnnotation;
import com.MyBatis.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 贺威
 * @create 2018-09-23 13:47
 */
public class MyBatisTest {

    /**
     * 1，根据xml配置文件(全局配置文件)，创建一个sqlSessionFactry对象数据源一些运行环境
     *2:sql映射环境xml： 配置每一个sql，及sql的封装规则等
     * 3：将sql的映射文件注册在全局配置环境中
     * @throws IOException
     */
    @Test
    public void  test() throws IOException {
        System.out.println("测试方法");
        String resource = "MyBatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2获取sqlSession实例，能直接执行已经映射的sql的语句
        SqlSession openSessin=sqlSessionFactory.openSession();
        try {
                                                //唯一标识列，对应着 User.xml 中的 id
            User user = openSessin.selectOne("User.selectUser", 1);
            System.out.println("好了" + user);
        }finally {

            openSessin.close();
        }
    }

    /**
     * 获取接口信息
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {
        //获取sqlSessionFactory
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
       //获取session对象
        SqlSession session=sqlSessionFactory.openSession();
        try {
            //获取接口的实现类对象
            //MYbatis会为接口创建一个代理的实现类对象，然后代理对象会自动去执行增删改的操作
            UserDao us=  session.getMapper(UserDao.class);
            User u=us.getUserID(1);
            System.out.println("User:"+u);
        }finally {
            session.close();
        }
    }

    /**
     * 注解的方式查询
     */
    @Test
    public void  test02() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession openSession=sessionFactory.openSession();
        try {
            UserAnnotation ua=openSession.getMapper(UserAnnotation.class);
            User u=ua.getUser(1);
            System.out.println("User"+u);
        }finally{
            openSession.close();
        }
    }

    /**
     *  SqlSession OpenSession=sessionFactory.openSession(); //需要手动提交
     *    SqlSession OpenSession=sessionFactory.openSession(true); //自动提交
     * @throws IOException
     */
    @Test
    public  void addUser() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserDao ud=OpenSession.getMapper(UserDao.class);
           User user =new User(2,"谈乐","女",20);
           ud.addUser(user);
            System.out.println("ud:"+ud);
            OpenSession.commit();
        }finally {
            OpenSession.close();
        }
    }

    @Test
    public void update() throws IOException {
        InputStream inputStream =Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session =sessionFactory.openSession();

        try {
            UserDao ud=session.getMapper(UserDao.class);
            User u=new User(4,"12","q",30);
            ud.update(u);
            System.out.println("修改成功");
            session.commit();
        }finally{
            session.close();
        }
    }


    @Test
    public  void delete() throws IOException {

        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserDao ud=OpenSession.getMapper(UserDao.class);

            ud.delete(2);
            System.out.println("ud:"+ud);
            OpenSession.commit();
        }finally {
            OpenSession.close();
        }

    }

}
