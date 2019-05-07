package in.co.vbinfotech.bottomnavigationlib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

import in.co.vbinfotech.mk.bottomnav.BottomNavigationView;
import in.co.vbinfotech.mk.bottomnav.ItemClickListener;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Drawable> mIconList;
    private ArrayList<String> mTitleList;
    private Bitmap mOverlay;
    private FrameLayout mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*  feedbackOverlayMode : Allowed Values are "ADD", "CLEAR", "DARKEN", "DST", "DST_ATOP", "DST_IN", "DST_OUT", "DST_OVER", "LIGHTEN", "MULTIPLY", "OVERLAY",
        "SCREEN", "SRC", "SRC_ATOP", "SRC_IN", "SRC_OUT", "SRC_OVER", "XOR" */
        mContainer = findViewById(R.id.container);
        mIconList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        mIconList.add(getResources().getDrawable(R.drawable.home));
        mIconList.add(getResources().getDrawable(R.drawable.blend));
        mIconList.add(getResources().getDrawable(R.drawable.border));
        mIconList.add(getResources().getDrawable(R.drawable.camera));
        mIconList.add(getResources().getDrawable(R.drawable.colorbucket));
        mIconList.add(getResources().getDrawable(R.drawable.config));
        mIconList.add(getResources().getDrawable(R.drawable.effect));
        mIconList.add(getResources().getDrawable(R.drawable.face));
        mIconList.add(getResources().getDrawable(R.drawable.heart));
        mIconList.add(getResources().getDrawable(R.drawable.invert));
        mIconList.add(getResources().getDrawable(R.drawable.opacity));
        mIconList.add(getResources().getDrawable(R.drawable.rotate));
        mIconList.add(getResources().getDrawable(R.drawable.text));

        mTitleList.add("Home");
        mTitleList.add("Blend");
        mTitleList.add("Border");
        mTitleList.add("Camera");
        mTitleList.add("Bucket");
        mTitleList.add("Config");
        mTitleList.add("Effect");
        mTitleList.add("Emoji");
        mTitleList.add("Rate");
        mTitleList.add("Invert");
        mTitleList.add("Opacity");
        mTitleList.add("Rotate");
        mTitleList.add("Text");

        /*Overlay Color Image, This Overlay will be applied on click of menu Item*/
        mOverlay = BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.overlay);

        BottomNavigationView nav = new BottomNavigationView.Builder(MainActivity.this)
                .setIconList(mIconList)
                .setTitleList(mTitleList)
                .setFeedbackOverlay(mOverlay)
                .setFeedbackOverlayMode("DARKEN")
                .isViewAnimated(true)
                .isIconAnimated(false)
                .setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        mContainer.addView(nav);


    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void createLists(){

    }
}
