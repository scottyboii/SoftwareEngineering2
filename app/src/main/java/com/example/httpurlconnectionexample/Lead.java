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
        final StringBuilder sb = new StringBuilder("Lead{");
        sb.append("id='").append(id).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", reason='").append(reason_disqualified).append('\'');
        sb.append(", typeoflead='").append(type).append('\'');
        sb.append(", vendorid='").append(vendor_id).append('\'');
        sb.append(", rating='").append(rating).append('\'');
        sb.append(", companyid='").append(company_id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
