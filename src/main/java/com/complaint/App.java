
package com.complaint;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ComplaintDAO complaintDAO = new ComplaintDAO();

    public static void main(String[] args) {

        // Create database and table if they don't exist
        DatabaseConnection.initializeDatabase();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addComplaint();
                    break;

                case "2":
                    viewComplaints();
                    break;

                case "3":
                    searchComplaint();
                    break;

                case "4":
                    updateComplaint();
                    break;

                case "5":
                    deleteComplaint();
                    break;

                case "6":
                    System.out.println("\nThank you for using the Complaint Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // =========================
    // Display Menu
    // =========================
    private static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("     COMPLAINT MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Complaint");
        System.out.println("2. View All Complaints");
        System.out.println("3. Search Complaint");
        System.out.println("4. Update Complaint");
        System.out.println("5. Delete Complaint");
        System.out.println("6. Exit");
        System.out.println("========================================");
    }

    // =========================
    // CREATE
    // =========================
    private static void addComplaint() {

        System.out.println("\n---------- ADD COMPLAINT ----------");

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter complaint type: ");
        String complaintType = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter status: ");
        String status = scanner.nextLine();

        System.out.print("Enter complaint date (YYYY-MM-DD): ");
        String complaintDate = scanner.nextLine();

        Complaint complaint = new Complaint(
                customerName,
                email,
                complaintType,
                description,
                status,
                complaintDate
        );

        boolean result = complaintDAO.addComplaint(complaint);

        if (result) {
            System.out.println("\nComplaint added successfully!");
        } else {
            System.out.println("\nFailed to add complaint.");
        }
    }

    // =========================
    // READ - VIEW ALL
    // =========================
    private static void viewComplaints() {

        System.out.println("\n---------- ALL COMPLAINTS ----------");

        List<Complaint> complaints = complaintDAO.getAllComplaints();

        if (complaints.isEmpty()) {

            System.out.println("No complaints found.");

        } else {

            for (Complaint complaint : complaints) {

                System.out.println("----------------------------------------");
                System.out.println(complaint);
            }

            System.out.println("----------------------------------------");
        }
    }

    // =========================
    // READ - SEARCH
    // =========================
    private static void searchComplaint() {

        System.out.println("\n---------- SEARCH COMPLAINT ----------");

        System.out.print("Enter complaint ID: ");

        try {

            int complaintId = Integer.parseInt(scanner.nextLine());

            Complaint complaint =
                    complaintDAO.getComplaintById(complaintId);

            if (complaint != null) {

                System.out.println("\nComplaint found:");
                System.out.println("----------------------------------------");
                System.out.println(complaint);
                System.out.println("----------------------------------------");

            } else {

                System.out.println("\nComplaint not found.");
            }

        } catch (NumberFormatException e) {

            System.out.println("\nPlease enter a valid complaint ID.");
        }
    }

    // =========================
    // UPDATE
    // =========================
    private static void updateComplaint() {

        System.out.println("\n---------- UPDATE COMPLAINT ----------");

        try {

            System.out.print("Enter complaint ID to update: ");
            int complaintId = Integer.parseInt(scanner.nextLine());

            Complaint existingComplaint =
                    complaintDAO.getComplaintById(complaintId);

            if (existingComplaint == null) {

                System.out.println("\nComplaint not found.");
                return;
            }

            System.out.println("\nEnter new details:");

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter complaint type: ");
            String complaintType = scanner.nextLine();

            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter status: ");
            String status = scanner.nextLine();

            System.out.print("Enter complaint date (YYYY-MM-DD): ");
            String complaintDate = scanner.nextLine();

            Complaint complaint = new Complaint(
                    complaintId,
                    customerName,
                    email,
                    complaintType,
                    description,
                    status,
                    complaintDate
            );

            boolean result =
                    complaintDAO.updateComplaint(complaint);

            if (result) {
                System.out.println("\nComplaint updated successfully!");
            } else {
                System.out.println("\nFailed to update complaint.");
            }

        } catch (NumberFormatException e) {

            System.out.println("\nPlease enter a valid complaint ID.");
        }
    }

    // =========================
    // DELETE
    // =========================
    private static void deleteComplaint() {

        System.out.println("\n---------- DELETE COMPLAINT ----------");

        try {

            System.out.print("Enter complaint ID to delete: ");
            int complaintId = Integer.parseInt(scanner.nextLine());

            Complaint complaint =
                    complaintDAO.getComplaintById(complaintId);

            if (complaint == null) {

                System.out.println("\nComplaint not found.");
                return;
            }

            System.out.println("\nComplaint to be deleted:");
            System.out.println("----------------------------------------");
            System.out.println(complaint);
            System.out.println("----------------------------------------");

            System.out.print("Are you sure you want to delete it? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {

                boolean result =
                        complaintDAO.deleteComplaint(complaintId);

                if (result) {
                    System.out.println("\nComplaint deleted successfully!");
                } else {
                    System.out.println("\nFailed to delete complaint.");
                }

            } else {

                System.out.println("\nDelete operation cancelled.");
            }

        } catch (NumberFormatException e) {

            System.out.println("\nPlease enter a valid complaint ID.");
        }
    }
}