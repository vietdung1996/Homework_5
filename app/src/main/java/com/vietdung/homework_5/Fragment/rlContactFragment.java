package com.vietdung.homework_5.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vietdung.homework_5.Activity.MainActivity;
import com.vietdung.homework_5.Adapter.rlContactAdapter;
import com.vietdung.homework_5.Model.Contact;
import com.vietdung.homework_5.R;

import java.util.ArrayList;
import java.util.List;

public class rlContactFragment extends Fragment {
    RecyclerView rv_Contact;
    rlContactAdapter rlContactAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv_contact,container,false);


        rv_Contact =view.findViewById(R.id.rvContact);
        rlContactAdapter = new rlContactAdapter(getActivity(),MainActivity.contactList);
        rlContactAdapter.notifyDataSetChanged();
        rv_Contact.setAdapter(rlContactAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rv_Contact.setLayoutManager(gridLayoutManager);


        return view;



    }
}
