package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewLeadActivity extends AppCompatActivity {

    final String url_insert_lead = "https://student03.csucleeds.com/student03/cpu/api.php?apicall=insert";
    Spinner spinner_source, spinner_status, spinner_reason, spinner_type, spinner_rating;
    EditText vendorID, companyID;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead);

        spinner_source = findViewById(R.id.spinner_source);
        spinner_status = findViewById(R.id.spinner_status);
        spinner_reason = findViewById(R.id.spinner_reason);
        spinner_type = findViewById(R.id.spinner_type);
        spinner_rating = findViewById(R.id.spinner_rating);
        vendorID = findViewById(R.id.editText_vendorID);
        companyID = findViewById(R.id.editText_companyID);

        populateSpinners();

        //button
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URLConnectionPostHandler uRLConnectionPostHandler = new URLConnectionPostHandler();
                uRLConnectionPostHandler.setDataDownloadListener(new URLConnectionPostHandler.DataDownloadListener() {
                    @Override
                    public void dataDownloadedSuccessfully(Object data) {
                        // handler result
                        //TODO: Write a check for successful result
                        //Toast.makeText(NewLeadActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(NewLeadActivity.this, generateParameters(), Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void dataDownloadFailed() {
                        Toast.makeText(NewLeadActivity.this, "Record not added.", Toast.LENGTH_SHORT).show();
                    }
                });
                uRLConnectionPostHandler.execute(url_insert_lead, generateParameters());
            }
        });
    }

    private void populateSpinners() {
        List<String> sources = new ArrayList<>();
        sources.add("website");
        sources.add("telephone");
        sources.add("email");

        List<String> status = new ArrayList<>();
        status.add("new");
        status.add("working");
        status.add("qualified");
        status.add("disqualified");
        status.add("customer");

        List<String> reason = new ArrayList<>();
        reason.add("Too Small");
        reason.add("Unserved Geography");
        reason.add("Bad Information");
        reason.add("Competitor");

        List<String> types = new ArrayList<>();
        types.add("commercial");
        types.add("educational");
        types.add("domestic");

        List<String> ratings = new ArrayList<>();
        ratings.add("A");
        ratings.add("B");
        ratings.add("C");

        ArrayAdapter<String> dataAdapterSources = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sources);
        dataAdapterSources.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_source.setAdapter(dataAdapterSources);

        ArrayAdapter<String> dataAdapterStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, status);
        dataAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(dataAdapterStatus);

        ArrayAdapter<String> dataAdapterReasons = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reason);
        dataAdapterReasons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reason.setAdapter(dataAdapterReasons);

        ArrayAdapter<String> dataAdapterTypes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        dataAdapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_type.setAdapter(dataAdapterTypes);

        ArrayAdapter<String> dataAdapterRatings = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ratings);
        dataAdapterRatings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_rating.setAdapter(dataAdapterRatings);

    }

    private String generateParameters() {
        StringBuilder paramString = new StringBuilder();
        paramString.append("source=");
        paramString.append(spinner_source.getItemAtPosition(spinner_source.getSelectedItemPosition()).toString());
        paramString.append("&status=");
        paramString.append(spinner_status.getItemAtPosition(spinner_status.getSelectedItemPosition()).toString());
        paramString.append("&reason=");
        paramString.append(spinner_reason.getItemAtPosition(spinner_reason.getSelectedItemPosition()).toString());
        paramString.append("&typeoflead=");
        paramString.append(spinner_type.getItemAtPosition(spinner_type.getSelectedItemPosition()).toString());
        paramString.append("&vendorid=");
        paramString.append(vendorID.getText().toString());
        paramString.append("&rating=");
        paramString.append(spinner_rating.getItemAtPosition(spinner_rating.getSelectedItemPosition()).toString());
        paramString.append("&companyid=");
        paramString.append(companyID.getText().toString());
        return paramString.toString();
    }


}