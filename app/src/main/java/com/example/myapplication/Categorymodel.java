package com.example.myapplication;

public class Categorymodel {

    private String course_name;
    private String offer;
    private int course_image;

    // Constructor
    public Categorymodel(String course_name, String offer, int course_image) {
        this.course_name = course_name;
        this.offer = offer;
        this.course_image = course_image;
    }

    // Getter and Setter
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_rating() {
        return offer;
    }

    public void setCourse_rating(String course_rating) {
        this.offer = course_rating;
    }

    public int getCourse_image() {
        return course_image;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }
}

