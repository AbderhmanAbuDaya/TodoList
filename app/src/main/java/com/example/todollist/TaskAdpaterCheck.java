package com.example.todollist;
import android.content.Context;
        import android.graphics.Paint;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;
        import androidx.annotation.NonNull;
        import java.util.List;

public class TaskAdpaterCheck extends RecyclerView.Adapter<TaskAdpaterCheck.ViewHolder> {

    private final Context context;
    List<TaskCheck> tasks;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            checkBox = itemView.findViewById(R.id.CheckB1);


        }

        public void setData(final TaskCheck task) {
            // title.setText(task.getTitle());
            // isChecked.setText(task.getIsChecked());
            checkBox.setText(task.getTitle());
            checkBox.setSelected(task.getIsChecked());
        }
    }

    public TaskAdpaterCheck(Context context, List<TaskCheck> tasks) {
        this.context = context;
        this.tasks = tasks;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_task_check, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.setData(tasks.get(position));
        TaskCheck taskEntity = tasks.get(position);
        if(taskEntity.getIsChecked()){
            holder.checkBox.setChecked(true);
            holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                taskEntity.setIsChecked(isChecked);
                holder.checkBox.setSelected(isChecked);
                if(isChecked){
                    holder.checkBox.setText(taskEntity.getTitle());
                    holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    holder.checkBox.setText(taskEntity.getTitle());
                    holder.checkBox.setPaintFlags( holder.checkBox.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }
}