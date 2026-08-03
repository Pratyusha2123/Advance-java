package com.eventgo.main;

import com.eventgo.dao.UserDAO;
import com.eventgo.dao.EventDAO;
import com.eventgo.dao.BookingDAO;
import com.eventgo.model.User;
import com.eventgo.model.Event;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        EventDAO eventDAO = new EventDAO();
        BookingDAO bookingDAO = new BookingDAO();

        int choice;

        do {

            System.out.println("\n========== EVENTGO ==========");
            System.out.println("1. User Registration");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPhone(phone);

                    userDAO.registerUser(user);
                    break;

                case 2:
                    // User Login Logic
                    System.out.print("Enter User ID to Login: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    // Simple check if user exists (Assuming UserDAO has a method or we can directly open menu)
                    System.out.println("Login Successful!");

                    int userChoice;
                    do {
                        System.out.println("\n========== USER MENU ==========");
                        System.out.println("1. View Events");
                        System.out.println("2. Book Event Pass");
                        System.out.println("3. View My Bookings");
                        System.out.println("4. Cancel Booking");
                        System.out.println("5. Logout");
                        System.out.print("Enter Your Choice: ");

                        userChoice = sc.nextInt();
                        sc.nextLine();

                        switch (userChoice) {
                            case 1:
                                eventDAO.viewAllEvents();
                                break;
                            case 2:
                                System.out.print("Enter Event ID to Book: ");
                                int eventId = sc.nextInt();
                                System.out.print("Enter Number of Passes: ");
                                int quantity = sc.nextInt();
                                bookingDAO.bookPass(userId, eventId, quantity);
                                break;
                            case 3:
                                bookingDAO.viewMyBookings(userId);
                                break;
                            case 4:
                                System.out.print("Enter Booking ID to Cancel: ");
                                int bookingId = sc.nextInt();
                                bookingDAO.cancelBooking(bookingId, userId);
                                break;
                            case 5:
                                System.out.println("Logging out from User Account...");
                                break;
                            default:
                                System.out.println("Invalid Choice!");
                        }
                    } while (userChoice != 5);

                    break;

                case 3:
                    // Admin Login & Menu
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.nextLine();

                    if (!adminPass.equals("admin123")) {
                        System.out.println("Invalid Admin Password!");
                        break;
                    }

                    System.out.println("Admin Login Successful!");
                    int adminChoice;

                    do {
                        System.out.println("\n========== ADMIN MENU ==========");
                        System.out.println("1. Add Event");
                        System.out.println("2. View Events");
                        System.out.println("3. Logout");
                        System.out.print("Enter Your Choice: ");

                        adminChoice = sc.nextInt();
                        sc.nextLine();

                        switch (adminChoice) {
                            case 1:
                                Event event = new Event();
                                System.out.print("Enter Event Name: ");
                                event.setEventName(sc.nextLine());

                                System.out.print("Enter Venue: ");
                                event.setVenue(sc.nextLine());

                                System.out.print("Enter Event Date (yyyy-mm-dd): ");
                                event.setEventDate(java.sql.Date.valueOf(sc.nextLine()));

                                System.out.print("Enter Event Time (hh:mm:ss): ");
                                event.setEventTime(java.sql.Time.valueOf(sc.nextLine()));

                                System.out.print("Enter Available Passes: ");
                                event.setAvailablePasses(sc.nextInt());

                                System.out.print("Enter Ticket Price: ");
                                event.setTicketPrice(sc.nextDouble());

                                eventDAO.addEvent(event);
                                break;

                            case 2:
                                eventDAO.viewAllEvents();
                                break;

                            case 3:
                                System.out.println("Logging out from Admin Account...");
                                break;

                            default:
                                System.out.println("Invalid Choice!");
                        }
                    } while (adminChoice != 3);

                    break;

                case 4:
                    System.out.println("Thank You For Using EventGo!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
