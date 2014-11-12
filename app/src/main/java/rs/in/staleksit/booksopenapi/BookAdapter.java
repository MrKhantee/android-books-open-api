package rs.in.staleksit.booksopenapi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by astoisavljevic on 12.11.14..
 */
public class BookAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private Activity activity;
    private List<BookItem> data;

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

        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView subTitle = (TextView) vi.findViewById(R.id.subTitle);

        BookItem bookItem = data.get(position);

        title.setText(bookItem.getTitle());
        subTitle.setText(bookItem.getSubTitle());

        return vi;
    }
}
