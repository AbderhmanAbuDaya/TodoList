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
     ListItemClickListener mListItemClickListener;

    List<TaskItem> tasks;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, count;
        ListItemClickListener listItemClickListener;

        public ViewHolder(View view, ListItemClickListener listItemClickListener) {
            super(view);
            this.listItemClickListener=listItemClickListener;
            title = (TextView) view.findViewById(R.id.taskName);
            count = (TextView) view.findViewById(R.id.taskNumber);
            view.setOnClickListener(this);
        }

        public void setData(TaskItem task) {
            title.setText(task.getTitle());
            count.setText("" + task.getCount());
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
    public TaskAdapterEx(List<TaskItem> tasks,ListItemClickListener listItemClickListener) {
        this.tasks = tasks;
        this.mListItemClickListener = listItemClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_item, viewGroup, false);

        return new ViewHolder(view,mListItemClickListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        viewHolder.setData(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
    interface ListItemClickListener {
        void onListItemClick(int position);
    }
}
