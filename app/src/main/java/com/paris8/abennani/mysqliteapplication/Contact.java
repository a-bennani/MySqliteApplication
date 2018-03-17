package com.paris8.abennani.mysqliteapplication;

/**
 * Created by tifoo on 25/01/2018.
 */

public class Contact {

    private long CONTACT_ID;
    private String FIRST_NAME;
    private String LAST_NAME;
    private String COMPANY;
    private String PHONE_NUMBER;
    private String MAIL_ADRESS;

    public Contact(){

    }

    public Contact(long id, String fn, String ln, String cmp, String ph, String mail)
    {
        this.CONTACT_ID = id;
        this.FIRST_NAME = fn;
        this.LAST_NAME = ln;
        this.COMPANY = cmp;
        this.PHONE_NUMBER = ph;
        this.MAIL_ADRESS = mail;
    }

    public void setContactID(long id){
        this.CONTACT_ID = id;
    }

    public long getContactID(){
        return this.CONTACT_ID;
    }


    public void setFirstName(String fn){
        this.FIRST_NAME = fn;
    }

    public String getFirstName(){
        return this.FIRST_NAME;
    }


    public void setLastName(String ln){
        this.LAST_NAME = ln;
    }

    public String getlastName(){
        return this.LAST_NAME;
    }


    public void setCompany(String cmp){
        this.COMPANY = cmp;
    }

    public String getCompany(){
        return this.COMPANY;
    }


    public void setPhoneNumber(String ph){
        this.PHONE_NUMBER = ph;
    }


    public String getPhoneNumber(){
        return this.PHONE_NUMBER;
    }

    public void setMailAdress(String mail){
        this.MAIL_ADRESS = mail;
    }

    public String getMailAdress(){
        return this.MAIL_ADRESS;
    }

}
