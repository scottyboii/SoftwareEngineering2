package com.example.httpurlconnectionexample;

import androidx.annotation.NonNull;


/**
 * Lead Class
 * Strings id, source, status, reason_disqualified, type, vendor_id, rating, company_id
 * This is just the main Lead class with all of the constructors and the toString for the lead class.
 */
public class Lead {
    // Lead Properties
    @NonNull
    final String id;
    final String source;
    final String status;
    final String reason_disqualified;
    final String type;
    final String vendor_id;
    final String rating;
    final String company_id;

    // Lead Constructor
    public Lead(@NonNull String id, String source, String status, String reason, String type, String vendorid, String rating, String companyid) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.reason_disqualified = reason;
        this.type = type;
        this.vendor_id = vendorid;
        this.rating = rating;
        this.company_id = companyid;
    }

    // Lead toString, with logic to only include the relevant parts of the string. Will return "empty lead" if no field is set.
    @NonNull
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (!source.equalsIgnoreCase("null") && !source.isEmpty() || !status.equalsIgnoreCase("null") && !status.isEmpty() || !reason_disqualified.equalsIgnoreCase("null") && !reason_disqualified.isEmpty() || !type.equalsIgnoreCase("null") && !type.isEmpty() || !vendor_id.equalsIgnoreCase("null") && !vendor_id.isEmpty() || !rating.equalsIgnoreCase("null") && !rating.isEmpty() || !company_id.equalsIgnoreCase("null") && !company_id.isEmpty()) {
            sb.append(id).append(")");
            if (!source.equalsIgnoreCase("null") && !source.isEmpty()) {
                sb.append("\nSource: '").append(source).append('\'');
            }
            if (!status.equalsIgnoreCase("null") && !status.isEmpty()) {
                sb.append("\nStatus: '").append(status).append('\'');
            }
            if (!reason_disqualified.equalsIgnoreCase("null") && !reason_disqualified.isEmpty()) {
                if (!reason_disqualified.equalsIgnoreCase("Not Disqualified")) {
                    sb.append("\nReason Disqualified: '").append(reason_disqualified).append('\'');
                }
            }
            if (!type.equalsIgnoreCase("null") && !type.isEmpty()) {
                sb.append("\nLead Type: '").append(type).append('\'');
            }
            if (!vendor_id.equalsIgnoreCase("null") && !vendor_id.isEmpty()) {
                sb.append("\nVendor ID: ").append(vendor_id);
            }
            if (!rating.equalsIgnoreCase("null") && !rating.isEmpty()) {
                sb.append("\nRating: '").append(rating).append('\'');
            }
            if (!company_id.equals("null") && !company_id.isEmpty()) {
                sb.append("\nCompany ID: ").append(company_id);
            }
        } else {
            sb.append(id).append(")\n").append("Empty Lead");
        }
        return sb.toString();
    }
}
