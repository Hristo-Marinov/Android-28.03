package com.example.android_lesson_1;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddActivity extends AppCompatActivity {

    private TextView data;
    private Button btnAddBooks;
    private String url = "https://simple-books-api.glitch.me/books?type=fiction";
    private SharedPreferencesManager sharedPreferencesManager;

    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sharedPreferencesManager = new SharedPreferencesManager(this);
        data = findViewById(R.id.bookTitle);
        btnAddBooks = findViewById(R.id.btnAddBooks);

        btnAddBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBooksFromApi();
            }
        });
    }

    private void addBooksFromApi() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        sharedPreferencesManager.saveBooksJson(response.toString());
                        insertBooksIntoDatabase(response);
                        Toast.makeText(AddActivity.this, "Books fetched and cached successfully!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddActivity.this, "Error fetching data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(request);
    }
    private void insertBooksIntoDatabase(JSONArray booksArray) {
        try {
            for (int i = 0; i < booksArray.length(); i++) {
                JSONObject bookObject = booksArray.getJSONObject(i);
                String title = bookObject.optString("name", "");
                String author = bookObject.optString("author", "");
                int pages = bookObject.optInt("pages", 0);

                boolean isInserted = myDB.addBook(title, author, pages);
                if (!isInserted) {
                    Toast.makeText(AddActivity.this, "Failed to add book: " + title, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(AddActivity.this, "Error processing books data", Toast.LENGTH_SHORT).show();
        }
    }

}
