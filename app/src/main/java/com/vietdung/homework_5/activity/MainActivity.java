package com.vietdung.homework_5.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.vietdung.homework_5.R;
import com.vietdung.homework_5.adapter.RvContactAdapter;
import com.vietdung.homework_5.database.DBContact;
import com.vietdung.homework_5.model.Contact;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_Add;
    Switch swChange;
    RecyclerView rv_Contact;
    Dialog dialog;
    public static String name, phone;
    DBContact dbContact;
    List<Contact> contactList;
    RvContactAdapter rlContactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addEvents();
        showList();
    }

    private void addEvents() {
        eventsClick();
        eventsSwitch();
    }

    private void eventsSwitch() {
        swChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    rv_Contact.setLayoutManager(layoutManager);

                } else {

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    rv_Contact.setLayoutManager(gridLayoutManager);
                }
            }
        });
    }

    private void eventsClick() {
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

    }

    private void showDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.setTitle(R.string.dialogTitle);
        dialog.setContentView(R.layout.dialog_add);
        dialog.setCanceledOnTouchOutside(false);
        Button btn_Close = dialog.findViewById(R.id.btnClose);
        Button btn_Save = dialog.findViewById(R.id.btnSave);
        final EditText et_Name = dialog.findViewById(R.id.etName);
        final EditText et_Phone = dialog.findViewById(R.id.etPhone);


        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_Name.getText().toString().trim();
                phone = et_Phone.getText().toString().trim();
                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.ErrorInfor, Toast.LENGTH_SHORT).show();
                    Log.d("name", name);
                    Log.d("phone", phone);
                } else {
                    Contact contact = new Contact();
                    contact.setName(name);
                    contact.setPhone(phone);
                    dbContact.addContact(contact);
                    showList();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void initView() {
        btn_Add = findViewById(R.id.btnAdd);
        swChange = findViewById(R.id.swChange);
        rv_Contact = findViewById(R.id.rvContact);
        dbContact = new DBContact(getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rv_Contact.setLayoutManager(gridLayoutManager);


    }

    private void showList(){
        contactList = dbContact.contactList();
        Collections.reverse(contactList);
        rlContactAdapter = new RvContactAdapter(MainActivity.this, contactList);
        rlContactAdapter.notifyDataSetChanged();
        rv_Contact.setAdapter(rlContactAdapter);
    }

}

