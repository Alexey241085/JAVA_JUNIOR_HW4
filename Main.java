package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;



public class Main {
    public static void main(String[] args) throws SQLException {

        Student studentNew1 = new Student(1L, "Alexey", "Ivashin", 38);
        Student studentNew2 = new Student(2L, "Petr", "Petrov", 24);
        Student studentNew3 = new Student(3L, "Alexandr", "Ovechkin", 39);
        Student studentNew4 = new Student(4L, "Olga", "Sidorova", 27);
        Student studentNew5 = new Student(5L, "Vera", "Vasina", 31);
        Student studentNew6 = new Student(6L, "Maria", "Orlova", 46);



        DbHiber.createTable();

        DbHiber.studentPersist2(studentNew1);
        DbHiber.studentPersist2(studentNew2);
        DbHiber.studentPersist2(studentNew3);
        DbHiber.studentPersist2(studentNew4);
        DbHiber.studentPersist2(studentNew5);
        DbHiber.studentPersist2(studentNew6);

        DbHiber.printTablw();

        DbHiber.findStudentById(2L);
        DbHiber. mergeStudentById(1L, 2L);
        DbHiber.printTablw();

        DbHiber.removeStudentById(3L);
        DbHiber.printTablw();

        search();



    }
    public static void search(){
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try (Session session = sessionFactory.openSession()){

               Query<Student> query =  session.createQuery("from Student where age > 25");
//                Query<Student> query = session.createQuery("select * from student2 where 'age' > 25", Student.class);
                List<Student> studentList = query.getResultList();
                studentList.stream().forEach(System.out::println);
            }
        }
    }
}