package baemin.com.foodrain_android.network;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import baemin.com.foodrain_android.R;
import baemin.com.foodrain_android.util.Constants;

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
                .error(R.drawable.ready)
                .resize(0, view.getHeight())
                .into(view);
    }
}
