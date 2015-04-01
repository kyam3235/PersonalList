package com.symphodia.example.personallist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//データベースを制御するためのクラス
//将来的には削除する(コンテンツプロバイダへ機能を行こうする)
public class DbControl {
    PersonDatabaseHelper mHelper;

    public DbControl(Context context) {
        mHelper = new PersonDatabaseHelper(context);
    }

    /**
     * 挿入
     * @param name 名前
     * @param age 年齢
     * @return id
     */
    public long insert(String name, int age) {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonTable.COLUMN_NAME, name);
        values.put(PersonTable.COLUMN_AGE, age);
        return database.insert(PersonTable.TABLE_PERSON, null, values);
    }

    /**
     * 削除
     * @param id 削除する項目のid
     * @return true:成功, false:失敗
     */
    public boolean delete(long id){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        return database.delete(PersonTable.TABLE_PERSON, PersonTable.COLUMN_ID + "=" + id, null) > 0;
    }

    /**
     * 名前が部分一致する項目の取得
     * @param name 名前
     * @return カーソル
     */
    public Cursor fetchSelectBy(String name){
        SQLiteDatabase database = mHelper.getWritableDatabase();
        Cursor cursor = database.query(PersonTable.TABLE_PERSON,
                new String[]{PersonTable.COLUMN_ID, PersonTable.COLUMN_NAME, PersonTable.COLUMN_AGE},
                "name like ?",
                new String[]{"%" + name + "%"},
                null, null, null);
        return cursor;
    }

    /**
     * 全取得
     * @return カーソル
     */
    public Cursor fetchAll() {
        SQLiteDatabase database = mHelper.getWritableDatabase();
        String sql = "select "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_ID + ", "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_NAME + ", "
                + PersonTable.TABLE_PERSON + "." + PersonTable.COLUMN_AGE
                + " from "
                + PersonTable.TABLE_PERSON + ";";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor;
    }
}
