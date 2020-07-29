package com.codepath.sshradha_simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnLongClickListener {
        void onItemsLongClicked(int position);
    }

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    List<String> items;
    OnLongClickListener onLongClickListener;
    OnClickListener onClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener onLongClickListener, OnClickListener onClickListener) {
        this.items = items;
        this.onLongClickListener = onLongClickListener;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        //wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);

        //Bind the item to a view holder
        holder.bind(item);
    }

    // how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // containers to provide easy access to views that represents each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tbItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tbItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the view holder with the data of string item
        public void bind(String item) {
            tbItem.setText(item);
            tbItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onItemClicked(getAdapterPosition());
                }
            });
            tbItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // notifying the listener the position that was long pressed
                    onLongClickListener.onItemsLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
