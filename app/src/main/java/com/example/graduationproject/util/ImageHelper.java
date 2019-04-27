package com.example.graduationproject.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.graduationproject.R;
import com.example.graduationproject.util.glide.GlideApp;

import java.security.MessageDigest;


/**
 * 图片处理
 */
public final class ImageHelper {


    public static void showRoundedImage(Context context, ImageView imageView, String url) {
        if (context == null || imageView == null || url == null)
            return;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_placeholder);
        requestOptions.centerCrop();
        //requestOptions.transform(new RoundedCornersTransformation(100,30));
        requestOptions.transform(new GlideRoundTransform(context, 10));
        GlideApp.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void showRoundedImage(Context context, ImageView imageView, int drawableId) {
        if (context == null || imageView == null || drawableId == -1)
            return;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_placeholder);
        requestOptions.centerCrop();
        //requestOptions.transform(new RoundedCornersTransformation(100,30));
        requestOptions.transform(new GlideRoundTransform(context, 10));
        GlideApp.with(context).load(drawableId).apply(requestOptions).into(imageView);
    }


    public static void showImage(Context context, ImageView imageView, int drawableId) {
        if (context == null || imageView == null || drawableId <= 0) {
            if (drawableId == 0) {
                imageView.setVisibility(View.INVISIBLE);
            }
            return;
        }
        imageView.setVisibility(View.VISIBLE);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.image_placeholder);
        //imageView.setImageResource(drawableId);
        GlideApp.with(context).load(drawableId).apply(requestOptions).into(imageView);
    }

    public static void showImage(Context context, ImageView imageView, String url, int defaultId, int errorId) {
        if (context == null || imageView == null || url == null || defaultId <= 0 || errorId <= 0)
            return;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(defaultId);
        requestOptions.error(errorId);
        GlideApp.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void showImage(Context context, ImageView imageView, String url) {
        if (context == null || imageView == null || url == null)
            return;
        GlideApp.with(context).load(url).into(imageView);
    }




    public static void showImageGifOnce(Context context, int url, ImageView imageView) {

        GlideApp.with(context).load(url).listener(new RequestListener<Drawable>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                GifDrawable drawable = (GifDrawable) resource;
                //drawable.setLoopCount();
                //drawable.setLoopCount(1);

                return false;
            }
        }).into(imageView);
    }


    /**
     * @param context
     * @param imageView
     * @param drawableId
     */
//    public static void showHeadPortrait(Context context, ImageView imageView, int drawableId) {
//        if (context == null || imageView == null || drawableId <= 0)
//            return;
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.head_portrait_placeholder);
//        requestOptions.transform(new ShadowCircleCrop());
//        GlideApp.with(context).load(drawableId).apply(requestOptions).into(imageView);
//    }
//
//    /**
//     * @param context
//     * @param imageView
//     * @param drawableId
//     * @param defaultId
//     */
//    public static void showHeadPortrait(Context context, ImageView imageView, int drawableId, int defaultId) {
//        if (context == null || imageView == null || drawableId <= 0 || defaultId <= 0)
//            return;
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(defaultId);
//        requestOptions.transform(new ShadowCircleCrop());
//        GlideApp.with(context).load(drawableId).apply(requestOptions).into(imageView);
//    }
//
//    /**
//     * @param context
//     * @param imageView
//     * @param url
//     */
//    public static void showHeadPortrait(Context context, ImageView imageView, String url) {
//        if (context == null || imageView == null || url == null)
//            return;
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.head_portrait_placeholder);
//        requestOptions.transform(new ShadowCircleCrop());
//        GlideApp.with(context).load(url).apply(requestOptions).into(imageView);
//    }
//
//    /**
//     * @param context
//     * @param imageView
//     * @param url
//     * @param errorId
//     */
//    public static void showHeadPortrait(Context context, ImageView imageView, String url, int errorId) {
//        if (context == null || imageView == null || url == null)
//            return;
//        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.head_portrait_placeholder);
//        requestOptions.error(errorId);
//        requestOptions.transform(new ShadowCircleCrop());
//        GlideApp.with(context).load(url).apply(requestOptions).into(imageView);
//    }
//    public static String GetStringByImageView(ImageView imageView){
//        // 从ImageView得到Bitmap对象
//        if(((BitmapDrawable)imageView.getDrawable()).getBitmap()==null){
//            return null;
//        }
//        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//        // 把Bitmap转码成字符串
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 50,baos);
//        String imageBase64 = new String (Base64.encode(baos.toByteArray(), 0));
//        return imageBase64;
//    }
//

    /**
     * @param context
     * @param imageView
     * @param url
     * @param defaultId
     * @param errorId
     */
    public static void showHeadPortrait(Context context, ImageView imageView, String url, int defaultId, int errorId) {
        if (context == null || imageView == null || url == null)
            return;
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(defaultId);
        requestOptions.error(errorId);
        requestOptions.transform(new ShadowCircleCrop());
        GlideApp.with(context).load(url).apply(requestOptions).into(imageView);
    }


    private static class ShadowCircleCrop extends BitmapTransformation {
        // The version of this transformation, incremented to correct an error in a previous version.
        // See #455.
        private static final int VERSION = 1;
        private static final String ID = "com.bumptech.glide.load.resource.bitmap.CircleCrop." + VERSION;
        private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

        public ShadowCircleCrop() {
            // Intentionally empty.
        }

        /**
         * @deprecated Use {@link #ShadowCircleCrop()}.
         */
        @Deprecated
        public ShadowCircleCrop(@SuppressWarnings("unused") Context context) {
            this();
        }

        /**
         * @deprecated Use {@link #ShadowCircleCrop()}
         */
        @Deprecated
        public ShadowCircleCrop(@SuppressWarnings("unused") BitmapPool bitmapPool) {
            this();
        }

        // Bitmap doesn't implement equals, so == and .equals are equivalent here.
        @SuppressWarnings("PMD.CompareObjectsWithEquals")
        @Override
        protected Bitmap transform(
                @NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {

            return TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof com.bumptech.glide.load.resource.bitmap.CircleCrop;
        }

        @Override
        public int hashCode() {
            return ID.hashCode();
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {
            messageDigest.update(ID_BYTES);
        }
    }

    public static class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
            return TransformationUtils.roundedCorners(pool, bitmap, outWidth, outHeight, 20);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        public String getId() {
            return getClass().getName() + Math.round(radius);
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) {

        }
    }


}
