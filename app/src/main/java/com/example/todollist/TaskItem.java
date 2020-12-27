package com.example.todollist;



public class TaskItem {
    String Title;

    int count;

    public TaskItem() {
    }

    public TaskItem(int count,String title) {
        this.Title = title;
        this.count=count;
    }

    public int getCount() {
        return count;
    }

    public void setCount( String id) {
        this.count = count;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }




}