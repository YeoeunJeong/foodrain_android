package baemin.com.foodrain_android.network;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.util.Constants;
import baemin.com.foodrain_android.util.WindowSize;

public class ImageGenerator {

    private static ImageGenerator instance;

    public ImageGenerator() {
    }

    public static ImageGenerator getInstance() {
        if (instance == null) {
            instance = new ImageGenerator();
        }
        return instance;
    }

    public void createImageService(String url, ImageView view) {
        Picasso.with(view.getContext())
                .load(Constants.IMAGE_URL + url)
                .placeholder(R.drawable.ready)
                .error(R.drawable.ready_image)
                .fit().centerCrop()
                .into(view);
    }

    public void createMenuImageService(int width, String url, ImageView view) {
        // 4096
        Picasso.with(view.getContext())
                .load(Constants.IMAGE_URL + url)
                .placeholder(R.drawable.ready_transperant)
                .error(R.drawable.ready_menu)
                .resize(width, 0)
                .into(view);
    }

    public void createImageService(String url, ImageView view, int width, int height) {
        Picasso.with(view.getContext())
                .load(Constants.IMAGE_URL + url)
                .placeholder(R.drawable.ready)
                .error(R.drawable.ready)
                .resize(width, height)
                .into(view);
    }
}
