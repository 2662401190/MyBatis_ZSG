package com.MyBatis.test;

import com.MyBatis.Entity.Address;
import com.MyBatis.Entity.User;
import com.MyBatis.dao.UserAnnotation;
import com.MyBatis.dao.UserDao;
import com.MyBatis.dao.UserPlus;
import com.MyBatis.dao.address;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
           User user =new User(null,"谈乐","女",20);
           ud.addUser(user);
            System.out.println("ud:"+user.getName());
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

    /**
     * 多条件查询
     * @throws IOException
     */
    @Test
    public void cx() throws IOException {
        InputStream inputStream =Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpSessin=sessionFactory.openSession();

        try {
            UserDao u=OpSessin.getMapper(UserDao.class);
            User user=u.cxs(4,"12");
            System.out.println("User:"+user);

        }finally {
            OpSessin.close();
        }
    }

    @Test
    public void getmap() throws IOException {
        InputStream inputStream =Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpSessin=sessionFactory.openSession();

        try {
            UserDao u=OpSessin.getMapper(UserDao.class);
            Map<String,Object> map=new  HashMap<String,Object>();
            map.put("id",4);
            map.put("name",12);
            User user=u.getmap(map);
            System.out.println("User:"+user);

        }finally {
            OpSessin.close();
        }
    }

    /**
     * 返回一个map
     */
    @Test
    public void returnMap() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserDao ud=OpenSession.getMapper(UserDao.class);

            Map<String,Object>map=ud.returnMap(1);
            System.out.println("Map:"+map);
        }finally {
            OpenSession.close();
        }
    }

    /**
     * 模糊查询
     * @throws IOException
     */
    @Test
    public void getMapParam() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserDao ud=OpenSession.getMapper(UserDao.class);

            Map<Integer,User>map=ud.getMapParam("%1%");
            System.out.println("Map:"+map.keySet());
        }finally {
            OpenSession.close();
        }
    }

    @Test
    public  void UserID() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserPlus ud=OpenSession.getMapper(UserPlus.class);

            User u=ud.UserID(1);
            System.out.println("Map:"+u.getAddress().getName());
        }finally {
            OpenSession.close();
        }
    }

    /**
     * 两表联查
     * @throws IOException
     */
    @Test
    public  void UserAndAddress() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserPlus ud=OpenSession.getMapper(UserPlus.class);
            User u=ud.getUserAndAddress(1);
            System.out.println("Map:"+u);
            System.out.println("User:"+u.getAddress());
        }finally {
            OpenSession.close();
        }
    }


    /**
     * 分布式查询
     * @throws IOException
     */
    @Test
    public  void fb() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            UserPlus ud=OpenSession.getMapper(UserPlus.class);
            User u=ud.UserID(1);
            System.out.println("Map:"+u);
            System.out.println("User:"+u.getAddress());
        }finally {
            OpenSession.close();
        }
    }

    /**
     * 查询地址表（address）中 用户表（User）集合的信息
     * @throws IOException
     */
    @Test
    public void setuser() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            address a=OpenSession.getMapper(address.class);

            Address address=a.getAddressIDPlus(1);
            System.out.println("address:"+address.getUsers());
        }finally {
            OpenSession.close();
        }

    }

    /**
     * 分布式查询用户表的信息
     * @throws IOException
     */
    @Test
    public void lists() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
            address a=OpenSession.getMapper(address.class);

            Address list=a.getAddressID(1);
            System.out.println("address:"+list.getUsers());
        }finally {
            OpenSession.close();
        }
    }

    /**
     * 鉴别器:  出错了
     * @throws IOException
     */
    @Test
    public void Dis() throws IOException {
        InputStream inputStream=Resources.getResourceAsStream("MyBatis_config.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession OpenSession=sessionFactory.openSession();

        try {
           UserDao ud=OpenSession.getMapper(UserDao.class);
           User u=ud.getUserID(1);

            System.out.println("address:"+u);
            System.out.println("User:"+u.getAddress().getName());
        }finally {
            OpenSession.close();
        }
    }

}
