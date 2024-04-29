package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // API URL for my api
    final String url_api_view = "https://student03.csucleeds.com/student03/cpu/api.php?apicall=view";

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the new lead activity to add a lead
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), NewLeadActivity.class);
            startActivity(intent);
        });
    }


    public void onResume() {
        super.onResume();

        URLConnectionGetHandler uRLConnectionGetHandler = new URLConnectionGetHandler();
        uRLConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                ListView listView = (ListView) findViewById(R.id.listView);

                // Add the leads to the view
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1, Objects.requireNonNull(jsonDecoder((String) data)));

                if (listView != null) {
                    listView.setAdapter(adapter);
                }
            }

            // If it failed to get the data from the API
            @Override
            public void dataDownloadFailed() {
                Toast.makeText(MainActivity.this, "No records found.", Toast.LENGTH_SHORT).show();

            }
        });
        uRLConnectionGetHandler.execute(url_api_view);
    }

    /**
     * Decode the json that is returned from the api and create a new lead to be displayed
     * @param json_string           String of json that is passed into the function
     * @return                      Returns a List of strings for each attribute for the Lead
     */
    private List<String> jsonDecoder(String json_string) {
        try {
            json_string = json_string.substring(json_string.indexOf("{"));

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("leads");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Lead newLead = new Lead(
                        object.getString("id"),
                        object.getString("source"),
                        object.getString("status"),
                        object.getString("reason_disqualified"),
                        object.getString("type"),
                        object.getString("current_component_vendor_id"),
                        object.getString("rating"),
                        object.getString("contact_id"));
                items.add(newLead.toString());
            }
            return items;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
