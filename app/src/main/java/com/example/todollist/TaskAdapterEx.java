package com.example.todollist;
import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CheckBox;
        import android.widget.TextView;

        import androidx.recyclerview.widget.RecyclerView;
        import androidx.annotation.NonNull;
        import java.util.List;

public class TaskAdapterEx extends RecyclerView.Adapter<TaskAdapterEx.ViewHolder> {

    List<TaskItem> tasks;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, count;

        public ViewHolder(View view) {
            super(view);


            title = (TextView) view.findViewById(R.id.taskName);
            count = (TextView) view.findViewById(R.id.taskNumber);
        }

        public void setData(TaskItem task) {
            title.setText(task.getTitle());
            count.setText("" + task.getCount());
        }
    }

    public TaskAdapterEx(List<TaskItem> tasks) {
        this.tasks = tasks;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_item, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        viewHolder.setData(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}