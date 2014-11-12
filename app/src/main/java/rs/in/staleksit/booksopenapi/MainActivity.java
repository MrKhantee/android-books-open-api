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

import java.util.ArrayList;
import java.util.List;

import rs.in.staleksit.booksopenapi.adapter.BookAdapter;
import rs.in.staleksit.booksopenapi.model.BookItem;


public class MainActivity extends Activity {

    private static final String TAG_NAME = "Books-Open-API";

    private EditText etQuery;
    private Button btnSearch;

    private ListView lvQueryResult;

    private BookAdapter adapter;

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
                }
            });
        }

        lvQueryResult = (ListView) findViewById(R.id.lvQueryResult);

        adapter = new BookAdapter(this, getDummyBookList());
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


 }
