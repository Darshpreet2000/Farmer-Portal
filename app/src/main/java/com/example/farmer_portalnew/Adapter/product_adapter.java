package com.example.farmer_portalnew.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmer_portalnew.Classes.Addproduct;
import com.example.farmer_portalnew.Classes.Cart;
import com.example.farmer_portalnew.displayproduct;
import com.example.farmer_portalnew.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class product_adapter extends RecyclerView.Adapter<product_adapter.Productholder> {
    public product_adapter(List<Cart> product) {
        this.product = product;
    }

    private List<Cart> product=new ArrayList<>();
    private OnItemClicked onClick;

    public product_adapter(List<Cart> product, OnItemClicked onClick) {
        this.product = product;
        this.onClick = onClick;
    }

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
        void onbuttonclicked(int position);
     //   void onbidclicked(int position);
    }

    @NonNull
    @Override
    public Productholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_items,parent,false);
        return new Productholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Productholder holder, int position) {
        final Cart currentnote=product.get(position);
        holder.title.setText(currentnote.getCropName());
        holder.category.setText(currentnote.getCategory());
        holder.quantity.setText(String.valueOf(currentnote.getQuantity()));
        holder.CropPrice.setText(String.valueOf(currentnote.getPrice())+" Rupees");
         holder.minquantity.setText(String.valueOf(currentnote.getMinQuantity()));
      holder.bidprice.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              @SuppressLint("RestrictedApi") Context context = getApplicationContext();
              Intent intent = new Intent(context, displayproduct.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("class", (Serializable) currentnote);


              context.startActivity(intent);
          }
      });


    }
    @Override
    public int getItemCount() {
        return product.size();
    }

    class Productholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView category;
        private TextView quantity;
        private  TextView minquantity;
        private  TextView CropPrice;
        private EditText Enteramounttobuy;
      private Button Buttoncart,bidprice;
        public Productholder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.producttitle);
            category=itemView.findViewById(R.id.productcategory);
            quantity=itemView.findViewById(R.id.productquantity);
            CropPrice=itemView.findViewById(R.id.PriceOfCrop1);
            minquantity=itemView.findViewById(R.id.minproductquantity);
           Buttoncart=itemView.findViewById(R.id.addtocart);
           Buttoncart.setOnClickListener(this);
           bidprice=itemView.findViewById(R.id.buttonbidprice);
           bidprice.setOnClickListener(this);
          Enteramounttobuy=itemView.findViewById(R.id.amountobuy);
        }

        @Override
        public void onClick(View v) {
            onClick.onbuttonclicked(getAdapterPosition());
           // onClick.onbidclicked(getAdapterPosition());
        }
    }

}
