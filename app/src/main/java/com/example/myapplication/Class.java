package com.example.myapplication;

public class Class {
    private int ID;
    private String className;

    public Class(){}


    public Class(int ID,String className){
        this.ID =ID;
        this.className = className;
    }
    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID=ID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
