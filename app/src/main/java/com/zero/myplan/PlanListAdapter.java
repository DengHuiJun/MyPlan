package com.zero.myplan;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zero on 15-12-23.
 */
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.PlanViewHolder> {


    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView typeTv;
        TextView contentTv;
        TextView dateTv;
        TextView daysTv;
        ImageView nodeIv;


        public PlanViewHolder(View itemView) {
            super(itemView);


        }
    }
}
