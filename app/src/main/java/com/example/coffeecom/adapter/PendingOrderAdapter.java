package com.example.coffeecom.adapter;

import static com.example.coffeecom.helper.FormatDateTime.convertDatetoStringTime;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coffeecom.Provider;
import com.example.coffeecom.R;
import com.example.coffeecom.model.BrewedOrderModel;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.ArrayList;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderAdapter.ViewHolder>{


    private static final String TAG = "PendingOrderAdapter";
    ArrayList<BrewedOrderModel> brewedOrder;
    Context activity;


    public PendingOrderAdapter(ArrayList<BrewedOrderModel> brewedOrder, Context activity) {
        this.brewedOrder = brewedOrder;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView orderCoffeePic;
        private TextView orderCoffeeName, orderCustomerName, orderCoffeeTime;
        private ImageButton acceptOrderBtn, declineOrderBtn;
        private ConstraintLayout coffeeOrderLayout;

        public ViewHolder(@NonNull View itemView, int viewTypex) {
            super(itemView);

            coffeeOrderLayout= itemView.findViewById(R.id.coffeeOrderLayout);
            orderCoffeePic= itemView.findViewById(R.id.orderCoffeePic);
            orderCoffeeName= itemView.findViewById(R.id.orderCoffeeName);
            orderCustomerName= itemView.findViewById(R.id.orderCustomerName);
            orderCoffeeTime= itemView.findViewById(R.id.orderCoffeeTime);
            acceptOrderBtn= itemView.findViewById(R.id.acceptOrderBtn);
            declineOrderBtn= itemView.findViewById(R.id.declineOrderBtn);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if(brewedOrder.get(position).getOrderStatus().equals("P")){
            return 1; // pending and await accept or decline
        }else if(brewedOrder.get(position).getOrderStatus().equals("A")){
            return 2; // accepted
        }else{
            return 0; //no pending or accepted
        }
    }

    @NonNull
    @Override
    public PendingOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_no_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }else if (viewType == 1){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pending_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }else{
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_accepted_coffee_order, parent, false);
            return new PendingOrderAdapter.ViewHolder(inflate, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderAdapter.ViewHolder holder, int position) {
        if(getItemViewType(position) != 0){
            //Insert details
            holder.orderCoffeeName.setText(brewedOrder.get(position).getOrderedCoffee().get(0).getCoffeeTitle());
            holder.orderCustomerName.setText(brewedOrder.get(position).getCustomerName());
            holder.orderCoffeeTime.setText(convertDatetoStringTime(brewedOrder.get(position).getOrderStartTime()));
            //Insert pic
            String picUrl = brewedOrder.get(position).getOrderedCoffee().get(0).getCoffeePic();
            int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
            Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.orderCoffeePic);

            if(brewedOrder.get(position).getOrderStatus().equals("P")){
                holder.acceptOrderBtn.setOnClickListener(view -> {
                    Provider.getUser().getBrewedOrder().get(position).setOrderStatus("A");
                    updateOrderStatus(brewedOrder.get(position).getOrderStatus(), brewedOrder.get(position).getOrderId());
                    notifyDataSetChanged();
                });

                holder.declineOrderBtn.setOnClickListener(view -> {
                    Provider.getUser().getBrewedOrder().get(position).setOrderStatus("D");
                    updateOrderStatus(brewedOrder.get(position).getOrderStatus(), brewedOrder.get(position).getOrderId());
                    notifyDataSetChanged();
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return brewedOrder.size();
    }

    private void updateOrderStatus(String status, String orderId) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            String[] field = new String[2];
            field[0] = "orderStatus";
            field[1] = "orderId";

            //Creating array for data
            String[] data = new String[2];
            data[0] = status;
            data[1] = orderId;

            PutData putData = new PutData("http://" + Provider.getIpAddress() + "/CoffeeCommunityPHP/updateorderstatus.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    if(result.equals("Update success")){
                        Log.i(TAG, "Update Successful");
                    }
                    for (int i = 0; i < Provider.getUser().getBrewedOrder().size(); i++) {
                        if (Provider.getUser().getBrewedOrder().get(i).getOrderId().equals(orderId)){
                            Provider.getUser().getBrewedOrder().get(i).setOrderStatus(status);
                        }
                    }
                }
            }
        });
    }

}