package rs.in.staleksit.booksopenapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import rs.in.staleksit.booksopenapi.BookAppVolley;
import rs.in.staleksit.booksopenapi.R;
import rs.in.staleksit.booksopenapi.model.BookItem;
import rs.in.staleksit.booksopenapi.model.BookSearch;

/**
 * Created by astoisavljevic on 21.11.14..
 */
public class BookArrayAdapter extends ArrayAdapter<BookItem> {

    private ImageLoader mImageLoader;

    public BookArrayAdapter(Context context, int textViewResourceId, List<BookItem> books, ImageLoader imageLoader) {
        super(context, textViewResourceId, books);
        mImageLoader = imageLoader;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_row, null);
        }

        ViewHolder holder = (ViewHolder) v.getTag(R.id.id_holder);

        if (holder == null) {
            holder = new ViewHolder(v);
            v.setTag(R.id.id_holder, holder);
        }

        BookItem item = getItem(position);
        if (item.getImageUrl() != null) {
            holder.image.setImageUrl(item.getImageUrl(), mImageLoader);
        } else {
            holder.image.setImageResource(R.drawable.no_image);
        }

        holder.title.setText(item.getTitle());
        holder.subTitle.setText(item.getSubTitle());

        return v;
    }

    private class ViewHolder {
        NetworkImageView image;
        TextView title;
        TextView subTitle;

        public ViewHolder(View v) {
            image = (NetworkImageView) v.findViewById(R.id.thumbnail);
            title = (TextView) v.findViewById(R.id.title);
            subTitle = (TextView) v.findViewById(R.id.subTitle);
            v.setTag(this);
        }
    }



}
