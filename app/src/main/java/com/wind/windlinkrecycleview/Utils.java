package com.wind.windlinkrecycleview;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhangcong on 2017/7/25.
 */

public class Utils {
            /*
           类似于Toast.
            */
    public static void showSnackBar(View view,String content)
    {
        Snackbar snackbar = Snackbar.make(view, "当前点击的是: " + content , Snackbar.LENGTH_SHORT);
        View mView = snackbar.getView();
        mView.setBackgroundColor(Color.RED);
        TextView text = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        text.setTextColor(Color.WHITE);
        text.setTextSize(25);
        snackbar.show();
    }


}
