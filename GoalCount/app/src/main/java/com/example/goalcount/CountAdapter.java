package com.example.goalcount;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class CountAdapter extends ListAdapter<CountEntry, CountAdapter.CountViewHolder> {

    private final OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick(CountEntry entry);
    }

    protected CountAdapter(OnDeleteClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    private static final DiffUtil.ItemCallback<CountEntry> DIFF_CALLBACK = new DiffUtil.ItemCallback<CountEntry>() {
        @Override
        public boolean areItemsTheSame(@NonNull CountEntry oldItem, @NonNull CountEntry newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CountEntry oldItem, @NonNull CountEntry newItem) {
            return oldItem.count == newItem.count && oldItem.timestamp == newItem.timestamp;
        }
    };

    @NonNull
    @Override
    public CountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_count_log, parent, false);
        return new CountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountViewHolder holder, int position) {
        CountEntry entry = getItem(position);
        holder.tvCount.setText("+" + entry.count);
        
        String dateString = DateFormat.format("MMM dd, yyyy - hh:mm a", new Date(entry.timestamp)).toString();
        holder.tvDate.setText(dateString);

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(entry));
    }

    static class CountViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvCount;
        ImageButton btnDelete;

        public CountViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvCount = itemView.findViewById(R.id.tvCount);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
