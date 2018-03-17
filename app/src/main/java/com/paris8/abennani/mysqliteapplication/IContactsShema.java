package com.paris8.abennani.mysqliteapplication;

/**
 * Created by tifoo on 01/02/2018.
 */

public interface IContactsShema {
    String CONTACT_TABLE_NAME = "contact";

    String CONTACT_ID = "id";
    String CONTACT_FIRST_NAME = "first_name";
    String CONTACT_LAST_NAME = "last_name";
    String CONTACT_COMPANY = "company";
    String CONTACT_PHONE_NUMBER = "phone_number";
    String CONTACT_MAIL_ADRESS = "email";

    String CONTACT_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + CONTACT_TABLE_NAME + " ("
                    + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + CONTACT_FIRST_NAME + " TEXT, "
                    + CONTACT_LAST_NAME + " TEXT, "
                    + CONTACT_COMPANY + " TEXT, "
                    + CONTACT_PHONE_NUMBER + " TEXT, "
                    + CONTACT_MAIL_ADRESS + " TEXT);"
            ;

    String[] CONTACT_COLUMNS = new String[] { CONTACT_ID,
            CONTACT_FIRST_NAME, CONTACT_LAST_NAME, CONTACT_COMPANY, CONTACT_PHONE_NUMBER,  CONTACT_MAIL_ADRESS};

}
