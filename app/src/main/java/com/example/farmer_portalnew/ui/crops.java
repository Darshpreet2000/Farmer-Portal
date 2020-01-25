package com.example.farmer_portalnew.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.farmer_portalnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class crops extends Fragment {

  ListView cropslist;
  List<String> CropCategory ;
  public crops() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CropCategory=new ArrayList<>();
        cropslist=view.findViewById(R.id.croplist);
        CropCategory.add("Vegetable");
        CropCategory.add("Pulses");
        CropCategory.add("Fruits");
        CropCategory.add("Spices");
        CropCategory.add("Beverages");
        CropCategory.add("Dry Fruits");
        CropCategory.add("Beverages");
        CropCategory.add("Others");
        cropslist.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.croplistitem,R.id.categorytitle,CropCategory));

       cropslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(getActivity(),displaycategorycrop.class);
               switch(position){
                   case 0:   intent.putExtra("category","Vegetable");startActivity(intent);
                                break;

                   case 1:   intent.putExtra("category","Pulses");startActivity(intent);
                       break;

                   case 2:   intent.putExtra("category","Fruits");startActivity(intent);
                       break;

                   case 3:   intent.putExtra("category","Spices");startActivity(intent);
                       break;

                   case 4:   intent.putExtra("category","Dry Fruits");startActivity(intent);
                       break;
                   case 5:   intent.putExtra("category","Beverages");startActivity(intent);
                             break;

                   case 6:   intent.putExtra("category","Others");startActivity(intent);
                       break;

               }
           }
       });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crops, container, false);
    }

}
