package com.hnd.freemovie.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.hnd.freemovie.R;

import java.util.Random;

public class ImageHelper {
    private static int cardImageHeight;
    private static int cardImageWidth;
    private Context context;
    public ImageHelper(Context context) {
        this.context = context;
        cardImageHeight = (int)context.getResources().getDimension(R.dimen.movie_card_image_height);
        cardImageWidth = (int)context.getResources().getDimension(R.dimen.movie_card_image_width);
    }
    public static int getCardImageHeight() {
        return cardImageHeight;
    }
    public static int getCardImageWidth() {
        return cardImageWidth;
    }

    public static String getURL(String id){

         return null;
    }
    public static String getDrawableName(String  id){
        int i = new Random().nextInt(9)+1;
        return "movie_"+i+"_img";
    }
}
