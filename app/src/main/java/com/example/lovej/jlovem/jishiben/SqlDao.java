package com.example.lovej.jlovem.jishiben;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/20 0020.
 */

public class SqlDao {
    private OpenHelper helper;
    public SqlDao(Context context) {
        helper=new OpenHelper(context);
    }
    /**
     * 添加
     * @param stu
     * @return
     */
    public boolean add(Student stu){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", stu.getName().toString());
        values.put("sex", stu.getSex().toString());
        long insert = db.insert("student", null, values);
        db.close();
        if(insert !=-1){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 删除
     * @param name
     */
    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL("delete student where name =?",new String[]{name});
        db.delete("student", "name=?", new String[]{name});
        db.close();
    }
    /**
     * 修改
     * @param name
     * @param newsex
     */
    public void update(String name, String newsex){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update student set sex=? where name=?",new Object[]{name,newsex});
        db.close();
    }
    /**
     *查找学生姓名
     * @param
     * @return
     */
    public Student findName(Student stu2 ){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student where name=?", new String[]{stu2.getName()});
        Student s = new Student();
        while(cursor.moveToNext()){
//			String name = cursor.getString(1);
            s.setName(stu2.getName());
        }
        cursor.close();
        db.close();
        return s;
    }
    //查询所有学生
    public List<Student> findAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        List<Student> list =new ArrayList<Student>();
        Cursor cursor = db.query("student", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            //创建学生对象
            Student s = new Student();
            String name = cursor.getString(1);
            String sex = cursor.getString(2);
            //添加到学生bean里面
            s.setName(name);
            s.setSex(sex);
            list.add(s);
        }
        db.close();
        cursor.close();
        return list;

    }
}

