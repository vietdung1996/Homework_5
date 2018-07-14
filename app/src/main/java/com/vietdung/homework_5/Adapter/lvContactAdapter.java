package com.vietdung.homework_5.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vietdung.homework_5.Model.Contact;
import com.vietdung.homework_5.R;

import java.util.ArrayList;
import java.util.List;

public class lvContactAdapter extends BaseAdapter {
    FragmentActivity context;
    List<Contact> contactArrayList;
    ViewHolderContact viewHolderContact;
    int layout;

    public lvContactAdapter(FragmentActivity context,int layout, List<Contact> contactArrayList) {
        this.context = context;
        this.layout = layout;
        this.contactArrayList = contactArrayList;
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolderContact{
        TextView tv_Name,tv_Phone;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolderContact = new ViewHolderContact();
            view = inflater.inflate(R.layout.custom_layout_contact,viewGroup,false);
            viewHolderContact.tv_Name = view.findViewById(R.id.tvName);
            viewHolderContact.tv_Phone = view.findViewById(R.id.tvPhone);
            view.setTag(viewHolderContact);
        } else {
            viewHolderContact = (ViewHolderContact) view.getTag();
        }
        Contact contact =contactArrayList.get(i);
        viewHolderContact.tv_Name.setText(contact.getName());
        viewHolderContact.tv_Phone.setText(contact.getPhone());
        return view;
    }
}
