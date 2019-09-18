package com.example.ratio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewpagerAdapter extends RecyclerView.Adapter<ViewpagerAdapter.MyViewHolder> {


    private Context context;
    private int itemCount;

    public ViewpagerAdapter(Context context, int count) {
        this.context = context;
        this.itemCount = count;
    }


    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return position % getItemCount();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen1, parent, false);
                break;
            }
            case 1: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen4, parent, false);
                break;
            }
            case 2: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen0, parent, false);
                break;
            }

            case 3: {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.screen2, parent, false);
                break;
            }


        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }


}