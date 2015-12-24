package com.zero.myplan.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.myplan.R;
import com.zero.myplan.core.dao.model.PlanM;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by zero on 15-12-23.
 */
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.PlanViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<PlanM> mList;

    public PlanListAdapter(Context context, List<PlanM> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanViewHolder(mLayoutInflater.inflate(R.layout.plan_list_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {
        String dateNow = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).format(System.currentTimeMillis());
        PlanM item = mList.get(position);
        holder.typeTv.setText(item.getType());
        holder.dateTv.setText(item.getDoneTime());
        holder.contentTv.setText(item.getContent());
//        holder.daysTv.setText(Utils.getDaysByTwoDate(dateNow, item.getDoneTime()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<PlanM> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView typeTv;
        TextView contentTv;
        TextView dateTv;
        TextView daysTv;
        ImageView nodeIv;


        public PlanViewHolder(View itemView) {
            super(itemView);

            typeTv = (TextView) itemView.findViewById(R.id.plan_item_type_tv);
            contentTv = (TextView) itemView.findViewById(R.id.plan_item_content_tv);
            dateTv = (TextView) itemView.findViewById(R.id.plan_item_date_tv);
            daysTv = (TextView) itemView.findViewById(R.id.plan_item_days_tv);
            nodeIv = (ImageView) itemView.findViewById(R.id.plan_item_node_iv);

        }
    }
}
