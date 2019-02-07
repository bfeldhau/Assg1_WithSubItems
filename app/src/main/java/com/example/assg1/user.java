package com.example.assg1;

public class user {
    public String task;
    public String description;
    private boolean checked = false;

    public boolean isChecked(){
        return checked;
    }

    public void toggleChecked(){
        checked = !checked;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }
    public user(String task, String description){
        this.task =  task;
        this.description = description;
    }
}