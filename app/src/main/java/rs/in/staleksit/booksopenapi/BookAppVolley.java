package rs.in.staleksit.booksopenapi;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by astoisavljevic on 13.11.14..
 */
public class BookAppVolley {

    private static RequestQueue mRequestQueue;


    private BookAppVolley() {

    }

    static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
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

}
