package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AllDetilsAdapter extends RecyclerView.Adapter<AllDetilsAdapter.MyViewHolder>{
    private List<Mlm> iconList;
    private Context cntx;
    RecyclerView view;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);


        }
    }
    public AllDetilsAdapter(Context context, RecyclerView view, List<Mlm> catList) {
        this.iconList = catList;
        this.cntx = context;
        this.view=view;
    }
    @Override
    public AllDetilsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_employee_all_details, parent, false);

        return new AllDetilsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AllDetilsAdapter.MyViewHolder holder, final int position) {
        Mlm ride = iconList.get(position);
        holder.name.setText(ride.getName());
        holder.email.setText(ride.getEmail());




    }
    @Override
    public int getItemCount() {
        return iconList.size();
    }

}
