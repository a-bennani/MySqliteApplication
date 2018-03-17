package com.paris8.abennani.mysqliteapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tifoo on 02/02/2018.
 */

public class DataBase {
    private static final String TAG = "MyDatabase";
    protected final static int VERSION = 1;
    protected final static String NAME = "mydatabase.db";

    private final Context mContext;

    protected myDataBaseHandler m_DBHandler;
    protected DAOContacts m_DAOcontact;



    public DataBase open(){
        m_DBHandler = new myDataBaseHandler ( mContext);
        SQLiteDatabase m_DB = m_DBHandler.getWritableDatabase();
        m_DAOcontact = new DAOContacts(m_DB);
        return this;
    }

    public void close() {
        m_DBHandler.close();
    }


    public DataBase(Context context) {
        this.mContext = context;
    }


    private static class myDataBaseHandler extends SQLiteOpenHelper {

        public myDataBaseHandler(Context context){
            super(context, NAME, null, VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(IContactsShema.CONTACT_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "
                    + IContactsShema.CONTACT_TABLE_NAME);
            onCreate(db);
        }

    }

}



