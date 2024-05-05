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

    private static final String URL_API_VIEW = "https://student03.csucleeds.com/student03/cpu/api.php?apicall=view";

    private FloatingActionButton addLeadButton;
    private ListView leadsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUiComponents();
        setAddLeadButtonListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchAndDisplayLeads();
    }

    private void initializeUiComponents() {
        addLeadButton = findViewById(R.id.floatingActionButton);
        leadsListView = findViewById(R.id.listView);
    }

    private void setAddLeadButtonListener() {
        addLeadButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewLeadActivity.class);
            startActivity(intent);
        });
    }

    private void fetchAndDisplayLeads() {
        URLConnectionGetHandler urlConnectionGetHandler = new URLConnectionGetHandler();
        urlConnectionGetHandler.setDataDownloadListener(new URLConnectionGetHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                displayLeads((String) data);
            }

            @Override
            public void dataDownloadFailed() {
                showNoRecordsFoundMessage();
            }
        });
        urlConnectionGetHandler.execute(URL_API_VIEW);
    }

    private void displayLeads(String jsonString) {
        List<String> leadStrings = parseLeadsFromJson(jsonString);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, leadStrings);
        leadsListView.setAdapter(adapter);
    }

    /**
     * Convert json to a list of leads
     * @param jsonString
     * @return List of Leads
     */
    private List<String> parseLeadsFromJson(String jsonString) {
        try {
            jsonString = jsonString.substring(jsonString.indexOf("{"));
            List<String> leadStrings = new ArrayList<>();
            JSONObject root = new JSONObject(jsonString);
            JSONArray leadsArray = root.getJSONArray("leads");

            for (int i = 0; i < leadsArray.length(); i++) {
                JSONObject leadObject = leadsArray.getJSONObject(i);
                Lead lead = createLeadFromJson(leadObject);
                leadStrings.add(lead.toString());
            }
            return leadStrings;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Lead createLeadFromJson(JSONObject leadObject) throws JSONException {
        return new Lead(
                leadObject.getString("id"),
                leadObject.getString("source"),
                leadObject.getString("status"),
                leadObject.getString("reason_disqualified"),
                leadObject.getString("type"),
                leadObject.getString("current_component_vendor_id"),
                leadObject.getString("rating"),
                leadObject.getString("contact_id")
        );
    }

    private void showNoRecordsFoundMessage() {
        Toast.makeText(this, "No records found.", Toast.LENGTH_SHORT).show();
    }
}