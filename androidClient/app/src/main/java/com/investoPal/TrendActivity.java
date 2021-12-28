package com.investoPal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrendActivity extends AppCompatActivity {

    public TrendActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(getMainView());
    }


    //--------------------------------------------------------------------------------------------
    //helper functions


    private ScrollView getMainView(){
        //sets up the overall view to display based on the data array we get from the server

        /*
        Data Array Format:

        */
        Utils utils = new Utils(this);

        ScrollView mainView = new ScrollView(this);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        //This linear layout inside the Scroll view ensures that multiple other views can be placed within the scroll view
        RelativeLayout mainViewLayout = new RelativeLayout(this);
        mainViewLayout.setPadding(utils.DpToPixel(16), utils.DpToPixel(10), utils.DpToPixel(16), utils.DpToPixel(10));

        ArrayList testArray = new ArrayList<String[]>();
        String[] View1Array = {"image", "Hello", "test_image"};
        testArray.add(View1Array);
        String[] View2Array = {"text", "a_bit---longer"};
        testArray.add(View2Array);

        int previousViewId = -1;
        for(int i = 0; i < testArray.size(); i++){
            String[] ViewParams = (String[]) testArray.get(i);
            String viewType = ViewParams[0];
            String text = ViewParams[1];

            // Each array in our data arraylist contains what each part of the overall view would look like
            // we create a seperate view for each part and link them together relatively in MainViewLayout
            View viewSegment;

            if(viewType.equals("image")){
                String image_name = ViewParams[2];
                viewSegment = new TextAndImageView(text, image_name, this);
            }
            else{
                viewSegment = setupArticleView(text);
            }

            if(previousViewId != -1){
                //case where this is not the first view created
                //This is the part to define the relative position of this view compared to the previous view
                ViewGroup.LayoutParams originParam = viewSegment.getLayoutParams();
                RelativeLayout.LayoutParams viewSegParam = new RelativeLayout.LayoutParams(originParam.width, originParam.height);
                viewSegParam.addRule(RelativeLayout.BELOW, previousViewId);
                mainViewLayout.addView(viewSegment, viewSegParam);
            }
            else{
                //case where this is the first view
                mainViewLayout.addView(viewSegment);
            }
            viewSegment.setId(View.generateViewId());
            previousViewId = viewSegment.getId();
        }

        mainView.addView(mainViewLayout);
        return mainView;
    }


    private TextView setupArticleView(String articleName){
        //set up a view to display only the article name and a black frame around the name
        TextView title = new TextView(this);
        title.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        title.setText(articleName);
        title.setBackgroundResource(getResources().getIdentifier("border", "drawable", this.getPackageName()));
        return title;
    }

}

