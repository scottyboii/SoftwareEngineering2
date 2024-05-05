package com.example.httpurlconnectionexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewLeadActivity extends AppCompatActivity {

    private static final String URL_INSERT_LEAD = "https://student03.csucleeds.com/student03/cpu/api.php?apicall=insert";

    private Spinner sourceSpinner, statusSpinner, reasonSpinner, typeSpinner, ratingSpinner;
    private EditText vendorIdEditText, companyIdEditText;
    private Button submitButton;

    private URLConnectionPostHandler urlConnectionPostHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead);

        initializeUiComponents();
        populateSpinners();
        setSubmitButtonListener();
    }

    private void initializeUiComponents() {
        sourceSpinner = findViewById(R.id.spinner_source);
        statusSpinner = findViewById(R.id.spinner_status);
        reasonSpinner = findViewById(R.id.spinner_reason);
        typeSpinner = findViewById(R.id.spinner_type);
        ratingSpinner = findViewById(R.id.spinner_rating);
        vendorIdEditText = findViewById(R.id.editText_vendorID);
        companyIdEditText = findViewById(R.id.editText_companyID);
        submitButton = findViewById(R.id.button_submit);
    }

    private void populateSpinners() {
        List<String> sources = new ArrayList<>(List.of("Website", "Telephone", "Email"));
        List<String> status = new ArrayList<>(List.of("New", "Working", "Qualified", "Disqualified", "Customer"));
        List<String> reasons = new ArrayList<>(List.of("Not Disqualified", "Too Small", "Unserved Geography", "Bad Information", "Competitor"));
        List<String> types = new ArrayList<>(List.of("Commercial", "Educational", "Domestic"));
        List<String> ratings = new ArrayList<>(List.of("A", "B", "C"));

        setSpinnerAdapter(sourceSpinner, sources);
        setSpinnerAdapter(statusSpinner, status);
        setSpinnerAdapter(reasonSpinner, reasons);
        setSpinnerAdapter(typeSpinner, types);
        setSpinnerAdapter(ratingSpinner, ratings);
    }

    private void setSpinnerAdapter(Spinner spinner, List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setSubmitButtonListener() {
        urlConnectionPostHandler = new URLConnectionPostHandler();
        urlConnectionPostHandler.setDataDownloadListener(new URLConnectionPostHandler.DataDownloadListener() {
            @Override
            public void dataDownloadedSuccessfully(Object data) {
                handleSuccessfulDataDownload(data);
            }

            @Override
            public void dataDownloadFailed() {
                handleFailedDataDownload();
            }
        });

        submitButton.setOnClickListener(v -> {
            String parameters = generateParameters();
            urlConnectionPostHandler.execute(URL_INSERT_LEAD, parameters);
        });
    }

    private String generateParameters() {
        return "source=" + getSelectedSpinnerItem(sourceSpinner) +
                "&status=" + getSelectedSpinnerItem(statusSpinner) +
                "&reason=" + getSelectedSpinnerItem(reasonSpinner) +
                "&typeoflead=" + getSelectedSpinnerItem(typeSpinner) +
                "&vendorid=" + vendorIdEditText.getText().toString() +
                "&rating=" + getSelectedSpinnerItem(ratingSpinner) +
                "&companyid=" + companyIdEditText.getText().toString();
    }

    private String getSelectedSpinnerItem(Spinner spinner) {
        return spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
    }

    private void handleSuccessfulDataDownload(Object data) {
        Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void handleFailedDataDownload() {
        Toast.makeText(this, "Record not added.", Toast.LENGTH_SHORT).show();
    }
}