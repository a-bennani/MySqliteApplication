package com.paris8.abennani.mysqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tifoo on 25/01/2018.
 */

public class DAOContacts extends DBContentProvider implements IContactsShema, IContactDAO {

    private Cursor cursor;

    public DAOContacts(SQLiteDatabase m_DB) {
        super(m_DB);
    }

    public boolean add(Contact entry)
    {
        ContentValues value = new ContentValues();
        value.put(CONTACT_FIRST_NAME, entry.getFirstName());
        value.put(CONTACT_LAST_NAME, entry.getlastName());
        value.put(CONTACT_COMPANY, entry.getCompany());
        value.put(CONTACT_PHONE_NUMBER, entry.getPhoneNumber());
        value.put(CONTACT_MAIL_ADRESS, entry.getMailAdress());
        try {
            return super.insert(CONTACT_TABLE_NAME, value) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }
    }

    public boolean delete(int id)
    {
        final String Where =  CONTACT_ID + " = ?" ;
        final String Args[] = {String.valueOf(id)};
        try {
            return super.delete(CONTACT_TABLE_NAME,Where, Args) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("Database", ex.getMessage());
            return false;
        }

    }

    public Contact fetchUserByID(int id) {
        final String selectionArgs[] = { String.valueOf(id) };
        final String selection = CONTACT_ID + " = ?";
        Contact entry = new Contact();
        cursor = super.query(CONTACT_TABLE_NAME, CONTACT_COLUMNS, selection,
                selectionArgs, CONTACT_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                entry = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return entry;
    }

    public List<Contact> fetchAllEntrys() {
        List<Contact> userList = new ArrayList<Contact>();
        cursor = super.query(CONTACT_TABLE_NAME, CONTACT_COLUMNS, null,
                null, CONTACT_ID);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Contact entry = cursorToEntity(cursor);
                userList.add(entry);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return userList;
    }

    protected Contact cursorToEntity(Cursor cursor) {

        Contact entry = new Contact();

        int idIndex;
        int FirstNameIndex;
        int LastNameIndex;
        int CompagnyIndex;
        int PhoneNumberIndex;
        int EmailIndex;

        if (cursor != null) {
            if (cursor.getColumnIndex(CONTACT_ID) != -1) {
                idIndex = cursor.getColumnIndexOrThrow(CONTACT_ID);
                entry.setContactID( cursor.getInt(idIndex));
            }
            if (cursor.getColumnIndex(CONTACT_FIRST_NAME) != -1) {
                FirstNameIndex = cursor.getColumnIndexOrThrow(
                        CONTACT_FIRST_NAME);
                entry.setFirstName(cursor.getString(FirstNameIndex));
            }
            if (cursor.getColumnIndex(CONTACT_LAST_NAME) != -1) {
                LastNameIndex = cursor.getColumnIndexOrThrow(
                        CONTACT_LAST_NAME);
                entry.setLastName(cursor.getString(LastNameIndex));
            }
            if (cursor.getColumnIndex(CONTACT_COMPANY) != -1) {
                CompagnyIndex = cursor.getColumnIndexOrThrow(
                        CONTACT_COMPANY);
                entry.setCompany(cursor.getString(CompagnyIndex));
            }
            if (cursor.getColumnIndex(CONTACT_PHONE_NUMBER) != -1) {
                PhoneNumberIndex = cursor.getColumnIndexOrThrow(
                        CONTACT_PHONE_NUMBER);
                entry.setPhoneNumber(cursor.getString(PhoneNumberIndex));
            }
            if (cursor.getColumnIndex(CONTACT_MAIL_ADRESS) != -1) {
                EmailIndex = cursor.getColumnIndexOrThrow(
                        CONTACT_MAIL_ADRESS);
                entry.setMailAdress(cursor.getString(EmailIndex));
            }

        }
        return entry;
    }


    public Contact select(){
        Cursor cursor = super.rawQuery("select * from " + CONTACT_TABLE_NAME , new String[]{"LS"});
        Contact entry = null;
        while (cursor.moveToNext())
        {
            long ids = cursor.getLong(0);
            String fn = cursor.getString(1);
            String ln = cursor.getString(2);
            String cmp = cursor.getString(3);
            String ph = cursor.getString(4);
            String mail = cursor.getString(5);
            entry = new Contact(ids, fn, ln, cmp, ph, mail);
        }
        cursor.close();
        return entry;
    }



}
