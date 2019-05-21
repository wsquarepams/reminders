package com.example.android.reminders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.CardView;
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
            public TextView title, description, locationName, alertTime;
            public CardView cardView;
            private MyViewHolder myViewHolder = this;


            private MyViewHolder(View view) {
                super(view);
                title = view.findViewById(R.id.title);
                description = view.findViewById(R.id.description);
                locationName = view.findViewById(R.id.locationName);
                alertTime = view.findViewById(R.id.alertTime);
                cardView = (CardView) view;

                //year = (TextView) view.findViewById(R.id.year);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerViewOnClickListener.onItemClicked(myViewHolder.getAdapterPosition());
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

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Reminder reminder = reminderList.get(position);

            if ((position % 2) == 0) {
                (holder.cardView).setCardBackgroundColor(MainActivity.evenColor);
            } else {
                (holder.cardView).setCardBackgroundColor(MainActivity.oddColor);
            }
            holder.title.setText(reminder.getName());
            holder.description.setText("Description: " + reminder.getDescription());
            holder.locationName.setText("Location: " + reminder.getLocationName());
            if (reminder.getTimeBased()) {
                holder.alertTime.setText("Alert time:" + reminder.getStartTimestamp() + " to " + reminder.getEndTimestamp());
            } else {
                holder.alertTime.setText("No Alert Time.");
            }
        }

        @Override
        public int getItemCount() {
            return reminderList.size();
        }
    }
