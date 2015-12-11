package com.yj.zzgjj.gjj.model;

/**
 * 首页功能列表项实体
 * Created by Administrator on 2015/12/10.
 */
public class HomeFuncItem {
    private int iconId;
    private String name;
    private int backgroundColorId;
    private Class<?> cls;

    public int getBackgroundColorId() {
        return backgroundColorId;
    }

    public void setBackgroundColorId(int backgroundColorId) {
        this.backgroundColorId = backgroundColorId;
    }

    public HomeFuncItem(int iconId, String name, int backgroundColorId, Class<?> cls) {
        this.iconId = iconId;
        this.name = name;
        this.backgroundColorId = backgroundColorId;
        this.cls = cls;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
