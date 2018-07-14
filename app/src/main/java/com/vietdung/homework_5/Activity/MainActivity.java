package com.vietdung.homework_5.Activity;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.vietdung.homework_5.Fragment.lvContactFragment;
import com.vietdung.homework_5.Fragment.rlContactFragment;
import com.vietdung.homework_5.Model.Contact;
import com.vietdung.homework_5.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    FrameLayout frameLayout;
    Button btn_Add;
    Switch swChange;
    FragmentManager fragmentManager;
    Dialog dialog;
    public static String name,phone;
    public static List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addEvents();
        getData();
    }



    private void addEvents() {
        eventsSwitch();
        eventsClick();

    }

    private void eventsSwitch() {
        swChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    FragmentTransaction tranrvContact = fragmentManager.beginTransaction();
                    rlContactFragment rlContactFragment = new rlContactFragment();
                    tranrvContact.replace(R.id.flFragment,rlContactFragment);
                    tranrvContact.commit();
                } else {

                    FragmentTransaction tranlvContact = fragmentManager.beginTransaction();
                    lvContactFragment lvContactFragment = new lvContactFragment();
                    tranlvContact.replace(R.id.flFragment,lvContactFragment);
                    tranlvContact.commit();
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
                phone= et_Phone.getText().toString().trim();
                if(name.isEmpty() || phone.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.ErrorInfor, Toast.LENGTH_SHORT).show();
                    Log.d("name", name);
                    Log.d("phone",phone);
                }else{
                    contactList.add(new Contact(name,phone));
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void initView() {
        btn_Add = findViewById(R.id.btnAdd);
        swChange = findViewById(R.id.swChange);
        fragmentManager = getSupportFragmentManager();
        if(contactList==null){
            contactList =new ArrayList<>();
        }

        FragmentTransaction tranlvContact = fragmentManager.beginTransaction();
        lvContactFragment lvContactFragment = new lvContactFragment();
        tranlvContact.replace(R.id.flFragment,lvContactFragment);
        tranlvContact.commit();
    }


    //Giả lập dữ liệu
    private void getData() {
        contactList.add(new Contact("Huy","0123456789"));
        contactList.add(new Contact("Phong","22222222"));
        contactList.add(new Contact("Long","11111111"));
        contactList.add(new Contact("Nam","987654321"));
    }
}

