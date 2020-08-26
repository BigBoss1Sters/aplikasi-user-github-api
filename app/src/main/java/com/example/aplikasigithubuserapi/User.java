package com.example.aplikasigithubuserapi;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String username;
    private String avatar;
    private String url;
    private String name;
    private String location;
    private String company;
    private int repository;
    private int following;
    private int follower;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getRepository() {
        return repository;
    }

    public void setRepository(int repository) {
        this.repository = repository;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatar);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(company);
        dest.writeInt(repository);
        dest.writeInt(following);
        dest.writeInt(follower);
    }

    User(){

    }

    private User(Parcel in) {
        username = in.readString();
        avatar = in.readString();
        url = in.readString();
        name = in.readString();
        location = in.readString();
        company = in.readString();
        repository = in.readInt();
        following = in.readInt();
        follower = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
