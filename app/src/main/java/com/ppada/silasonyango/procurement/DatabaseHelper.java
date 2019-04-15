package com.ppada.silasonyango.procurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SilasOnyango on 11/15/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_Details";
    public static final String SECOND_TABLE_NAME="bookmark";
    public static final String COL_4="ID";
    public static final String COL_5="HEADER";

    public static final String COL_1="ID";
    public static final String COL_2="HEADER";
    public static final String COL_3="PARAGRAPH";

    public static final String THIRD_TABLE_NAME="Users";
    public static final String COL_6="dbId";
    public static final String COL_7="id";
    public static final String COL_8="name";
    public static final String COL_9="email";
    public static final String COL_10="holder";

    public static final String FOURTH_TABLE_NAME="PaintShare";
    public static final String COL_11="dbId";
    public static final String COL_12="Name";
    public static final String COL_13="Topic";
    public static final String COL_14="StartIndex";
    public static final String COL_15="EndIndex";


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,HEADER VARCHAR(200),PARAGRAPH VARCHAR(700000))");
        db.execSQL("CREATE TABLE " + SECOND_TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,HEADER VARCHAR(2000))");
        db.execSQL("CREATE TABLE " + THIRD_TABLE_NAME + "(dbId INTEGER PRIMARY KEY AUTOINCREMENT,id VARCHAR(200),name VARCHAR(200),email VARCHAR(200),holder VARCHAR(200))");

        db.execSQL("CREATE TABLE " + FOURTH_TABLE_NAME + "(dbId INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(500),Topic VARCHAR(500),StartIndex VARCHAR(500),EndIndex VARCHAR(500))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +SECOND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +THIRD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +FOURTH_TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String header,String paragraph)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,header);
        contentValues.put(COL_3, paragraph);


        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +TABLE_NAME+ " ORDER BY "+COL_1+ " DESC",null);
        return res;
    }

    public Cursor getSpecificData(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +TABLE_NAME+ " WHERE " +COL_2+ " LIKE '%" +name+ "%';",null);
        return res;
    }



    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME, " HEADER = ?",new String[] {id});
    }

    public Cursor average()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT AVG(MARKS) FROM " + TABLE_NAME, null);
        return res;
    }


    public boolean insertSecondTable(String header)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_5,header);



        long result=db.insert(SECOND_TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }


    public Cursor getAllSecondTable()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +SECOND_TABLE_NAME+ " ORDER BY "+COL_4+ " DESC",null);
        return res;
    }

    public Integer deleteBookMarks(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(SECOND_TABLE_NAME, " HEADER = ?",new String[] {id});
    }

    public boolean insertCredentials(String id,String name,String email,String Key)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_7,id);
        contentValues.put(COL_8, name);
        contentValues.put(COL_9, email);
        contentValues.put(COL_10, Key);
        //contentValues.put(COL_5, address);

        long result=db.insert(THIRD_TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllCredentials()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +THIRD_TABLE_NAME,null);
        return res;
    }

    public Integer deleteCredentials(String Key)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(SECOND_TABLE_NAME, " holder = ?",new String[] {Key});
    }


    public boolean updateCredentials(String id,String name,String email,String Key)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_7,id);
        contentValues.put(COL_8, name);
        contentValues.put(COL_9, email);

        //contentValues.put(COL_5, address);


        db.update(THIRD_TABLE_NAME,contentValues, "holder = ?",new String[] {Key});

        return true;

    }

    public boolean insertPaintShare(String Name,String Topic,String StartIndex,String EndIndex)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_12,Name);
        contentValues.put(COL_13, Topic);


        contentValues.put(COL_14,StartIndex);
        contentValues.put(COL_15, EndIndex);


        long result=db.insert(FOURTH_TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllPaintShare()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +FOURTH_TABLE_NAME,null);
        return res;
    }

    public Integer deletePaintShare(String Name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(FOURTH_TABLE_NAME, " Name = ?",new String[] {Name});
    }

    public Cursor getSpecificPaintShare(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM " +FOURTH_TABLE_NAME+ " WHERE " +COL_12+ " LIKE '%" +name+ "%';",null);
        return res;
    }

}

