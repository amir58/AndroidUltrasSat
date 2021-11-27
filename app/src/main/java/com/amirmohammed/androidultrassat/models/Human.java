package com.amirmohammed.androidultrassat.models;

public class Human {
    // global variables
    private String name;
    private int age;
    private int weight;
    private int height;

    public Human(String name, int age, int weight, int height) { // local variables
        this.name = name;
        this.weight = weight;
        this.height = height;
        setAge(age);
    }

    public void printData() {
        System.out.println(name);
        System.out.println(age);
        System.out.println(weight);
        System.out.println(height);
    }

    // modifiers returnType funName ( parameters ) { }
    // Access modifiers => public , private , protected
    // , package private
    // Non access modifiers => final , static , abstract
    // return type => void , int , String , double , boolean , long , Class
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 6) {
            System.out.println("Age wrong");
            throw new RuntimeException("Age in Human class must be bigger than 18");
        } else {
            this.age = age;
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return this.name;
    }


}
