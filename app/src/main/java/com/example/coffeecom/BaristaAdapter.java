package com.example.coffeecom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BaristaAdapter extends RecyclerView.Adapter<BaristaAdapter.ViewHolder>{

    ArrayList<BaristaModel> baristas;

    public BaristaAdapter(ArrayList<BaristaModel> baristas) {
        this.baristas = baristas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView baristaPic;
        TextView baristaCardName, baristaLocation, baristaDistance;
        ConstraintLayout baristaSmallCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            baristaPic = itemView.findViewById(R.id.baristaPic);
            baristaCardName = itemView.findViewById(R.id.baristaCardName);
            baristaLocation = itemView.findViewById(R.id.baristaLocation);
            baristaDistance = itemView.findViewById(R.id.baristaDistance);
            baristaSmallCard = itemView.findViewById(R.id.baristaSmallCard);
        }
    }

    @NonNull
    @Override
    public BaristaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_barista_small, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BaristaAdapter.ViewHolder holder, int position) {

        //barista pic
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(baristas.get(position).getBaristaPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.baristaPic);

        holder.baristaCardName.setText(baristas.get(position).getBaristaName());
        holder.baristaLocation.setText(baristas.get(position).getBaristaTaman());
        holder.baristaDistance.setText("Not do gok");

        holder.baristaSmallCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input code to open details coffee page
            }
        });
    }

    @Override
    public int getItemCount() {
        return Provider.baristas.size();
    }


}
