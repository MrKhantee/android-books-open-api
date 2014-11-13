package rs.in.staleksit.booksopenapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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

import rs.in.staleksit.booksopenapi.model.BookItem;


public class BookItemActivity extends Activity {

    private static final String TAG_NAME = "Books-Open-API";

    private static final String OPEN_IT_BOOKS_API_ENDPOINT_BOOK = "http://it-ebooks-api.info/v1/book/";

    private NetworkImageView biaThumbnail;
    private TextView tvBiaTitle;
    private TextView tvBiaSubtitle;
    private TextView tvBiaAuthor;
    private TextView tvBiaYear;
    private TextView tvBiaPublisher;
    private TextView tvBiaPages;
    private TextView tvBiaDescription;

    private ImageLoader imageLoader = BookAppVolley.getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_item);

        Intent intent = getIntent();
        Long bookItemId = intent.getLongExtra("rs.in.staleksit.booksopenapi.BOOK_ID", 0);

        Log.d(TAG_NAME, "BookItemActivity - ID: " + bookItemId.toString());

        RequestQueue queue = BookAppVolley.getRequestQueue();
        JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET, OPEN_IT_BOOKS_API_ENDPOINT_BOOK + bookItemId.toString(), null, myRequestSuccessListener(), myRequestErrorListener());
        queue.add(searchRequest);

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
    private Response.Listener<JSONObject> myRequestSuccessListener() {
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG_NAME, response.toString());
                try {
                    double searchTime = response.getDouble("Time");
                    String errorCode = response.getString("Error");
                    Log.d(TAG_NAME, "[searchTime: " + searchTime  + "; errorCode: " + errorCode + "]");
                    tvBiaTitle.setText(response.getString("Title"));
                    tvBiaSubtitle.setText(response.getString("SubTitle"));
                    tvBiaAuthor.setText("Author: " + response.getString("Author"));
                    tvBiaYear.setText("Year: " + response.getString("Year"));
                    tvBiaPublisher.setText("Publisher: " + response.getString("Publisher"));
                    tvBiaPages.setText("Pages: " + response.getString("Page"));
                    tvBiaDescription.setText(response.getString("Description"));

                    biaThumbnail.setImageUrl(response.getString("Image"), imageLoader);
                } catch (JSONException jsonEx) {
                    Log.e(TAG_NAME, "There were problems in parsing JSON Response! ERROR: " + jsonEx.getMessage());
                }
            }
        };
        return response;
    }

    private Response.ErrorListener myRequestErrorListener() {
        Response.ErrorListener errorResponse = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG_NAME, volleyError.toString());
            }
        };
        return errorResponse;
    }
}
