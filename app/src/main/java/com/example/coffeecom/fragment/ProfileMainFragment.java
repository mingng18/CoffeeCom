package com.example.coffeecom.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.ProfileBrewHistoryAdapter;
import com.example.coffeecom.adapter.ProfileOrderHistoryAdapter;
import com.example.coffeecom.adapter.ProfilePostHistoryAdapter;
import com.example.coffeecom.model.BrewedOrderModel;
import com.example.coffeecom.model.PostModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ProfileMainFragment extends Fragment {

    private TextView txtProfileName, txtProfileType;
    private ImageView imgBarista;
    private RecyclerView orderListRV, brewListRV, postListRV;
    private ImageButton btnEditProfile;
    private ConstraintLayout btnTerms, btnPrivacy, btnBankCard, btnHelpDesk, btnFeedback, btnLogOut;

    ArrayList<PostModel> myPost;

    //Adapter
    ProfileOrderHistoryAdapter orderAdapter;
    ProfileBrewHistoryAdapter brewAdapter;
    ProfilePostHistoryAdapter postAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_profile_main,container,false);
        initialiseID(view);

        //Adapter
        orderAdapter = new ProfileOrderHistoryAdapter();
        orderListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        brewAdapter = new ProfileBrewHistoryAdapter(Provider.getUser().getBrewedOrder());
        brewListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        postAdapter = new ProfilePostHistoryAdapter(myPost);
        postListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        orderListRV.setAdapter(orderAdapter);
        brewListRV.setAdapter(brewAdapter);
        postListRV.setAdapter(postAdapter);


        // Inflate the layout for this fragment
        return view;
    }

    private void initialiseID(View view){
        txtProfileName = view.findViewById(R.id.textViewProfileName);
        txtProfileType = view.findViewById(R.id.textViewProfileType);
        btnEditProfile = view.findViewById(R.id.imageButtonProfileEdit);
        btnTerms = view.findViewById(R.id.btnProfile1);
        btnPrivacy = view.findViewById(R.id.btnProfile2);
        btnBankCard = view.findViewById(R.id.btnProfile3);
        btnHelpDesk = view.findViewById(R.id.btnProfile4);
        btnFeedback = view.findViewById(R.id.btnProfile5);
        btnLogOut = view.findViewById(R.id.btnProfile6);
        imgBarista = view.findViewById(R.id.baristaPic);
        orderListRV = view.findViewById(R.id.orderListRV);
        brewListRV = view.findViewById(R.id.brewListRV);
        postListRV = view.findViewById(R.id.postListRV);
    }
}