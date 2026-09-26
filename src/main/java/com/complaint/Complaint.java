package com.complaint;

public class Complaint {

    private int complaintId;
    private String customerName;
    private String email;
    private String complaintType;
    private String description;
    private String status;
    private String complaintDate;

    // Default constructor
    public Complaint() {
    }

    // Constructor for adding a new complaint
    public Complaint(String customerName, String email,
                     String complaintType, String description,
                     String status, String complaintDate) {

        this.customerName = customerName;
        this.email = email;
        this.complaintType = complaintType;
        this.description = description;
        this.status = status;
        this.complaintDate = complaintDate;
    }

    // Constructor with complaint ID
    public Complaint(int complaintId, String customerName, String email,
                     String complaintType, String description,
                     String status, String complaintDate) {

        this.complaintId = complaintId;
        this.customerName = customerName;
        this.email = email;
        this.complaintType = complaintType;
        this.description = description;
        this.status = status;
        this.complaintDate = complaintDate;
    }

    // Getters and Setters

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    @Override
    public String toString() {
        return "Complaint ID: " + complaintId +
                "\nCustomer Name: " + customerName +
                "\nEmail: " + email +
                "\nComplaint Type: " + complaintType +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nDate: " + complaintDate;
    }
}