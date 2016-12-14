package com.example.dllo.project_a_cst.my_database;

import com.example.dllo.project_a_cst.else_class.MyApp;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/7.
 */
public class DBTool {
    private static DBTool ourInstance = new DBTool();
    private static MyPersonDao mPersonDao;
    private static MyMusicPersonDao myMusicPersonDao;
    // 对外提供getInstance方法 获取本类的单例对象
    public static DBTool getInstance() {
        if (ourInstance == null){
            synchronized (DBTool.class){
                if (ourInstance ==null){
                    ourInstance = new DBTool();
                }
            }
        }
        // 初始化XXXDao对象
        mPersonDao = MyApp.getDaoSession().getMyPersonDao();
        myMusicPersonDao = MyApp.getDaoSession().getMyMusicPersonDao();
        return ourInstance;
    }

    private DBTool() {
    }
    // 增加单一对象的方法
    public void insertPerson(MyPerson person){
        mPersonDao.insert(person);
    }
    public void insertPerson(MyMusicPerson person){
        myMusicPersonDao.insert(person);
    }

    // 增加集合的方法
//    public void insertList(List<Person> list) {
//        mPersonDao.insertInTx(list);
//    }
    // 删除单一对象方法
    public void deletePerson(MyPerson person){
        mPersonDao.delete(person);
    }
    public void deletePerson(MyMusicPerson person){
        myMusicPersonDao.delete(person);
    }
    // 删除所有内容
    public void deleteAll(){
        mPersonDao.deleteAll();
    }
    public void deleteMusicAll(){
        myMusicPersonDao.deleteAll();
    }
    // 根据id删除
    public void deleteById(Long id){
        mPersonDao.deleteByKey(id);
    }

    // 根据歌名删除
    public void deleteByName(String name){
        DeleteQuery<MyPerson> deleteQuery = mPersonDao.queryBuilder()
                .where(MyPersonDao.Properties.MusicName.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public void deleteMusicByName(String name){
        DeleteQuery<MyMusicPerson> deleteQuery = myMusicPersonDao.queryBuilder()
                .where(MyMusicPersonDao.Properties.Title.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
//    // 根据姓名性别年龄进行删除
//    public void deleteBy(String name, String sex, int age){
//        DeleteQuery<MyPerson> deleteQuery = mPersonDao.queryBuilder()
//                .where(MyPersonDao.Properties.Name.eq(name),MyPersonDao.Properties
//                        .Sex.eq(sex),MyPersonDao.Properties.Age.eq(age)).buildDelete();
//        if (deleteQuery!=null){
//            deleteQuery.executeDeleteWithoutDetachingEntities();
//        }
//    }
    // 查询所有的方法
    public List<MyPerson> queryAll(){
        // 方法一
        List<MyPerson> list = mPersonDao.loadAll();
        // 方法二
        List<MyPerson> personList = mPersonDao.queryBuilder().list();
        return list;
    }
    public List<MyMusicPerson> queryMusicAll(){
        List<MyMusicPerson> list = myMusicPersonDao.loadAll();
        return list;
    }
    // 查重的方法
    // 根据歌名来查询
    public boolean isSave(String name){
        QueryBuilder<MyPerson> queryBuilder = mPersonDao.queryBuilder()
                .where(MyPersonDao.Properties.MusicName.eq(name));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size>0?true:false;
    }
    public boolean isMusicSave(String name){
        QueryBuilder<MyMusicPerson> queryBuilder = myMusicPersonDao.queryBuilder()
                .where(MyMusicPersonDao.Properties.Title.eq(name));
        // 获取到我们要查询的内容的size
        Long size = queryBuilder.buildCount().count();
        return size>0?true:false;
    }

//    public boolean isSave(MyPerson person) {
//        QueryBuilder<MyPerson> queryBuilder = mPersonDao.queryBuilder().where(PersonDao.Properties.Name.eq(person.getName()),
//                PersonDao.Properties.Sex.eq(person.getSex()),PersonDao.Properties.Age.eq(person.getAge()));
//        Long size = queryBuilder.buildCount().count();
//        return size>0?true:false;
//    }

}
