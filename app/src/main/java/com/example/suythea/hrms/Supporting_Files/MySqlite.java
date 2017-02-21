package com.example.suythea.hrms.Supporting_Files;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lolzzlolzz on 10/20/16.
 */

public class MySqlite extends SQLiteOpenHelper {
    Context context;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyHRDatabase";

    String CREATE_TBL_TOPIC ="CREATE TABLE tbl_current_user (id INT, uid INT)";

    public MySqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TBL_TOPIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    public ArrayList<NewsGridModel> getDataFromDB(int keyNote) {
//
//        ArrayList<NewsGridModel> array = new ArrayList<NewsGridModel>();
//
//        String query = "SELECT " + KEY_ID + ", " + KEY_WEBADDRESS + " FROM " + DATABASE_TABLE + " where " + KEY_KEYNOTE + " = " + keyNote;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        NewsGridModel model = null;
//        while(cursor.moveToNext()){
//
//            model = new NewsGridModel(cursor.getString(0),cursor.getString(1));
//            array.add(model);
//        }
//
//        cursor.close();
//        return array;
//    }
//
//    public void insertIntoDB(ArrayList<NewsGridModel> models, int keyNote) {
//
//        deleteAllTheOld(keyNote);
//
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = null;
//
//        for (int i = 0; i < models.size() ; i++){
//            values = new ContentValues();
//            values.put(KEY_ID, models.get(i).getId());
//            values.put(KEY_WEBADDRESS, models.get(i).getWebAddress());
//            values.put(KEY_KEYNOTE, String.valueOf(keyNote));
//            db.insert(DATABASE_TABLE, null, values);
//        }
//
//        db.close();
//    }
//
//    public void deleteAllTheOld(int keyNote){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM " + DATABASE_TABLE + " where " + KEY_KEYNOTE + " = " + keyNote);
//    }

//    public void insert() {
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, 1);
//        values.put(KEY_WEBADDRESS, "OK");
//
//        db.insert(DATABASE_TABLE, null, values);
//        db.close();
//    }



//    public void deleteAll(){
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL("DELETE FROM " + DATABASE_TABLE);
//    }

//    public ArrayList<Topic> getAll() {
//        ArrayList<Topic> topics = new ArrayList<Topic>();
//
//        String query = "SELECT  * FROM " + DATABASE_TABLE;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        Topic topic = null;
//        while(cursor.moveToNext()){
//
//            topic = new Topic();
//            topic.setId(Integer.parseInt(cursor.getString(0)));
//            topic.setTopic(cursor.getString(1));
//            topic.setDescription(cursor.getString(2));
//
//            topics.add(topic);
//
//        }
//
//        cursor.close();
//        return topics;
//    }

}

