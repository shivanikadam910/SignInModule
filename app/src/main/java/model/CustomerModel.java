package model;

import java.io.Serializable;

public class CustomerModel implements Serializable {
    private String name;
    private int age;
    private int id;
    private boolean isActive;

    public CustomerModel( int id, String name, int age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", isActive=" + isActive +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
