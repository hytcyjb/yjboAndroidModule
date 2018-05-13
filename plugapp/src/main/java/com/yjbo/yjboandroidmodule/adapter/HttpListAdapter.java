package com.yjbo.yjboandroidmodule.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yjbo.yjboandroidmodule.R;
import com.yjbo.yjboandroidmodule.entity.HttpUrlClass;
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

    private List<HttpUrlClass> list;
    private Context mContext;

    public void bindData(List<HttpUrlClass> list, Context mContext) {
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
        holder.itemTxt.setText(list.get(position).getHttp_url());
        holder.timeTxt.setText(list.get(position).getTime());
        holder.itemLayout.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_layout)
        LinearLayout itemLayout;
        @Bind(R.id.item_txt)
        TextView itemTxt;
        @Bind(R.id.time_txt)
        TextView timeTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.d("NormalTextViewHolder", "onClick--> position = " + itemLayout.getTag() + "---" + getPosition());
                    mdialogChoose.pos(Integer.valueOf(itemLayout.getTag() + ""));
//                   new ItemOnclick().setonItem(Integer.valueOf(itemTxt.getTag() + ""));
                }
            });
        }
    }

    //更新数据
    public void updateData(List<HttpUrlClass> mlist) {
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
