package com.vietdung.homework_5.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vietdung.homework_5.R;
import com.vietdung.homework_5.database.DBContact;
import com.vietdung.homework_5.model.Contact;

import java.util.List;

public class RvContactAdapter extends RecyclerView.Adapter<RvContactAdapter.RecyclerViewHolder> {

    List<Contact> contactList;
    Dialog dialog;
    DBContact dbContact;
    private Activity context;


    public RvContactAdapter(Activity context, List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
        dbContact = new DBContact(context);


    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_layout_contact, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {

        holder.tv_Phone.setText(contactList.get(position).getPhone());
        holder.tv_Name.setText(contactList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(context);
                dialog.setTitle(R.string.dialogTitleItem);
                dialog.setContentView(R.layout.dialog_item);
                dialog.setCanceledOnTouchOutside(false);

                Button btn_Delete = dialog.findViewById(R.id.btnDelete);
                Button btn_Save = dialog.findViewById(R.id.btnSaveItem);
                final EditText et_Name = dialog.findViewById(R.id.etNameItem);
                final EditText et_Phone = dialog.findViewById(R.id.etPhoneItem);
                et_Name.setText(contactList.get(position).getName());
                et_Phone.setText(contactList.get(position).getPhone());

                btn_Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbContact.deleteContact(contactList.get(position));
                        contactList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btn_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = et_Name.getText().toString().trim();
                        String phone = et_Phone.getText().toString().trim();
                        if (name.isEmpty() || phone.isEmpty()) {
                            Toast.makeText(context, R.string.ErrorInfor, Toast.LENGTH_SHORT).show();
                        } else {
                            contactList.get(position).setName(name);
                            contactList.get(position).setPhone(phone);
                            dbContact.Update(contactList.get(position));
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Name, tv_Phone;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tvName);
            tv_Phone = itemView.findViewById(R.id.tvPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
