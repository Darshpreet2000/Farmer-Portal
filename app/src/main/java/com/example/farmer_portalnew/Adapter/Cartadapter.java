package com.example.farmer_portalnew.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.Cart;
import com.example.farmer_portalnew.R;

import java.util.ArrayList;
import java.util.List;

public class Cartadapter  extends RecyclerView.Adapter<Cartadapter.mycartholder> {
    private List<Cart> cartlist=new ArrayList<>();
    private Cartadapter.OnItemClicked onClick;

    public Cartadapter(List<Cart> cartlist, Cartadapter.OnItemClicked onClick) {
        this.cartlist = cartlist;
        this.onClick = onClick;
    }

    public interface OnItemClicked {
        void onbuttonclicked(int position);
        //   void onbidclicked(int position);
    }


    @NonNull
    @Override
    public mycartholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cartitem,parent,false);
        return new Cartadapter.mycartholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull mycartholder holder, int position) {
        final Cart currentnote=cartlist.get(position);
        holder.title.setText(currentnote.getCropName());
        holder.category.setText(currentnote.getCategory());
        holder.quantity.setText(String.valueOf(currentnote.getQuantity()));
        holder.cropprice.setText(String.valueOf(currentnote.getPrice())+" Rupees");
        holder.buyingquantity.setText(String.valueOf(currentnote.getBuyQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    class mycartholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title,buyingquantity;
        private TextView quantity,category,cropprice,name,phone,farmerorbuyer;
        private Button Removecart;

        public mycartholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.producttitle);
            //   category=itemView.findViewById(R.id.productcategory1);
            quantity=itemView.findViewById(R.id.productquantity);
            category=itemView.findViewById(R.id.productcategory);
            // phone=itemView.findViewById(R.id.mybidphone);
            cropprice=itemView.findViewById(R.id.PriceOfCrop1);
            buyingquantity=itemView.findViewById(R.id.buyingquantity);
            Removecart=itemView.findViewById(R.id.Removecart);
            Removecart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClick.onbuttonclicked(getAdapterPosition());
        }
    }
}