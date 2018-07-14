package com.vietdung.homework_5.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.vietdung.homework_5.Activity.MainActivity;
import com.vietdung.homework_5.Adapter.lvContactAdapter;
import com.vietdung.homework_5.Model.Contact;
import com.vietdung.homework_5.R;

import java.util.ArrayList;
import java.util.List;

public class lvContactFragment extends Fragment {
    ListView lv_Contact;
    //List<Contact> contactList;
    lvContactAdapter lvContactAdapter;
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lv_contact,container,false);

        lv_Contact = view.findViewById(R.id.lvContact);
        //contactList = new ArrayList<>();
        lvContactAdapter = new lvContactAdapter(getActivity(),R.layout.custom_layout_contact, MainActivity.contactList);
        lvContactAdapter.notifyDataSetChanged();
        lv_Contact.setAdapter(lvContactAdapter);
        eventClick();
        return view;
    }

    private void eventClick() {
        lv_Contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                dialog = new Dialog(getActivity());
                dialog.setTitle(R.string.dialogTitleItem);
                dialog.setContentView(R.layout.dialog_item);
                dialog.setCanceledOnTouchOutside(false);

                Button btn_Delete =dialog.findViewById(R.id.btnDelete);
                Button btn_Save = dialog.findViewById(R.id.btnSaveItem);
                final EditText et_Name = dialog.findViewById(R.id.etNameItem);
                final EditText et_Phone = dialog.findViewById(R.id.etPhoneItem);
                et_Name.setText(MainActivity.contactList.get(i).getName());
                et_Phone.setText(MainActivity.contactList.get(i).getPhone());

                btn_Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.contactList.remove(i);
                        lvContactAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btn_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = et_Name.getText().toString().trim();
                        String phone= et_Phone.getText().toString().trim();
                        if(name.isEmpty() || phone.isEmpty()){
                            Toast.makeText(getActivity(), R.string.ErrorInfor, Toast.LENGTH_SHORT).show();
                        }else{
                            MainActivity.contactList.get(i).setName(name);
                            MainActivity.contactList.get(i).setPhone(phone);
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }
        });

    }

}

