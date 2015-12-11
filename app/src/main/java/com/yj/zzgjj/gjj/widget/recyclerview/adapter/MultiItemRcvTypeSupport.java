package com.yj.zzgjj.gjj.widget.recyclerview.adapter;

public interface MultiItemRcvTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}