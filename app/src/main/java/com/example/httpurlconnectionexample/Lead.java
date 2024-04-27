package com.example.httpurlconnectionexample;

import static java.lang.String.valueOf;

import android.util.Log;

import androidx.annotation.NonNull;

public class Lead {
    @NonNull
    String id;
    final String source;
    final String status;
    final String reason_disqualified;
    final String type;
    final String vendor_id;
    // String linkedin;
    // String role;
    final String rating;
    final String company_id;


    public Lead(String id, String source, String status, String reason, String type, String vendorid, String rating, String companyid) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.reason_disqualified = reason;
        this.type = type;
        this.vendor_id = vendorid;
        this.rating = rating;
        this.company_id = companyid;
    }

    @NonNull
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (source != "null" && !source.isEmpty() || status != "null" && !status.isEmpty() || reason_disqualified != "null" && !reason_disqualified.isEmpty() || type != "null" && !type.isEmpty() || vendor_id != "null" && !vendor_id.isEmpty() || rating != "null" && !rating.isEmpty() || company_id != "null" && !company_id.isEmpty()) {
            sb.append(id).append(")");
            if (source != "null" && !source.isEmpty()) {
                sb.append("\nSource: '").append(source).append('\'');
            }
            if (status != "null" && !status.isEmpty()) {
                sb.append("\nStatus: '").append(status).append('\'');
            }
            if (reason_disqualified != "null" && !reason_disqualified.isEmpty()) {
                if (!reason_disqualified.equalsIgnoreCase("Not Disqualified")) {
                    sb.append("\nReason Disqualified: '").append(reason_disqualified).append('\'');
                }
            }
            if (type != "null" && !type.isEmpty()) {
                sb.append("\nLead Type: '").append(type).append('\'');
            }
            if (vendor_id != "null" && !vendor_id.isEmpty()) {
                sb.append("\nVendor ID: ").append(vendor_id);
            }
            if (rating != "null" && !rating.isEmpty()) {
                sb.append("\nRating: '").append(rating).append('\'');
            }
            if (company_id != "null" && !company_id.isEmpty()) {
                sb.append("\nCompany ID: ").append(company_id);
            }
        } else {
            sb.append(id).append(")\n").append("Empty Lead");
        }
        return sb.toString();
    }
}
