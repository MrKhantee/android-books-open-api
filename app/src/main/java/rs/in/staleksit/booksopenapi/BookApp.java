package rs.in.staleksit.booksopenapi;

import android.app.Application;

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
