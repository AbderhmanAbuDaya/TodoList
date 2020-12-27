package com.example.todollist;


public class TaskCheck {
    String Title;
    Boolean isChecked;
    String id;

    public  TaskCheck() {
    }

    public TaskCheck(String id,String title, Boolean isChecked) {
        this.Title = title;
        this.isChecked = isChecked;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
}