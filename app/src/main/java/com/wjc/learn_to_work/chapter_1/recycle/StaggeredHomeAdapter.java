package com.wjc.learn_to_work.chapter_1.recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.wjc.learn_to_work.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJC on 2018/6/10.
 */
public class StaggeredHomeAdapter extends RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    private List<Integer> mHeight;

    public interface OnItemClickLitener{
        void onItemClick(View view,int position);

        void onItemLongClick(View view,int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public StaggeredHomeAdapter(Context context,List<String> datas){
        mInflater = LayoutInflater.from(context);
        this.mDatas  = datas;

        mHeight = new ArrayList<Integer>();
        for (int i = 0;i<this.mDatas.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_recycle, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeight.get(position);

        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));

        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener !=null){
            holder.itemView.setOnClickListener(v->{
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.itemView,pos);
            });


            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView,pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setmDatas(int position){
        mDatas.add(position,"add");
        mHeight.add((int) (100+Math.random()*3000));
        notifyDataSetChanged();
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    class MyViewHolder extends ViewHolder {

        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_item);
        }
    }
}
