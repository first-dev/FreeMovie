package com.hnd.freemovie.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.hnd.freemovie.R;

public class RecyclerViewHelper {

    public static final int KEEP_MARGIN = -1;
    private Context context;
    public RecyclerViewHelper(Context context) {
        this.context = context;
    }

    public int getSideMargin(){
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final float widthPixels = displayMetrics.widthPixels;
        final float cardWidth = context.getResources().getDimension(R.dimen.movie_card_width);
        final float listMargin = context.getResources().getDimension(R.dimen.movie_list_margin);
        final int cardsOnScreen = (int)((widthPixels-listMargin*2) / cardWidth);
        final int sideMargin = (int)((widthPixels-(cardsOnScreen*cardWidth)) / 2);
        return sideMargin;
    }
    public int getRVSidePadding(){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final float widthPixels = displayMetrics.widthPixels;
        final float cardWidth = context.getResources().getDimension(R.dimen.movie_card_width);
        final float cardPadding = context.getResources().getDimension(R.dimen.movie_card_margin_half);
        final float listMargin = context.getResources().getDimension(R.dimen.movie_list_margin);
        final int imagesOnScreen = (int)((widthPixels-listMargin*2) / cardWidth);
        final int RVSidePadding = (int)(((widthPixels-(imagesOnScreen*cardWidth)) / 2)-cardPadding);
        return RVSidePadding;
    }
    public static void setMargins (View view, int l, int t, int r, int b) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if(l==KEEP_MARGIN) l=p.leftMargin;
            if(t==KEEP_MARGIN) t=p.topMargin;
            if(r==KEEP_MARGIN) r=p.rightMargin;
            if(b==KEEP_MARGIN) b=p.bottomMargin;
            p.setMargins(l, t, r, b);
            view.requestLayout();
        }
    }
    public int getVisibleCards(){
        final Resources resources = context.getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        final float widthPixels = displayMetrics.widthPixels;
        final float cardWidth = resources.getDimension(R.dimen.movie_card_width);
        final float listMargin = resources.getDimension(R.dimen.movie_list_margin);
        final int visibleCards = (int)((widthPixels-listMargin*2) / cardWidth);
        return visibleCards;
    }
    public int getVisibleLists(){
        final Resources resources = context.getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        final float heightPixels = displayMetrics.heightPixels;
        final float cardHeight = resources.getDimension(R.dimen.movie_card_height);
        final float recyclerViewPadding = resources.getDimension(R.dimen.movie_card_margin_half)+resources.getDimension(R.dimen.movie_card_margin_half);
        final float recyclerViewHeight = cardHeight+recyclerViewPadding;
        final float headerTextSize = resources.getDimension(R.dimen.movie_list_header_text_size);
        final float headerPadding = resources.getDimension(R.dimen.movie_list_header_padding) *2;
        final float headedHeight = headerPadding+headerTextSize;
        final float listHeight = recyclerViewHeight+headedHeight;
        final float listMargin = resources.getDimension(R.dimen.movie_list_margin_half) *2;
        final int visibleLists = (int)((heightPixels) / (listHeight+listMargin));
        return visibleLists;
    }

}
