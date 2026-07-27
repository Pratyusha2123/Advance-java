package com.demoemployee;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        int choice;

        do {

            System.out.println("\n========== Employee Management System ==========");
            System.out.println("1. Register Employee");
            System.out.println("2. Get Employee By ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice : ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    scanner.nextLine();

                    System.out.print("Enter Employee Name : ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Department : ");
                    String department = scanner.nextLine();

                    System.out.print("Enter Salary : ");
                    double salary = scanner.nextDouble();

                    dao.registerEmployee(name, department, salary);

                    break;

                case 2:

                    System.out.print("Enter Employee ID : ");
                    int id = scanner.nextInt();

                    dao.getEmployeeById(id);

                    break;

                case 3:

                    dao.getAllEmployees();

                    break;

                case 4:

                    System.out.print("Enter Employee ID : ");
                    int updateId = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter New Name : ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter New Department : ");
                    String newDepartment = scanner.nextLine();

                    System.out.print("Enter New Salary : ");
                    double newSalary = scanner.nextDouble();

                    dao.updateEmployee(updateId, newName, newDepartment, newSalary);

                    break;

                case 5:

                    System.out.print("Enter Employee ID : ");
                    int deleteId = scanner.nextInt();

                    dao.deleteEmployee(deleteId);

                    break;

                case 6:

                    System.out.println("Thank You...");
                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        } while (choice != 6);

        scanner.close();
    }
}
