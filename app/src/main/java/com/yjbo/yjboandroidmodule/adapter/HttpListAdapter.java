package com.yjbo.yjboandroidmodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.util.L;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @description: <描述当前版本功能>
 * <p>
 * 主页面的列表信息
 * </p>
 * @author: yjbo
 * @date: 2016-07-07 18:30
 */
public class HttpListAdapter extends RecyclerView.Adapter<HttpListAdapter.ViewHolder> {

    private List<String> list;
    private Context mContext;

    public void bindData(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_httplist_layout, parent, false);
        ViewHolder holderView = new ViewHolder(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTxt.setText(list.get(position));
        holder.itemTxt.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_txt)
        TextView itemTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.d("NormalTextViewHolder", "onClick--> position = " + itemTxt.getTag() + "---" + getPosition());
                    mdialogChoose.pos(Integer.valueOf(itemTxt.getTag() + ""));
//                   new ItemOnclick().setonItem(Integer.valueOf(itemTxt.getTag() + ""));
                }
            });
        }
    }

    //添加数据的方法
    public void addData(int position, String str) {
        list.add(position, str);
        notifyItemInserted(position);
        notifyDataSetChanged();
    }

    //删除数据的方法
    public void removeData(int position) {
        if ("你好呀，字符进来了".equals(list.get(position))) {
            list.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }
    }
    //更新数据
    public void updateData(List<String> mlist) {
            list = mlist;
            notifyDataSetChanged();
    }

    private static DialogChoose mdialogChoose;

    public static void SetonDialogChoose(DialogChoose dialogChoose) {
        mdialogChoose = dialogChoose;
    }

    public interface DialogChoose {
        void pos(int position);
    }
}
