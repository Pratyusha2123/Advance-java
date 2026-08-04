package com.bloodcamp;

import com.bloodcamp.dao.DonorDAO;
import com.bloodcamp.dao.SlotDAO;
import com.bloodcamp.model.Donor;
import com.bloodcamp.model.Slot;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DonorDAO donorDAO = new DonorDAO();
        SlotDAO slotDAO = new SlotDAO();

        while (true) {

            System.out.println("\n========= Blood Donation Camp =========");
            System.out.println("1. Register Donor");
            System.out.println("2. View Donors");
            System.out.println("3. Update Donor");
            System.out.println("4. Delete Donor");
            System.out.println("5. Book Donation Slot");
            System.out.println("6. Cancel Slot");
            System.out.println("7. Batch Donor Registration");
            System.out.println("8. Exit");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    Donor donor = new Donor();

                    System.out.print("Enter Name : ");
                    donor.setName(sc.nextLine());

                    System.out.print("Enter Age : ");
                    donor.setAge(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter Blood Group : ");
                    donor.setBloodGroup(sc.nextLine());

                    System.out.print("Enter Phone : ");
                    donor.setPhone(sc.nextLine());

                    donorDAO.registerDonor(donor);

                    break;

                case 2:

                    donorDAO.viewDonors();

                    break;

                case 3:

                    Donor update = new Donor();

                    System.out.print("Enter Donor ID : ");
                    update.setDonorId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter New Name : ");
                    update.setName(sc.nextLine());

                    System.out.print("Enter New Age : ");
                    update.setAge(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter New Blood Group : ");
                    update.setBloodGroup(sc.nextLine());

                    System.out.print("Enter New Phone : ");
                    update.setPhone(sc.nextLine());

                    donorDAO.updateDonor(update);

                    break;

                case 4:

                    System.out.print("Enter Donor ID : ");

                    int id = sc.nextInt();

                    donorDAO.deleteDonor(id);

                    break;
                case 5:

                    Slot slot = new Slot();

                    System.out.print("Enter Donor ID : ");
                    slot.setDonorId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter Donation Date (YYYY-MM-DD) : ");
                    slot.setDonationDate(sc.nextLine());

                    System.out.print("Enter Donation Time : ");
                    slot.setDonationTime(sc.nextLine());

                    slotDAO.bookSlot(slot);

                    break;

                case 6:

                    System.out.print("Enter Booking ID : ");
                    int bookingId = sc.nextInt();

                    slotDAO.cancelSlot(bookingId);

                    break;

                case 7:

                    System.out.print("How Many Donors : ");
                    int n = sc.nextInt();
                    sc.nextLine();

                    Donor[] donors = new Donor[n];

                    for (int i = 0; i < n; i++) {

                        donors[i] = new Donor();

                        System.out.println("\nDonor " + (i + 1));

                        System.out.print("Name : ");
                        donors[i].setName(sc.nextLine());

                        System.out.print("Age : ");
                        donors[i].setAge(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Blood Group : ");
                        donors[i].setBloodGroup(sc.nextLine());

                        System.out.print("Phone : ");
                        donors[i].setPhone(sc.nextLine());

                    }

                    donorDAO.batchRegisterDonors(donors);

                    break;

                case 8:

                    System.out.println("Thank You!");
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice.");
            }

        }

    }

}