package com.example.farmer_portalnew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.DisplayTransportDetail;
import com.example.farmer_portalnew.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.TransportViewHolder> {

    private List<TransportDetail> myproductList = new ArrayList<>();

    public TransportAdapter(List<TransportDetail> myproductList) {
        this.myproductList = myproductList;
    }



    @NonNull
    @Override
    public TransportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.transportlist,parent,false);


        return new TransportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransportViewHolder holder, int position) {
        final TransportDetail currentnote=myproductList.get(position);
        //String tranpostdetail=data[position];

        String s="\nDrop Location :" +currentnote.getDropLoc().toString();
        s+="\n\nPickupLoc :"+currentnote.getPickLoc()+'\n'+"\nAvailable balance:"+currentnote.getPrice();
        Log.d("value",s);


        holder.source.setText("From: "+currentnote.getPickLoc());
        holder.price.setText("Price:   Rs. "+currentnote.getPrice());
        holder.destination.setText("To: "+currentnote.getDropLoc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, DisplayTransportDetail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("class", (Serializable) currentnote);


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myproductList.size();
    }

    public  class  TransportViewHolder extends RecyclerView.ViewHolder {
        TextView source,destination,price;
        public TransportViewHolder(@NonNull View itemView) {
            super(itemView);
            source=itemView.findViewById(R.id.source);
            destination=itemView.findViewById(R.id.destinations);
            price=itemView.findViewById(R.id.pricevalue);

        }
    }
}
