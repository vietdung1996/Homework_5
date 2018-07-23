package com.vietdung.homework_5.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vietdung.homework_5.R;
import com.vietdung.homework_5.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class DBContact extends SQLiteOpenHelper {

    public String TB_CONTACT = "CONTACT";
    public String TB_CONTACT_ID = "ID";
    public String TB_CONTACT_NAME = "NAME";
    public String TB_CONTACT_PHONE = "PHONE";
    Context context;

    public DBContact(Context context) {
        super(context, String.valueOf(R.string.app_name), null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbContact = "CREATE TABLE " + TB_CONTACT + " ( " +
                TB_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TB_CONTACT_NAME + " TEXT, " +
                TB_CONTACT_PHONE + " TEXT )";
        sqLiteDatabase.execSQL(tbContact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_CONTACT);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_CONTACT_NAME, contact.getName());
        values.put(TB_CONTACT_PHONE, contact.getPhone());
        db.insert(TB_CONTACT, null, values);
        db.close();
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_CONTACT, TB_CONTACT_ID + " = ?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int Update(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_CONTACT_NAME, contact.getName());
        values.put(TB_CONTACT_PHONE, contact.getPhone());
        return db.update(TB_CONTACT, values, TB_CONTACT_ID + "=?", new String[]{String.valueOf(contact.getId())});
    }


    public List<Contact> contactList() {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "Select * from " + TB_CONTACT;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }
}
