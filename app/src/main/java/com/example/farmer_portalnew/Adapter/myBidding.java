package com.example.farmer_portalnew.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.bidclass;
import com.example.farmer_portalnew.R;

import java.util.ArrayList;
import java.util.List;

public class myBidding extends RecyclerView.Adapter<myBidding.Biddingholder> {

    public myBidding(List<bidclass> mybiddinglist) {
        this.mybiddinglist = mybiddinglist;
    }

    private List<bidclass> mybiddinglist=new ArrayList<>();
    @NonNull
    @Override
    public Biddingholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mybiddingitem,parent,false);
        return new myBidding.Biddingholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Biddingholder holder, int position) {
        final bidclass currentnote=mybiddinglist.get(position);
        holder.title.setText(currentnote.getCropName());
        holder.quantity.setText(String.valueOf(currentnote.getOfferQuantity()));
        holder.Bid.setText(String.valueOf(currentnote.getOfferPrice()));
        holder.cropprice.setText(String.valueOf(currentnote.getActualPrice()));
     //   holder.farmerorbuyer.setText("Farmer Details");
    }

    @Override
    public int getItemCount() {
        return mybiddinglist.size();
    }

    class Biddingholder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView category;
        private TextView quantity,Bid,cropprice,name,phone,farmerorbuyer;

        public Biddingholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.producttitle1);
//            category=itemView.findViewById(R.id.productcategory1);
            quantity=itemView.findViewById(R.id.productquantity1);
            //farmerorbuyer=itemView.findViewById(R.id.farmerorbuyer);
            Bid=itemView.findViewById(R.id.Bidprice);
          // name=itemView.findViewById(R.id.mybidname);
            //phone=itemView.findViewById(R.id.mybidphone);
            cropprice=itemView.findViewById(R.id.PriceOfCrop1);
        }
    }

}
