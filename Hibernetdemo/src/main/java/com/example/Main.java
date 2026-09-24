package com.example;

import Entity.Student;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction et = em.getTransaction();

    public static void main(String[] args) {

//        Student s1=new Student(1,"shriyanshi", "prishu");
//        et.begin();
//        em.persist(s1);
//        em.merge(s1);
//        Student student=em.find(Student.class, 3);
//        System.out.println(student);
//        em.remove(s1);
//        et.commit();

        int choice = 0;

        do {
            System.out.println("1: Add Student ");
            System.out.println("2: Update Student");
            System.out.println("3: Get student by id");
            System.out.println("4: Delete Student");
            System.out.println("5: Show all Students");
            System.out.println("6: Show students whose name start with perticular character");
            System.out.println("0: Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    findById();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    showAllStudents();
                    break;
                case 6:
                    nameStrtsWith();
                    break;

            }

        }while(choice!=0);
    }

    static void addStudent() {
        System.out.println("Enter Student Details ");
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        Student s1 = new Student(id, name, course);

        et.begin();
        em.persist(s1);
        et.commit();
        System.out.println("Student Added Successfully");
    }

    static void updateStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Student s1 = em.find(Student.class, id);

        if (s1 != null) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            s1.setName(name);
            s1.setCourse(course);

            et.begin();
            em.merge(s1);
            et.commit();

            System.out.println("Student Updated Successfully");
        } else {
            System.out.println("Student Not Found");
        }

    }

    static void findById() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        Student student = em.find(Student.class, id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found");
        }
    }

    static void deleteStudent(){
        System.out.print("Enter ID: ");
        int id= sc.nextInt();
        Student student = em.find(Student.class, id);

        if(student!=null) {
            EntityTransaction et = em.getTransaction();
            et.begin();
            em.remove(student);
            et.commit();
            System.out.println("Student Deleted Successfully");
        } else {
            System.out.println("Student Not Found");
        }
    }

    static void showAllStudents() {
        Query query = em.createQuery("select s from Student s");
        List<Student>stdlist = query.getResultList();
        for (Student student : stdlist) {
            System.out.println(student);
        }
    }
    static void nameStrtsWith(){
        Query qry = em.createQuery("select s from Student s where s.name LIKE :match");
        qry.setParameter("match", "p%");
        List<Student> stdlist2 = qry.getResultList();
        for (Student student : stdlist2) {
            System.out.println(student);
        }
    }
}
