package com.yj.zzgjj.gjj.widget.quickadapter.recycler;

public interface MultiItemRcvTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}