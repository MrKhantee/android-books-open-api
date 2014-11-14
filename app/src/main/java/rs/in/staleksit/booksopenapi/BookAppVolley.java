package rs.in.staleksit.booksopenapi;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

import rs.in.staleksit.booksopenapi.toolbox.BitmapLruCache;

/**
 * Created by astoisavljevic on 13.11.14..
 */
public class BookAppVolley {

    private static final String GOOGLE_ANALYTICS_BOOKS_OPEN_API_TRACKING_CODE = "UA-56765445-1";

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    private static HashMap<TrackerName, Tracker> mTrackers;
    private static GoogleAnalytics googleAnalytics;

    private BookAppVolley() {

    }

    static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));

        googleAnalytics = GoogleAnalytics.getInstance(context);
        mTrackers = new HashMap<TrackerName, Tracker>();
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

    public static synchronized Tracker getTracker(TrackerName trackerName) {
        if (!mTrackers.containsKey(trackerName)) {
            Tracker tracker = googleAnalytics.newTracker(GOOGLE_ANALYTICS_BOOKS_OPEN_API_TRACKING_CODE);
            // tracker.enableAdvertisingIdCollection(true);
            mTrackers.put(trackerName, tracker);
        }
        return mTrackers.get(trackerName);
    }


    public enum TrackerName {
        APP_TRACKER
    }

}
