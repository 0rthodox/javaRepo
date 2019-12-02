package ru.mipt.hometask.entities;

public class Person {
    private static int currentId;
    private static int numOfFields = 3;

    public static int getNumOfFields() {
        return numOfFields;
    }

    protected int id;
    protected String name;
    protected String surname;

    static {
        currentId = 1;
    }

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname) {
        this.id = getCurrentId();
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static int getCurrentId() {
        return currentId++;
    }
}
