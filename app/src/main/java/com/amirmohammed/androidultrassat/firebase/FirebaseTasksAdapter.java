package com.amirmohammed.androidultrassat.firebase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.androidultrassat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseTasksAdapter extends RecyclerView.Adapter<FirebaseTasksAdapter.Holder>{

    FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    List<Task> tasks;

    public FirebaseTasksAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Task task = tasks.get(position);

        holder.textViewTitle.setText(task.getTitle());
        holder.textViewDate.setText(task.getDate());
        holder.textViewTime.setText(task.getTime());

        holder.imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", "done");

                firestore.collection("roomOneUsers").document(firebaseAuth.getUid())
                        .collection("myTasks").document(task.getId()).update(map);

                tasks.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.imageViewArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("status", "archive");

                firestore.collection("roomOneUsers").document(firebaseAuth.getUid())
                        .collection("myTasks").document(task.getId()).update(map);

                 tasks.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setMessage("Are you sure to delete this task ?")
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firestore.collection("roomOneUsers").document(firebaseAuth.getUid())
                                        .collection("myTasks").document(task.getId()).delete();

                                tasks.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDate, textViewTime;
        ImageView imageViewDone, imageViewArchive;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.item_task_tv_title);
            textViewDate = itemView.findViewById(R.id.item_task_tv_date);
            textViewTime = itemView.findViewById(R.id.item_task_tv_time);
            imageViewDone = itemView.findViewById(R.id.item_task_iv_done);
            imageViewArchive = itemView.findViewById(R.id.item_task_iv_archive);
        }

    }
}
