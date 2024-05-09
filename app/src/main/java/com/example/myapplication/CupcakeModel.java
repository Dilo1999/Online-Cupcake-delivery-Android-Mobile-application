package com.example.myapplication;

public class CupcakeModel {

    private String course_name;
    private String course_rating;

    private String offer;

    private String price;
    private int course_image;



    // Constructor
    public CupcakeModel(String course_name, String course_rating, int course_image, String offer, String price) {
        this.course_name = course_name;
        this.course_rating = course_rating;
        this.course_image = course_image;
        this.offer=offer;
        this.price=price;
    }

    // Getter and Setter
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_rating() {
        return course_rating;
    }

    public String get_offer(){
        return offer;
    }

    public String get_price(){
        return price;
    }

    public void setCourse_rating(String course_rating) {
        this.course_rating = course_rating;
    }

    public int getCourse_image() {
        return course_image;
    }

    public void setCourse_image(int course_image) {
        this.course_image = course_image;
    }
}

