package rs.in.staleksit.booksopenapi;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import rs.in.staleksit.booksopenapi.toolbox.BitmapLruCache;

/**
 * Created by astoisavljevic on 13.11.14..
 */
public class BookAppVolley {

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;


    private BookAppVolley() {

    }

    static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
    }

    public static RequestQueue getRequestQueue() {
        RequestQueue result = null;
        if (mRequestQueue != null) {
            result = mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue can't be initialized!");
        }
        return result;
    }

    /**
     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
     * that no memory caching is used. This is useful for images that you know that will be show
     * only once.
     *
     * @return
     */
    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }

}
