package com.example.farmer_portalnew.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.bidclass;
import com.example.farmer_portalnew.Classes.bidding;
import com.example.farmer_portalnew.Classes.farmerclass;
import com.example.farmer_portalnew.R;
import com.example.farmer_portalnew.biddingview.BuyerDetails;
import com.example.farmer_portalnew.biddingview.FarmerDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class bidmycrop extends RecyclerView.Adapter<bidmycrop.bidmycropholder> {
    public bidmycrop(List<farmerclass> bidmycroplist) {
        this.bidmycroplist = bidmycroplist;
    }

    private List<farmerclass> bidmycroplist=new ArrayList<>();

    @NonNull
    @Override
    public bidmycropholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bidstomycropitem,parent,false);
        return new bidmycrop.bidmycropholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull bidmycropholder holder, int position) {
        final farmerclass currentnote=bidmycroplist.get(position);
        holder.title.setText(currentnote.getCropName());
          holder.quantity.setText(String.valueOf(currentnote.getOfferQuantity()));
    //    holder.category.setText(String.valueOf(currentnote.getCategory()));
            holder.Bid.setText(String.valueOf(currentnote.getOfferPrice()));
        holder.cropprice.setText(String.valueOf(currentnote.getActualPrice()));
    //    holder.phone.setText(String.valueOf(currentnote.getFarmerphone()));
     //   holder.name.setText(String.valueOf(currentnote.getFarmername()));
  //      holder.farmerorbuyer.setText("Buyer Details");
        holder.farmerdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("RestrictedApi") Context context = getApplicationContext();
                Intent i=new Intent(context, BuyerDetails.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("farmerclass", (Serializable) currentnote);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bidmycroplist.size();
    }

    class bidmycropholder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView category;
        private TextView quantity,Bid,cropprice,name,phone,farmerorbuyer;
        private Button farmerdetails;

        public bidmycropholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.producttitle1);
         //   category=itemView.findViewById(R.id.productcategory1);
            quantity=itemView.findViewById(R.id.productquantity1);
          // farmerorbuyer=itemView.findViewById(R.id.farmerorbuyer);
            Bid=itemView.findViewById(R.id.Bidprice);
            //name=itemView.findViewById(R.id.mybidname);
           // phone=itemView.findViewById(R.id.mybidphone);
            cropprice=itemView.findViewById(R.id.PriceOfCrop1);
            farmerdetails=itemView.findViewById(R.id.buyerdetails);
        }
    }
}


