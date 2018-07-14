package com.vietdung.homework_5.Adapter;

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

import com.vietdung.homework_5.Activity.MainActivity;
import com.vietdung.homework_5.Model.Contact;
import com.vietdung.homework_5.R;

import java.util.List;

public class rlContactAdapter extends RecyclerView.Adapter<rlContactAdapter.RecyclerViewHolder> {

     List<Contact> contactList;
     Dialog dialog;
    private Activity context;

    public rlContactAdapter(Activity context,List<Contact> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_layout_contact,parent,false);
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

                Button btn_Delete =dialog.findViewById(R.id.btnDelete);
                Button btn_Save = dialog.findViewById(R.id.btnSaveItem);
                final EditText et_Name = dialog.findViewById(R.id.etNameItem);
                final EditText et_Phone = dialog.findViewById(R.id.etPhoneItem);
                et_Name.setText(MainActivity.contactList.get(position).getName());
                et_Phone.setText(MainActivity.contactList.get(position).getPhone());

                btn_Delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.contactList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                btn_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = et_Name.getText().toString().trim();
                        String phone= et_Phone.getText().toString().trim();
                        if(name.isEmpty() || phone.isEmpty()){
                            Toast.makeText(context, R.string.ErrorInfor, Toast.LENGTH_SHORT).show();
                        }else{
                            MainActivity.contactList.get(position).setName(name);
                            MainActivity.contactList.get(position).setPhone(phone);
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
        TextView tv_Name,tv_Phone;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv_Name =  itemView.findViewById(R.id.tvName);
            tv_Phone = itemView.findViewById(R.id.tvPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
