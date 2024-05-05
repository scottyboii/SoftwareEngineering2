package com.example.httpurlconnectionexample;

import androidx.annotation.NonNull;

/**
 * Represents a lead with various properties.
 */
public class Lead {
    @NonNull
    private final String id;
    private final String source;
    private final String status;
    private final String reasonDisqualified;
    private final String type;
    private final String vendorId;
    private final String rating;
    private final String companyId;

    public Lead(@NonNull String id, String source, String status, String reasonDisqualified, String type, String vendorId, String rating, String companyId) {
        this.id = id;
        this.source = source;
        this.status = status;
        this.reasonDisqualified = reasonDisqualified;
        this.type = type;
        this.vendorId = vendorId;
        this.rating = rating;
        this.companyId = companyId;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (hasAnyNonNullOrNonEmptyField()) {
            sb.append(id).append(")");
            appendFieldIfNotNullOrEmpty(sb, "Source", source);
            appendFieldIfNotNullOrEmpty(sb, "Status", status);
            appendReasonDisqualifiedIfNotNullOrEmpty(sb);
            appendFieldIfNotNullOrEmpty(sb, "Lead Type", type);
            appendFieldIfNotNullOrEmpty(sb, "Vendor ID", vendorId);
            appendFieldIfNotNullOrEmpty(sb, "Rating", rating);
            appendFieldIfNotNullOrEmpty(sb, "Company ID", companyId);
        } else {
            sb.append(id).append(")\nEmpty Lead");
        }

        return sb.toString();
    }

    private boolean hasAnyNonNullOrNonEmptyField() {
        return !isNullOrEmpty(source) || !isNullOrEmpty(status) || !isNullOrEmpty(reasonDisqualified) || !isNullOrEmpty(type) || !isNullOrEmpty(vendorId) || !isNullOrEmpty(rating) || !isNullOrEmpty(companyId);
    }

    private void appendReasonDisqualifiedIfNotNullOrEmpty(StringBuilder sb) {
        if (!isNullOrEmpty(reasonDisqualified) && !"Not Disqualified".equalsIgnoreCase(reasonDisqualified)) {
            sb.append("\nReason Disqualified: '").append(reasonDisqualified).append('\'');
        }
    }

    private void appendFieldIfNotNullOrEmpty(StringBuilder sb, String fieldName, String fieldValue) {
        if (!isNullOrEmpty(fieldValue)) {
            sb.append("\n").append(fieldName).append(": '").append(fieldValue).append('\'');
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty() || "null".equalsIgnoreCase(value);
    }
}