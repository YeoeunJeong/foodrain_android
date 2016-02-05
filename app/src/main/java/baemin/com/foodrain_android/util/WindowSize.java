package baemin.com.foodrain_android.util;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class WindowSize {
    private Point size;
    public WindowSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        size = new Point();
        display.getSize(size);
    }

    public int getWindowWidth() {
        return size.x;
    }


    public int getWindowHeight() {
        return size.y;
    }
}
