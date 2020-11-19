package com.model;

public class Category {
    private int Id;
    private String name;
    private boolean deleted;

    public Category(int id, String name, boolean deleted) {
        Id = id;
        this.name = name;
        this.deleted = deleted;
    }
    public Category(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
