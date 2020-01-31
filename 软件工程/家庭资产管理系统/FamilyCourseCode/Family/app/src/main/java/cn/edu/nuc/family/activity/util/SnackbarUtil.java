package cn.edu.nuc.family.activity.util;

import android.graphics.Color;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class SnackbarUtil {
    public static void makeSnackBar(View mLayout,String text){
        Snackbar.make(mLayout, text, Snackbar.LENGTH_SHORT)
                .setActionTextColor(Color.WHITE)
                .setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击右侧的按钮之后的操作
                    }
                }).show();
    }
}
