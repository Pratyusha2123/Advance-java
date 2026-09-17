package com.example;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("HND");

        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();

        Student s1 = new Student(2, "Amrita", "DSA");

        et.begin();

        em.persist(s1);

        et.commit();

        em.close();
        emf.close();
    }
}
