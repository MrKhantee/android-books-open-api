package rs.in.staleksit.booksopenapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rs.in.staleksit.booksopenapi.adapter.BookAdapter;
import rs.in.staleksit.booksopenapi.model.BookItem;


public class MainActivity extends Activity {

    private static final String TAG_NAME = "Books-Open-API";

    private static final String OPEN_IT_BOOKS_API_ENDPOINT_SEARCH = "http://it-ebooks-api.info/v1/search/";

    private EditText etQuery;
    private Button btnSearch;

    private ListView lvQueryResult;

    private BookAdapter adapter;

    private List<BookItem> bookItemList = new ArrayList<BookItem>(0);

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etQuery = (EditText) findViewById(R.id.etQuery);
        if (null != etQuery) {
            etQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG_NAME, "EditText - etQuery clicked!");
                    etQuery.setText("");
                }
            });
        }

        btnSearch = (Button) findViewById(R.id.btnSearch);
        // check if btnSearch is located in view
        if (null != btnSearch) {
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG_NAME, "Button - btnSearch clicked!");
                    pDialog = ProgressDialog.show(MainActivity.this, "", "Downloading ...");

                    RequestQueue queue = BookAppVolley.getRequestQueue();
                    JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET, OPEN_IT_BOOKS_API_ENDPOINT_SEARCH + etQuery.getText().toString(), null, myRequestSuccessListener(), myRequestErrorListener());
                    queue.add(searchRequest);
                }
            });
        }

        lvQueryResult = (ListView) findViewById(R.id.lvQueryResult);

        adapter = new BookAdapter(this, bookItemList);
        lvQueryResult.setAdapter(adapter);

        lvQueryResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG_NAME, "onItemClicked - [position: " + position + "; id: " + id + "]");
                BookItem selectedBookItem = (BookItem) parent.getAdapter().getItem(position);
                Log.d(TAG_NAME, "Should open new activity [BookItemActivity] ID: " + selectedBookItem.getId().toString() + "; title: " + selectedBookItem.getTitle());
                Toast.makeText(MainActivity.this, "title: " + selectedBookItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                adapter.clear();
                try {
                    double searchTime = response.getDouble("Time");
                    int page = response.getInt("Page");
                    String total = response.getString("Total");
                    String errorCode = response.getString("Error");
                    Log.d(TAG_NAME, "[searchTime: " + searchTime  + "; page: " + page + "; total: " + total + "; errorCode: " + errorCode + "]");
                    JSONArray booksArray = response.getJSONArray("Books");
                    for (int i=0; i < booksArray.length(); i++) {
                        JSONObject bookItemJSON = booksArray.getJSONObject(i);
                        Log.d(TAG_NAME, "BookItem: [ID: " + bookItemJSON.getLong("ID") + "; Title: " + bookItemJSON.getString("Title") + "]");
                        BookItem bookItem = new BookItem();
                        bookItem.setId(bookItemJSON.getLong("ID"));
                        bookItem.setTitle(bookItemJSON.getString("Title"));
                        try {
                            bookItem.setSubTitle(bookItemJSON.getString("SubTitle"));
                        } catch (JSONException jsonEx) {
                            Log.e(TAG_NAME, jsonEx.getMessage());
                            bookItem.setSubTitle("");
                        }
                        bookItem.setImageUrl(bookItemJSON.getString("Image"));
                        bookItemList.add(bookItem);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException jsonEx) {
                    Log.e(TAG_NAME, "There were problems in parsing JSON Response! ERROR: " + jsonEx.getMessage());
                }
            }
        };
        hideProgressDialog();
        return response;
    }

    private Response.ErrorListener myRequestErrorListener() {
        Response.ErrorListener errorResponse = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG_NAME, volleyError.toString());
            }
        };
        hideProgressDialog();
        return errorResponse;
    }

    private void hideProgressDialog() {
        if (null != pDialog) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

 }
