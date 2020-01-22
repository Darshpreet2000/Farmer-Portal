package com.example.farmer_portal.PaymentMethod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braintreepayments.cardform.view.CardForm;
import com.example.farmer_portal.BuyNow;
import com.example.farmer_portal.R;

public class PaymentsMethod extends AppCompatActivity {
   Button paytmpay,cash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_method);
        paytmpay=findViewById(R.id.paytmpay);
        cash=findViewById(R.id.cash);
        paytmpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PaymentsMethod.this, paytm.class);
                startActivity(intent);
            }
        });
    }
}
