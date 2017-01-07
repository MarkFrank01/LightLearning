package com.wjc.learn_to_work.chapter_1.recycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.wjc.learn_to_work.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJC on 2018/6/10.
 */
public class RecycleviewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mList;
    private StaggeredHomeAdapter mStaggeredHomeAdapter;
    private HomeAdapter mHomeAdaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        initData();
        initView();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add(i +"");
        }
    }

    private void initView(){
        mRecyclerView =findViewById(R.id.id_recyclerview);

        //设置GridView
//        setGridView();

        //设置ListView
//        setListView();

        //设置WaterFallView
        setWaterfallView();
    }

    private void setListView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecycleviewActivity.this,DividerItemDecoration.VERTICAL_LIST));
        mHomeAdaper = new HomeAdapter(this,mList);
        setLister();
        mRecyclerView.setAdapter(mHomeAdaper);
    }

    private void setGridView(){
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHomeAdaper=new HomeAdapter(this, mList);
        setLister();
        mRecyclerView.setAdapter(mHomeAdaper);
    }


    public void setWaterfallView(){
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL))
        ;mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mStaggeredHomeAdapter = new StaggeredHomeAdapter(this,mList);
        mRecyclerView.setAdapter(mStaggeredHomeAdapter);
    }

    private void  setLister(){
        mHomeAdaper.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleviewActivity.this, "点击第"+(position + 1) + "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                new AlertDialog.Builder(RecycleviewActivity.this)
                        .setTitle("确定嘛？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定",(dialogInterface, i) -> {
                             mHomeAdaper.removeData(position);
                        }).show();

            }
        });

    }
}
