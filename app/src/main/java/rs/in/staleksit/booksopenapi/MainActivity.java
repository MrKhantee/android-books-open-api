package rs.in.staleksit.booksopenapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

                    RequestQueue queue = BookAppVolley.getRequestQueue();
                    JsonObjectRequest searchRequest = new JsonObjectRequest(Request.Method.GET, OPEN_IT_BOOKS_API_ENDPOINT_SEARCH + etQuery.getText().toString(), null, myRequestSuccessListener(), myRequestErrorListener());
                    queue.add(searchRequest);
                }
            });
        }

        lvQueryResult = (ListView) findViewById(R.id.lvQueryResult);

        // bookItemList = getDummyBookList();

        adapter = new BookAdapter(this, bookItemList);
        lvQueryResult.setAdapter(adapter);

        lvQueryResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG_NAME, "onItemClicked - [position: " + position + "; id: " + id + "]");
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

    private List<BookItem> getDummyBookList() {
        List<BookItem> result = new ArrayList<BookItem>();

        BookItem bookItem1 = new BookItem(1529159300L,
                "Expert Oracle and Java Security",
                "Expert Oracle and Java Security: Programming Secure Oracle Database Applications with Java provides ...",
                "http://s.it-ebooks-api.info/6/expert_oracle_and_java_security.jpg",
                "9781430238317",
                "Programming Secure Oracle Database Applications With Java",
                "David Coffin",
                "2011",
                "472",
                "Apress",
                "http://filepi.com/i/RSpHA1T");
        result.add(bookItem1);

        BookItem bookItem2 = new BookItem(832671681L,
                "The Well-Grounded Java Developer",
                "The Well-Grounded Java Developer starts with thorough coverage of Java 7 features ...",
                "http://s.it-ebooks-api.info/5/the_well-grounded_java_developer.jpg",
                "9781617290060",
                "Vital techniques of Java 7 and polyglot programming",
                "Benjamin J. Evans, Martijn Verburg",
                "2012",
                "496",
                "Manning",
                "http://filepi.com/i/J8BBzyR");
        result.add(bookItem2);


        return result;
    }

    private Response.Listener<JSONObject> myRequestSuccessListener() {
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG_NAME, response.toString());
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
                        bookItem.setSubTitle(bookItemJSON.getString("SubTitle"));
                        bookItem.setImageUrl(bookItemJSON.getString("Image"));
                        bookItemList.add(bookItem);
                    }
                    adapter.notifyDataSetChanged();
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
