package in.co.vbinfotech.mk.bottomnav;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import in.co.vbinfotech.mk.bottomnav.Anim.BounceAnimationInterpolator;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BottomNavigationAdapter extends RecyclerView.Adapter<BottomNavigationAdapter.BottomNavigationItemHolder> {

    private Context mContext;
    private ArrayList<Drawable> mIconList;
    private ArrayList<String> mTitleList;
    private String mOverlayMode;
    private Bitmap mOverlay;
    private LayoutInflater mInflater;
    ItemClickListener mListener;
    private boolean whoIsAnimated;

    public BottomNavigationAdapter(Context context, ArrayList<Drawable> iconList, ArrayList<String> titleList, Bitmap overlay, String overlayMode , ItemClickListener listener) {
        mContext = context;
        mIconList = iconList;
        mTitleList = titleList;
        mOverlayMode = overlayMode;
        mOverlay = overlay;
        mListener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public BottomNavigationItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item, parent, false);
        return new BottomNavigationItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final BottomNavigationItemHolder holder, final int position) {
        Glide.with(mContext).load(mIconList.get(position)).into(holder.mIconView);
        //holder.mIconView.setImageDrawable(mIconList.get(position));
        holder.mTitleView.setText(mTitleList.get(position));
        holder.mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.requestFocus();
                overlayIcon(drawableToBitmap(mIconList.get(position)) , mOverlay , holder.mIconView);
                final Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.click);
                BounceAnimationInterpolator interpolator = new BounceAnimationInterpolator(0.2, 20);
                anim.setInterpolator(interpolator);

                if(whoIsAnimated){
                    holder.mItemView.startAnimation(anim);
                }else{
                    holder.mIconView.startAnimation(anim);
                }

                mListener.onItemClick(position);
            }
        });

        holder.mItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    v.clearFocus();
                    holder.mIconView.setImageDrawable(mIconList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mIconList.size();
    }

    public class BottomNavigationItemHolder extends RecyclerView.ViewHolder {
        ImageView mIconView;
        TextView mTitleView;
        RelativeLayout mItemView;

        public BottomNavigationItemHolder(View itemView) {
            super(itemView);
            mIconView = itemView.findViewById(R.id.icon);
            mTitleView = itemView.findViewById(R.id.title);
            mItemView = itemView.findViewById(R.id.item);
        }
    }

    public void overlayIcon(Bitmap icon, Bitmap overlay , ImageView i) {
        Bitmap img1 = icon;
        Bitmap img2 = overlay;

        // Create result image
        Bitmap result = Bitmap.createBitmap(img1.getWidth(), img1.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(result);

        // Get proper display reference
        BitmapDrawable drawable = new BitmapDrawable(mContext.getResources(), result);
        i.setImageDrawable(drawable);
        canvas.drawBitmap(img1, 0, 0, null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        paint.setShader(new BitmapShader(img2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        canvas.drawRect(0, 0, img2.getWidth(), img2.getHeight(), paint);
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

    /*if value is true the entire view will animate, with value false only icon will animate*/
    public void setWhoIsAnimated(boolean whoIsAnimated){
        this.whoIsAnimated = whoIsAnimated;
    }

}
