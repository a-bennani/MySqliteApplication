package com.paris8.abennani.mysqliteapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataBase m_DB;
    Contact m_contact;
    ListView m_listView;
    List<Contact> m_contacts;

    private void refreshList()
    {
        m_contacts = m_DB.m_DAOcontact.fetchAllEntrys();
        List<String> nameList = new ArrayList<String>();
        for (Contact m_contact:m_contacts) {
            nameList.add(m_contact.getFirstName() + " " + m_contact.getlastName()) ;
            Log.w("entry name", m_contact.getFirstName());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameList);

        m_listView.setAdapter(adapter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //*********************************
        // ADD ENTRY
        //*********************************


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText fn = new EditText(MainActivity.this);
                final EditText ln = new EditText(MainActivity.this);
                final EditText cmp = new EditText(MainActivity.this);
                final EditText ph = new EditText(MainActivity.this);
                final EditText mail = new EditText(MainActivity.this);
                AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Add Entry");

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                fn.setHint("First Name");
                layout.addView(fn);

                ln.setHint("Last Name");
                layout.addView(ln);

                cmp.setHint("Company");
                layout.addView(cmp);

                ph.setHint("Phone");
                layout.addView(ph);

                mail.setHint("Email");
                layout.addView(mail);

                adb.setView(layout);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        m_contact = new Contact(m_listView.getChildCount(),
                                fn.getText().toString(),
                                ln.getText().toString(),
                                cmp.getText().toString(),
                                ph.getText().toString(),
                                mail.getText().toString());
                        m_DB.m_DAOcontact.add(m_contact);
                        refreshList();
                    }});
                adb.show();

//                Snackbar.make(view, m_DB.m_DAOcontact.fetchUserByID(1).getFirstName(), Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        m_DB = new DataBase(getApplicationContext());
        m_DB.open();
        m_listView = (ListView) findViewById(R.id.listView);

        refreshList();


        final SwipeDetector swipeDetector = new SwipeDetector();

        m_listView.setOnTouchListener(swipeDetector);
        //m_listView.setOnItemClickListener(listener);

        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, final long id) {
                final int positionToRemove = position;
                final int index = (int)m_listView.getItemIdAtPosition(positionToRemove);
                final Contact entry = m_contacts.get(index);


                //*********************************
                // DELETE ENTRY
                //*********************************

                if(swipeDetector.swipeDetected()){
                    AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete " + entry.getFirstName() + " " + entry.getlastName() + "?");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //MyDataObject.remove(positionToRemove);
                            m_DB.m_DAOcontact.delete((int)entry.getContactID());
                            refreshList();

                        }});
                    adb.show();
                }
                else {

                //*********************************
                // SHOW ENTRY
                //*********************************

                    final TextView fn = new TextView(MainActivity.this);
                    final TextView ln = new TextView(MainActivity.this);
                    final TextView cmp = new TextView(MainActivity.this);
                    final TextView ph = new TextView(MainActivity.this);
                    final TextView mail = new TextView(MainActivity.this);
                    AlertDialog.Builder adb=new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Entry Info");

                    LinearLayout layout = new LinearLayout(MainActivity.this);
                    layout.setOrientation(LinearLayout.VERTICAL);


                    fn.setHint("First Name: " + entry.getFirstName());
                    layout.addView(fn);

                    ln.setHint("Last Name: " + entry.getlastName());
                    layout.addView(ln);

                    cmp.setHint("Company: " + entry.getCompany());
                    layout.addView(cmp);

                    ph.setHint("Phone: " + entry.getPhoneNumber());
                    layout.addView(ph);

                    mail.setHint("Email: " + entry.getMailAdress());
                    layout.addView(mail);

                    adb.setView(layout);
                    adb.setPositiveButton("OK", null);
                    adb.show();
                }
            }
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        m_DB.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
