package com.ut.meipai.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.ut.meipai.R;

import java.io.File;

/**
 * Created by LZM on 2017/4/19.
 * Function:
 * Desc:
 */

public class GlideUtil {

    private static int sCommonPlaceholder;
    private static int sCirclePlaceholder;
    private static int sRoundPlaceholder;


    static {
        sCommonPlaceholder = R.mipmap.ic_launcher;
        sCirclePlaceholder = R.mipmap.ic_circle_place;
        sRoundPlaceholder = R.mipmap.ic_launcher;
    }

    public GlideUtil() {
    }

    /**
     * 设置圆形图片的占位图
     *
     * @param circlePlaceholder
     */
    public static void setCirclePlaceholder(int circlePlaceholder) {
        sCirclePlaceholder = circlePlaceholder;
    }

    /**
     * 设置正常图片的占位符
     *
     * @param commonPlaceholder
     */
    public static void setCommonPlaceholder(int commonPlaceholder) {
        sCommonPlaceholder = commonPlaceholder;
    }

    /**
     * 设置圆角图片的占位符
     *
     * @param roundPlaceholder
     */
    public static void setsRoundPlaceholder(int roundPlaceholder) {
        sRoundPlaceholder = roundPlaceholder;
    }

    /**
     * 正常加载图片
     *
     * @param obj
     * @param iv
     */
    public static void loadImg(Object obj, ImageView iv) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, iv);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCommonPlaceholder, iv);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .error(sCommonPlaceholder)
                    .placeholder(sCommonPlaceholder)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(iv);
        }
    }


    /**
     * 加载圆形图片
     *
     * @param obj
     * @param iv
     */
    public static void loadCircleImg(Object obj, ImageView iv) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, iv);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sCirclePlaceholder, iv);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .transform(new BitmapTransformation[]{new GlideUtil.GlideCircleTransform(iv.getContext())})
                    .error(sCirclePlaceholder)
                    .placeholder(sCirclePlaceholder).
                    diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
        }
    }

    /**
     * 加载圆角图片
     *
     * @param obj
     * @param iv
     * @param dp
     */
    public static void loadRoundImg(Object obj, ImageView iv, int dp) {
        DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, iv);
        if (drawableTypeRequest == null) {
            drawableTypeRequest = getDrawableTypeRequest(sRoundPlaceholder, iv);
        }
        if (drawableTypeRequest != null) {
            drawableTypeRequest
                    .centerCrop()
                    .dontAnimate()
                    .transform(new BitmapTransformation[]{new GlideUtil.GlideRoundTransform(iv.getContext(), dp)})
                    .error(sRoundPlaceholder)
                    .placeholder(sRoundPlaceholder).
                    diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);
        }
    }

    public static void loadRoundImg(Object obj, ImageView iv) {
        loadRoundImg(obj, iv, 4);
    }

    /**
     *
     * @param obj
     * @param iv
     * @param mode  使用ImageView默认设置加载图片的ScaleType
     * @param dp
     */
    public static void loadRoundImg(Object obj, ImageView iv, int mode, int dp) {
        if (mode == 0) {
            loadRoundImg(obj, iv, dp);
        } else {
            DrawableTypeRequest drawableTypeRequest = getDrawableTypeRequest(obj, iv);
            if (drawableTypeRequest == null) {
                drawableTypeRequest = getDrawableTypeRequest(sRoundPlaceholder, iv);
            }
            if (drawableTypeRequest != null) {

                drawableTypeRequest
                        .dontAnimate()
                        .transform(new BitmapTransformation[]{new GlideUtil.GlideRoundTransform(iv.getContext(), dp)})
                        .error(sRoundPlaceholder)
                        .placeholder(sRoundPlaceholder).
                        diskCacheStrategy(DiskCacheStrategy.RESULT).into(iv);

            }
        }
    }

    @Nullable
    private static DrawableTypeRequest getDrawableTypeRequest(Object obj, ImageView iv) {
        if (iv == null || obj == null) return null;
        Context context = iv.getContext();
        RequestManager manager = Glide.with(context);
        DrawableTypeRequest drawableTypeRequest = null;
        if (obj instanceof String) {
            drawableTypeRequest = manager.load((String) obj);
        } else if (obj instanceof Integer) {
            drawableTypeRequest = manager.load((Integer) obj);
        } else if (obj instanceof Uri) {
            drawableTypeRequest = manager.load((Uri) obj);
        } else if (obj instanceof File) {
            drawableTypeRequest = manager.load((File) obj);
        }
        return drawableTypeRequest;
    }

    private static class GlideCircleTransform extends BitmapTransformation {
        public GlideCircleTransform(Context context) {
            super(context);
        }

        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) {
                return null;
            } else {
                int size = Math.min(source.getWidth(), source.getHeight());
                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;
                Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
                Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
                if (result == null) {
                    result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
                }

                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float r = (float) size / 2.0F;
                canvas.drawCircle(r, r, r, paint);
                return result;
            }
        }

        public String getId() {
            return this.getClass().getName();
        }
    }

    private static class GlideRoundTransform extends BitmapTransformation {
        float radius = 0f;

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap toTransform) {
            if (toTransform == null) return null;

            Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return this.getClass().getName();
        }
    }
}
