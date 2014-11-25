package rs.in.staleksit.booksopenapi;

import android.app.Activity;
// import android.content.DialogInterface;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import rs.in.staleksit.booksopenapi.model.BookItem;
import rs.in.staleksit.booksopenapi.toolbox.GsonRequest;


public class BookItemActivity extends Activity {

    private static final String TAG_NAME = "Books-Open-API";

    private NetworkImageView biaThumbnail;
    private TextView tvBiaTitle;
    private TextView tvBiaSubtitle;
    private TextView tvBiaAuthor;
    private TextView tvBiaYear;
    private TextView tvBiaPublisher;
    private TextView tvBiaPages;
    private TextView tvBiaDescription;

    private ImageLoader imageLoader = BookAppVolley.getImageLoader();

    private String bookItemUrl;
    private String bookItemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item);

        Intent intent = getIntent();
        Long bookItemId = intent.getLongExtra("rs.in.staleksit.booksopenapi.BOOK_ID", 0);

        RequestQueue queue = BookAppVolley.getRequestQueue();

        GsonRequest<BookItem> bookItemGsonRequest = new GsonRequest<BookItem>(Request.Method.GET,
                BookItemContract.OPEN_IT_BOOKS_API_ENDPOINT_BOOK + bookItemId.toString(),
                BookItem.class,
                myRequestSuccessListener(),
                myRequestErrorListener());

        queue.add(bookItemGsonRequest);

        if (imageLoader == null)
            imageLoader = BookAppVolley.getImageLoader();

        biaThumbnail = (NetworkImageView) findViewById(R.id.biaThumbnail);

        tvBiaTitle = (TextView) findViewById(R.id.biaTitle);
        tvBiaSubtitle = (TextView) findViewById(R.id.biaSubTitle);
        tvBiaAuthor = (TextView) findViewById(R.id.biaAuthor);
        tvBiaYear = (TextView) findViewById(R.id.biaYear);
        tvBiaPublisher = (TextView) findViewById(R.id.biaPublisher);
        tvBiaPages = (TextView) findViewById(R.id.biaPages);
        tvBiaDescription = (TextView) findViewById(R.id.biaDescription);

    }

    /**
     * in case of success of volley request
     * @return
     */
    private Response.Listener<BookItem> myRequestSuccessListener() {
        Response.Listener<BookItem> response = new Response.Listener<BookItem>() {
            @Override
            public void onResponse(BookItem response) {
                tvBiaTitle.setText(response.getTitle());
                tvBiaSubtitle.setText(response.getSubTitle());
                tvBiaAuthor.setText("Author: "  + response.getAuthor());
                tvBiaYear.setText("Year " + response.getYear());
                tvBiaPublisher.setText("Publisher: " + response.getPublisher());
                tvBiaPages.setText("Pages: " + response.getPage());
                tvBiaDescription.setText(response.getDescription());

                biaThumbnail.setImageUrl(response.getImageUrl(), imageLoader);
            }
        };
        return response;
    }

    private Response.ErrorListener myRequestErrorListener() {
        Response.ErrorListener errorResponse = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                final Dialog dialog = new Dialog(BookItemActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Error");

                TextView textView = (TextView) dialog.findViewById(R.id.cdTextView);
                textView.setText(volleyError.toString());

                Button cdButton = (Button) dialog.findViewById(R.id.cdButton);
                cdButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        };
        return errorResponse;
    }

}
