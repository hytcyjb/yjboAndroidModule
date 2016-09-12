package com.yjbo.yjboandroidmodule.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.adapter.BaseRecyclerAdapter;
import com.yjbo.yjboandroidmodule.adapter.MyAdapter;
import com.yjbo.yjboandroidmodule.util.GridItemDecoration;

import java.util.ArrayList;

public class AddHeaderActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_add);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
//        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mLayoutManager = new GridLayoutManager(this, 2);
//        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new GridItemDecoration(this, true));

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addDatas(generateData());
        setHeader(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(AddHeaderActivity.this, position + "," + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setHeader(RecyclerView view) {
        View header = LayoutInflater.from(this).inflate(R.layout.header, view, false);
        mAdapter.setHeaderView(header);
    }

    private ArrayList<String> generateData() {
        ArrayList<String> data = new ArrayList<String>() {
            {
                for(int i=0;i<21;i++) add("数据" + i);
            }
        };
        return data;
    }
}
