package smktelkom_mlg.macitourapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MirzaUY on 3/3/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "macitour.db";
    private static final String TABLE_NAME = "tb_user";
    private static final String COLUMN_ID = "id_user";
    private static final String COLUMN_NAME = "nama_user";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String TABLE_CREATE = "create table tb_user (id_user integer primary key not null ,"+
            "nama_user text not null , email text not null , username text not null , password text not null);";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public void insertUser(DataUser data){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "select*from tb_user";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_EMAIL, data.getEmail());
        values.put(COLUMN_USERNAME, data.getUsername());
        values.put(COLUMN_PASSWORD, data.getPassword());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public String LoginUser(String username){
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b = "NOT FOUND";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if(a.equals(username)){
                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public String NamaUser(String username) {
        db = this.getReadableDatabase();
        String query = "select username, nama_user from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "NOT FOUND";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
