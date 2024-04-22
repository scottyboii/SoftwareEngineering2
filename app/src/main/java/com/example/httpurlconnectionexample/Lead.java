package com.example.httpurlconnectionexample;

public class Lead {
    String id, source, status, reason_disqualified, type, vendor_id, linkedin, role, rating, company_id;


    public Lead(String id, String source, String status, String type) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lead{");
        sb.append("id='").append(id).append('\'');
        sb.append(", source='").append(source).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
