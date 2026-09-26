package com.complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    // =========================
    // CREATE - Add Complaint
    // =========================
    public boolean addComplaint(Complaint complaint) {

        String sql = "INSERT INTO complaints " +
                "(customer_name, email, complaint_type, description, status, complaint_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, complaint.getCustomerName());
            statement.setString(2, complaint.getEmail());
            statement.setString(3, complaint.getComplaintType());
            statement.setString(4, complaint.getDescription());
            statement.setString(5, complaint.getStatus());
            statement.setDate(6, Date.valueOf(complaint.getComplaintDate()));

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error adding complaint.");
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // READ - View All Complaints
    // =========================
    public List<Complaint> getAllComplaints() {

        List<Complaint> complaints = new ArrayList<>();

        String sql = "SELECT * FROM complaints";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Complaint complaint = new Complaint();

                complaint.setComplaintId(
                        resultSet.getInt("complaint_id"));

                complaint.setCustomerName(
                        resultSet.getString("customer_name"));

                complaint.setEmail(
                        resultSet.getString("email"));

                complaint.setComplaintType(
                        resultSet.getString("complaint_type"));

                complaint.setDescription(
                        resultSet.getString("description"));

                complaint.setStatus(
                        resultSet.getString("status"));

                complaint.setComplaintDate(
                        resultSet.getDate("complaint_date").toString());

                complaints.add(complaint);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving complaints.");
            e.printStackTrace();
        }

        return complaints;
    }

    // =========================
    // READ - Search Complaint
    // =========================
    public Complaint getComplaintById(int complaintId) {

        String sql = "SELECT * FROM complaints WHERE complaint_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, complaintId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Complaint complaint = new Complaint();

                    complaint.setComplaintId(
                            resultSet.getInt("complaint_id"));

                    complaint.setCustomerName(
                            resultSet.getString("customer_name"));

                    complaint.setEmail(
                            resultSet.getString("email"));

                    complaint.setComplaintType(
                            resultSet.getString("complaint_type"));

                    complaint.setDescription(
                            resultSet.getString("description"));

                    complaint.setStatus(
                            resultSet.getString("status"));

                    complaint.setComplaintDate(
                            resultSet.getDate("complaint_date").toString());

                    return complaint;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching for complaint.");
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // UPDATE - Update Complaint
    // =========================
    public boolean updateComplaint(Complaint complaint) {

        String sql = "UPDATE complaints SET " +
                "customer_name = ?, " +
                "email = ?, " +
                "complaint_type = ?, " +
                "description = ?, " +
                "status = ?, " +
                "complaint_date = ? " +
                "WHERE complaint_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, complaint.getCustomerName());
            statement.setString(2, complaint.getEmail());
            statement.setString(3, complaint.getComplaintType());
            statement.setString(4, complaint.getDescription());
            statement.setString(5, complaint.getStatus());
            statement.setDate(6, Date.valueOf(complaint.getComplaintDate()));
            statement.setInt(7, complaint.getComplaintId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating complaint.");
            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE - Delete Complaint
    // =========================
    public boolean deleteComplaint(int complaintId) {

        String sql = "DELETE FROM complaints WHERE complaint_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, complaintId);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting complaint.");
            e.printStackTrace();
            return false;
        }
    }
}