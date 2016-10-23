package com.yjbo.mvp.PictureActivity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
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
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<String> list;
    private Context mContext;
    private int mkind = 0;
    private String mPosId = "";

    public void bindData(List<String> list, Context mContext, int kind,String PosId) {
        this.list = list;
        this.mkind = kind;
        this.mPosId = PosId;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.item_piclist_layout, parent, false);
        ViewHolder holderView = new ViewHolder(view);
        return holderView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTxt.setText(mPosId+"="+list.get(position));
        holder.itemLayout.setTag(position);
        if (position < list.size()) {
            if (mkind == 0) {
                L.i("--图片的路径--" + position + "==" + list.get(position));
                Glide
                        .with(mContext)
                        .load(list.get(position))
                        .into(holder.iconImage);
            } else if (mkind == 1) {
                Picasso.with(mContext)
                        .load(list.get(position))
                        .into(holder.iconImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_txt)
        TextView itemTxt;
        @Bind(R.id.icon_image)
        ImageView iconImage;
        @Bind(R.id.item_layout)
        LinearLayout itemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mdialogChoose.pos(Integer.valueOf(itemLayout.getTag() + ""));
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
