package rs.in.staleksit.booksopenapi;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.util.HashMap;

/**
 * Created by astoisavljevic on 13.11.14..
 */
public class BookApp extends Application {


    private void init() {
        BookAppVolley.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }


}
