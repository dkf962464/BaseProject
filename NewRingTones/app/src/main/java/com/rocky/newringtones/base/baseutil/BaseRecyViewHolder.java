package com.rocky.newringtones.base.baseutil;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BaseRecyViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> views;

    public BaseRecyViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.mContext = context;
        //指定一个初始为8
        views = new SparseArray<>(8);
    }

    /**
     * 取得一个RecyclerHolder对象
     *
     * @param context  上下文
     * @param itemView 子项
     * @return 返回一个RecyclerHolder对象
     */
    public static BaseRecyViewHolder getRecyclerHolder(Context context, View itemView) {
        return new BaseRecyViewHolder(context, itemView);
    }

    public SparseArray<View> getViews() {
        return this.views;
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     *
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置字符串
     */
    public BaseRecyViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyViewHolder setText(int viewId, String text, int flag) {
        TextView tv = getView(viewId);
        tv.getPaint().setFlags(flag);
        tv.setText(text);
        return this;
    }


    /**
     * Progressbar
     */
    public BaseRecyViewHolder setProgress(int viewId, int progress) {
        ProgressBar pb = getView(viewId);
        pb.setProgress(progress);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyViewHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyViewHolder setImageByUrl(int viewId, String url) {
        final ImageView view = (ImageView) getView(viewId);
        Glide.with(mContext).load(url).into(view);
        return this;
    }
}
