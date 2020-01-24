package com.example.farmer_portalnew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.BuyNow;
import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.TransportDetail;
import com.example.farmer_portalnew.DisplayTransportDetail;
import com.example.farmer_portalnew.MyProductActivity;
import com.example.farmer_portalnew.R;
import com.example.farmer_portalnew.displayproduct;

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

        String s=currentnote.getDropLoc().toString()+'\n';
        s+='\n'+currentnote.getPickLoc()+'\n'+currentnote.getPrice();


        holder.textView.setText(s);
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
        TextView textView;
        public TransportViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.TransportDetail);

        }
    }
}
