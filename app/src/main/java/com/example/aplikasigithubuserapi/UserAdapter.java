package com.example.aplikasigithubuserapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ListViewHolder> {
    private ArrayList<User> listUser = new ArrayList<>();

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        final User user = listUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getAvatar())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.avatar);
        holder.tvUsername.setText(user.getUsername());
        holder.tvUrl.setText(user.getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(user);
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(User data);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public void setData(ArrayList<User> list){
        listUser.clear();
        listUser.addAll(list);
        notifyDataSetChanged();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView tvUsername, tvUrl;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.img_item_avatar);
            tvUsername = itemView.findViewById(R.id.tv_item_username);
            tvUrl = itemView.findViewById(R.id.tv_url);
        }
    }
}
