package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "student2")
public class Student {

    @Id
    private Long id;

    @Column(name = "firaname")
    private String firaName;

    @Column(name = "secondname")
    private String secondName;

    @Column(name = "age")
    private Integer age;


    public Student(){

    }

    public Student(Long id, String firaName, String secondName, Integer age) {
        this.id = id;
        this.firaName = firaName;
        this.secondName = secondName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiraName() {
        return firaName;
    }

    public void setFiraName(String firaName) {
        this.firaName = firaName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firaName='" + firaName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                '}';
    }
}
