package com.example.coffeecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.adapter.BankCardAdapter;
import com.example.coffeecom.adapter.CoffeeBaristaListAdapter;

public class WalletActivity extends AppCompatActivity {

    TextView walletAmountText;
    Button transactionBtn, topUpWalletBtn;
    Button topUp5Btn, topUp10Btn, topUp20Btn, topUp30Btn, topUp50Btn, topUp100Btn;
    TextView topUpAmountTextBox;

    RecyclerView bankCardRecyclerView;
    RecyclerView.Adapter bankCardRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        walletAmountText = findViewById(R.id.walletAmountText);
        transactionBtn = findViewById(R.id.transactionBtn);
        topUpWalletBtn = findViewById(R.id.topUpWalletBtn);
        topUp5Btn = findViewById(R.id.topUp5Btn);
        topUp10Btn = findViewById(R.id.topUp10Btn);
        topUp20Btn = findViewById(R.id.topUp20Btn);
        topUp30Btn = findViewById(R.id.topUp30Btn);
        topUp50Btn = findViewById(R.id.topUp50Btn);
        topUp100Btn = findViewById(R.id.topUp100Btn);
        topUpAmountTextBox = findViewById(R.id.topUpAmountTextBox);

        try {
            walletAmountText.setText(String.format("%.2f", Provider.getUser().getWalletBalance()));
        }catch (NullPointerException e){
            walletAmountText.setText(String.valueOf(0.00));
        }

        topUpFillNumber();

        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(this, TransactionActivity.class));
            }
        });
        recyclerViewBankCard();

        topUpWalletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(this, new StatusActivity("Top Up Successful", "Top up Successfully!", "", new Intent(this, WalletActivity.class)));
            }
        });
    }

    public void topUpFillNumber() {
        topUp5Btn.setOnClickListener(view -> topUpAmountTextBox.setText(String.valueOf(5)));
        topUp10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(10));
            }
        });
        topUp20Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(20));
            }
        });
        topUp30Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(30));
            }
        });
        topUp50Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(50));
            }
        });
        topUp100Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topUpAmountTextBox.setText(String.valueOf(100));
            }
        });
    }

    private void recyclerViewBankCard() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bankCardRecyclerView = findViewById(R.id.bankCardRecyclerView);
        bankCardRecyclerView.setLayoutManager(linearLayoutManager);

        bankCardRecyclerViewAdapter = new BankCardAdapter();
        bankCardRecyclerView.setAdapter(bankCardRecyclerViewAdapter);
    }
}