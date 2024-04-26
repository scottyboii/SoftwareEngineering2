package com.example.httpurlconnectionexample;

import androidx.annotation.NonNull;

public class Lead {
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
        sb.append(id).append(")\n");
        sb.append("Source: '").append(source).append('\'');
        sb.append("\nStatus: '").append(status).append('\'');
        sb.append("\nReason Disqualified: '").append(reason_disqualified).append('\'');
        sb.append("\nLead Type: '").append(type).append('\'');
        sb.append("\nVendor ID: ").append(vendor_id);
        sb.append("\nRating: '").append(rating).append('\'');
        sb.append("\nCompany ID: ").append(company_id);
        return sb.toString();
    }
}
