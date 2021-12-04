package com.investoPal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class TextAndImageView extends FrameLayout {
    public ImageView image;
    public TextView title;

    public TextAndImageView(String text, String imgName, @NonNull Context context) {
        //imageName is the name of the image within the drawable folder
        //sets up a view to display an image of an article with its title flowing on the bottom
        super(context);
        Utils utils = new Utils(context);

        //The image of the view
        this.image = new ImageView(context);
        this.image.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                utils.DpToPixel(120)));
        this.image.setScaleType(ImageView.ScaleType.FIT_XY);
        this.image.setImageResource(getResources().getIdentifier(imgName, "drawable", context.getPackageName()));

        //the title of the view
        this.title = new TextView(context);
        this.title.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        this.title.setText(text);

        addView(image);
        addView(title);

        setId(View.generateViewId());
    }

}
