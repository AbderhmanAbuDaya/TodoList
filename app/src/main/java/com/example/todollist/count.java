package com.example.todollist;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class count {

    String categoryId, taskId, uid;
    int readCount;
    boolean flag = true;
    public count(String uid, String categoryId, String taskId) {
        this.uid = uid;
        this.categoryId = categoryId;
        this.taskId = taskId;
    }

    public void setListsCount(String string){
        FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            TaskItem categoryItem = snapshot.getValue(TaskItem.class);
                            if(categoryItem.getId().compareTo(categoryId) == 0 && flag){
                                readCount = categoryItem.getCount();
                                if(string.compareTo("Add") == 0){
                                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("count").setValue(++readCount);
                                } else if(string.compareTo("sub") == 0) {
                                    FirebaseDatabase.getInstance().getReference("Users").child(uid).child("category").child(categoryId).child("count").setValue(--readCount);
                                }
                                flag = false;
                                break;
                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                    }
                });

    }

}
