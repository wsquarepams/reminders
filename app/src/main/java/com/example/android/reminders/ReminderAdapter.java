package com.example.android.reminders;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.MyViewHolder> {

        private List<Reminder> reminderList;
        private RecyclerViewOnClickListener recyclerViewOnClickListener;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title, description;
            public MyViewHolder myViewHolder = this;

            public MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                description = view.findViewById(R.id.description);
                //year = (TextView) view.findViewById(R.id.year);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = myViewHolder.getAdapterPosition();
                        recyclerViewOnClickListener.onItemClicked(i);
                    }
                });
            }


//            @Override
//            public void onClick(View v) {
//                int i = this.getAdapterPosition();
//            }
        }


        public ReminderAdapter(List<Reminder> reminderList) {
            this.reminderList = reminderList;
        }

        public void setOnClickListener(RecyclerViewOnClickListener onClickListener) {
            recyclerViewOnClickListener = onClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.reminder_list_row, parent, false);

            return new MyViewHolder(itemView);
        }

//        @Override
//        public void onBindViewHolder(@NonNull ReminderAdapter.MyViewHolder myViewHolder, int i) {
//
//        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Reminder reminder = reminderList.get(position);
            holder.title.setText(reminder.getName());
            holder.description.setText(reminder.getDescription());
            //holder.year.setText(reminder.getYear());
        }

        @Override
        public int getItemCount() {
            return reminderList.size();
        }
    }
