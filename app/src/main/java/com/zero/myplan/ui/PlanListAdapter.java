package com.zero.myplan.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zero.myplan.R;
import com.zero.myplan.core.dao.PlanDao;
import com.zero.myplan.core.model.PlanM;
import com.zero.myplan.utils.DateUtils;

import java.util.List;

/**
 * Created by zero on 15-12-23.
 */
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.PlanViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<PlanM> mList;

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public PlanListAdapter(Context context, List<PlanM> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickLitener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlanViewHolder(mLayoutInflater.inflate(R.layout.plan_list_rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, final int position) {

        PlanM item = mList.get(position);
        holder.typeTv.setText(item.getType());
        holder.dateTv.setText(DateUtils.getDateByMillis(item.getDoneTime()));
        holder.contentTv.setText(item.getContent());
        holder.daysTv.setText(DateUtils.getDaysByTwoDate(System.currentTimeMillis(), item.getDoneTime())+ "");
        holder.viewLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<PlanM> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder {
        TextView typeTv;
        TextView contentTv;
        TextView dateTv;
        TextView daysTv;
        ImageView nodeIv;
        View viewLy;


        public PlanViewHolder(View itemView) {
            super(itemView);

            viewLy = itemView.findViewById(R.id.plan_item_ly);
            typeTv = (TextView) itemView.findViewById(R.id.plan_item_type_tv);
            contentTv = (TextView) itemView.findViewById(R.id.plan_item_content_tv);
            dateTv = (TextView) itemView.findViewById(R.id.plan_item_date_tv);
            daysTv = (TextView) itemView.findViewById(R.id.plan_item_days_tv);
            nodeIv = (ImageView) itemView.findViewById(R.id.plan_item_node_iv);

        }
    }
}
