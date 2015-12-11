package com.yj.zzgjj.gjj.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * 资源工具类
 * Created by Jerry on 2015/12/9.
 */
public class ResouceUtils {

    private static final String TAG = ResouceUtils.class.getSimpleName();

    private ResouceUtils(){

    }

    @ColorInt
    public static int getRescourceColor(@NonNull Context context,
                                        @ColorRes int colorId,
                                        @Nullable Resources.Theme theme){
        int color;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            color = context.getResources().getColor(colorId);
        } else {
            color = context.getResources().getColor(colorId, theme);
        }
        return color;
    }

    @ColorInt
    public static int getRescourceColor(@NonNull Context context,
                                        @ColorRes int colorId){
        return getRescourceColor(context, colorId, context.getTheme());
    }

    /**
     * 设置视图的上下左右的图片
     * @param context           ...
     * @param view              ...
     * @param leftDrawableId    左边图片
     * @param topDrawableId     上边图片
     * @param rightDrawableId   右边图片
     * @param bottomDrawableId  下边图片
     */
    public static void setResourceCompoundDrawables(
            @NonNull Context context,
            @NonNull TextView view,
            @DrawableRes int leftDrawableId,
            @DrawableRes int topDrawableId,
            @DrawableRes int rightDrawableId,
            @DrawableRes int bottomDrawableId){
        Drawable leftDrawable, topDrawable, rightDrawable, bottomDrawable;
        leftDrawable = getDrawable(context, leftDrawableId);
        topDrawable = getDrawable(context, topDrawableId);
        rightDrawable = getDrawable(context, rightDrawableId);
        bottomDrawable = getDrawable(context, bottomDrawableId);
        view.setCompoundDrawables(leftDrawable, topDrawable, rightDrawable, bottomDrawable);

    }

    @Nullable
    private static Drawable getDrawable(Context context, int drawableId){
        Drawable drawable;
        try{
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                drawable = context.getResources().getDrawable(drawableId);
            } else {
                drawable = context.getResources().getDrawable(drawableId, null);
            }
            if (null != drawable) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
        }catch (Resources.NotFoundException e){
            drawable = null;
        }
        return drawable;
    }
}
