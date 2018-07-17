package com.vietdung.homework_5.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietdung.homework_5.adapter.RvContactAdapter;
import com.vietdung.homework_5.database.DBContact;
import com.vietdung.homework_5.model.Contact;
import com.vietdung.homework_5.R;

import java.util.Collections;
import java.util.List;

public class RvContactFragment extends Fragment {
    RecyclerView rv_Contact;
    RvContactAdapter rlContactAdapter;
    List<Contact> contactList;
    DBContact dbContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv_contact, container, false);

        rv_Contact = view.findViewById(R.id.rvContact);
        dbContact = new DBContact(getActivity());
        showContactList();
        return view;
    }

    public void showContactList() {
        contactList = dbContact.contactList();
        Collections.reverse(contactList);
        rlContactAdapter = new RvContactAdapter(getActivity(), contactList);
        rv_Contact.setAdapter(rlContactAdapter);
        rlContactAdapter.notifyDataSetChanged();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rv_Contact.setLayoutManager(gridLayoutManager);
    }
}
