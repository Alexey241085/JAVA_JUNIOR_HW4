package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class DbHiber {

//    public static void main(String[] args) throws SQLException {
//
//        Student studentNew1 = new Student(1L, "Alexey", "Ivashin", 38);
//        Student studentNew2 = new Student(2L, "Petr", "Petrov", 24);
//        Student studentNew3 = new Student(3L, "Alexandr", "Ovechkin", 39);
//        Student studentNew4 = new Student(4L, "Olga", "Sidorova", 27);
//
//        createTable();
//
//        studentPersist2(studentNew1);
//        studentPersist2(studentNew2);
//        studentPersist2(studentNew3);
//        studentPersist2(studentNew4);
//
//        printTablw();
//
//        findStudentById(2L);
//        mergeStudentById(1L, 2L);
//
//        printTablw();
//
//        removeStudentById(3L);
//
//        printTablw();
//
//
//    }

    public static  void createTable() throws SQLException {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "berkut1985"); Statement st = con.createStatement()){
                st.execute("DROP TABLE IF EXISTS `homework3`.`student2`");
                st.execute("""
                 CREATE TABLE`homework3`.`student2`
                 (`id` INT NOT NULL DEFAULT  0,
                 `firaname` VARCHAR(30) NULL,
                 `secondname` VARCHAR(30) NULL,
                 `age` INT,
                 PRIMARY KEY (`id`));""");
            }
        }
    }


    public static void studentPersist2(Student student){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){

            student.setId(student.getId());
            student.setFiraName(student.getFiraName());
            student.setSecondName(student.getSecondName());
            student.setAge(student.getAge());

            try (Session session = sessionFactory.openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(student);
                transaction.commit();
            }
        }
    }


    public static void findStudentById(Long idStudent){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try (Session session = sessionFactory.openSession()){
                Student student = session.find(Student.class, idStudent);
                System.out.println(student);
            }
        }
    }

    public static void mergeStudentById(Long idStudent1, Long idStudent2){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try (Session session = sessionFactory.openSession()){
                Student student1 = session.find(Student.class, idStudent1);
                Student student2 = session.find(Student.class, idStudent2);
                System.out.println(student1);
                System.out.println(student2);

                Transaction transaction = session.beginTransaction();
                student1.setAge(student2.getAge());
                student1.setFiraName("AAAA");
                session.merge(student1);
                transaction.commit();
            }
            try (Session session = sessionFactory.openSession()){
                Student student1 = session.find(Student.class, idStudent1);
                System.out.println(student1);
            }
        }
    }


    public static void removeStudentById(Long idStudent){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try (Session session = sessionFactory.openSession()){
                Student student1 = session.find(Student.class, idStudent);

                System.out.println(student1);

                Transaction transaction = session.beginTransaction();
                session.remove(student1);
                transaction.commit();
            }
        }
    }


    public static void printTablw() throws SQLException {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "berkut1985"); Statement st = con.createStatement()){
                ResultSet resultSet = st.executeQuery("SELECT * FROM `homework3`.`student2`");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firaName = resultSet.getNString("firaname");
                    String secondName = resultSet.getNString("secondname");
                    int age = resultSet.getInt("age");

                    System.out.println("id = " + id + ", firaName " + firaName + ", secondName " + secondName + ", age " + age);
                }
            }
        }
    }
}

