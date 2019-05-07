package in.co.vbinfotech.mk.bottomnav;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BottomNavigationView extends RecyclerView {
    private Context mContext;
    private BottomNavigationAdapter mAdapter;
    private ArrayList<Drawable> mIconList;
    private ArrayList<String> mTitleList;
    private String mOverlayMode;
    private Bitmap mOverlay;
    ItemClickListener mListener;
    private boolean isViewAnimated;
    private boolean isIconAnimated;
    public BottomNavigationView(Context context) {
        super(context);
        mContext = context;
    }
    public BottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomNavigationView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BottomNavigationView(BottomNavigationView.Builder builder){
        super(builder.context);
        mContext = builder.context;
        mIconList = builder.IconList;
        mTitleList = builder.TitleList;
        mOverlay = builder.Overlay;
        mOverlayMode = builder.OverlayFeedbackMode;
        mListener = builder.Listener;
        isViewAnimated = builder.isViewAnimated;
        isIconAnimated = builder.isIconAnimated;

        this.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.HORIZONTAL , false));
        /*Adapter Required Params*/
        mAdapter = new BottomNavigationAdapter(mContext, mIconList, mTitleList, mOverlay, mOverlayMode, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*The Position in Implementing Activity , will begin from 1 */
                mListener.onItemClick(position+1);
            }
        });

        /*if value is true the entire view will animate, with value false only icon will animate*/
        if(isViewAnimated){
            mAdapter.setWhoIsAnimated(true);
        }else if(isIconAnimated){
            mAdapter.setWhoIsAnimated(false);
        }
        this.setAdapter(mAdapter);

    }

    @Override
    public Adapter getAdapter() {
        return mAdapter;
    }

    /*The View Builder Class to Build the View From Implementation Activity*/
    public static class Builder{
        private Context context;
        private ArrayList<Drawable> IconList;
        private ArrayList<String> TitleList;
        private Bitmap Overlay;
        private String OverlayFeedbackMode;
        private ItemClickListener Listener;

        /*Default Animation Values to the Builder*/
        private boolean isViewAnimated = false;
        private boolean isIconAnimated = true;

        public Builder(Context c){
            context = c;
        }
        public Builder setIconList(ArrayList<Drawable> iconsList){
            IconList = iconsList;
            return this;
        }

        public Builder setTitleList(ArrayList<String> titleList){
            TitleList = titleList;
            return this;
        }

        public Builder setFeedbackOverlay(Bitmap overlayImageBitmap){
            Overlay = overlayImageBitmap;
            return this;
        }

        public Builder setFeedbackOverlayMode(String MODE){
            OverlayFeedbackMode = MODE;
            return this;
        }

        public Builder setItemClickListener(ItemClickListener l){
            Listener = l;
            return this;
        }

        public Builder isViewAnimated(boolean isAnimated){
            isViewAnimated = isAnimated;
            return this;
        }

        public Builder isIconAnimated(boolean isAnimated){
            isIconAnimated = isAnimated;
            return this;
        }

        public BottomNavigationView build(){
            BottomNavigationView v = new BottomNavigationView(this);
            if (this.IconList.isEmpty()) {
                throw new IllegalStateException("Empty IconList Provided");
            }
            if (this.TitleList.isEmpty()) {
                throw new IllegalStateException("Empty TitleList Provided");
            }
            if (this.Overlay == null) {
                throw new IllegalStateException("Feedback Overlay Image Bitmap not Provided");
            }
            if(this.TitleList.size() != this.IconList.size()){
                throw new IllegalStateException("Invalid Number Of Items, Both ArrayList Should contain same number of items only!");
            }
            return v;
        }

    }

}
