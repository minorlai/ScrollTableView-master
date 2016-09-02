package com.loopeer.android.librarys.horizontalverticalscrollview.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by laiyingtang on 2016/8/27.
 */
public class ToastUtil {
    public static Toast toast;

    public static void ToaString(Context context,String content) {
        if (content == null) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
