package rs.in.staleksit.booksopenapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.in.staleksit.booksopenapi.BookAppVolley;
import rs.in.staleksit.booksopenapi.R;
import rs.in.staleksit.booksopenapi.model.BookItem;


/**
 * Created by astoisavljevic on 12.11.14..
 */
public class BookAdapter extends BaseAdapter {

    private static final String TAG_NAME = "Books-Open-API";

    private static LayoutInflater inflater = null;

    private Activity activity;
    private List<BookItem> data;

    ImageLoader imageLoader = BookAppVolley.getImageLoader();

    public BookAdapter(Activity activity, List<BookItem> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (null == convertView) {
            vi = inflater.inflate(R.layout.list_row, null);
        }

        if (imageLoader == null)
            imageLoader = BookAppVolley.getImageLoader();
        NetworkImageView thumbnail = (NetworkImageView) vi.findViewById(R.id.thumbnail);

        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView subTitle = (TextView) vi.findViewById(R.id.subTitle);

        BookItem bookItem = data.get(position);

        Log.d(TAG_NAME, "Url: " + bookItem.getImageUrl());
        thumbnail.setImageUrl(bookItem.getImageUrl(), imageLoader);

        title.setText(bookItem.getTitle());
        subTitle.setText(bookItem.getSubTitle());

        return vi;
    }
}
