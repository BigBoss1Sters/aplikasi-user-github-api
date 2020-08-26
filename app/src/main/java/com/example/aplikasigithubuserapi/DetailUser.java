package com.example.aplikasigithubuserapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailUser extends AppCompatActivity {
    public static final String EXTRA_ITEM = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail User");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        setData();
    }

    public void setData(){
        User user = getIntent().getParcelableExtra(EXTRA_ITEM);

        TextView tvName = findViewById(R.id.tv_item_name);
        ImageView avatar = findViewById(R.id.img_item_avatar);
        TextView tvUsername = findViewById(R.id.tv_item_username);
        TextView tvLocation = findViewById(R.id.tv_item_location);
        TextView tvCompany = findViewById(R.id.tv_item_company);
        TextView tvRepository = findViewById(R.id.tv_item_repository);
        TextView tvFollower = findViewById(R.id.tv_item_follower);
        TextView tvFollowing = findViewById(R.id.tv_item_following);

        if(user != null) {
            Glide.with(this)
                    .load(user.getAvatar())
                    .into(avatar);
            tvName.setText(user.getName());
            tvUsername.setText(user.getUsername());
            tvLocation.setText(user.getLocation());
            tvCompany.setText(user.getCompany());
            tvRepository.setText(user.getRepository());
            tvFollower.setText(user.getFollower());
            tvFollowing.setText(user.getFollowing());
        }
    }
}