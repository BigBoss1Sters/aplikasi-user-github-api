package com.example.aplikasigithubuserapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvUser;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<User> list = new ArrayList<>();
    private ProgressBar progressBar;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Github User's Search");
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        rvUser = findViewById(R.id.rv_user);
        progressBar = findViewById(R.id.progressBar);
        rvUser.setHasFixedSize(true);

        userAdapter = new UserAdapter();

        showRecyclerList();
        getListUser();
    }

    public void getListUsers(String username) {
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient asyncHttpClient1 = new AsyncHttpClient();
        asyncHttpClient1.addHeader("User-Agent", "request");
        asyncHttpClient1.addHeader("Authorization", "token a8d0fa76f1dbc00b8f0227b6bc1c619c9dd2406c");
        asyncHttpClient1.get("https://api.github.com/search/users/"+username, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);

                String result = new String(responseBody);
                Log.d(TAG, result);

                try {
                    JSONObject jsonObject = new JSONObject(result);

                        String username = jsonObject.getString("login");
                        String avatar = jsonObject.getString("avatar_url");
                        String url = jsonObject.getString("url");
                        String name = jsonObject.getString("name");
                        String location = jsonObject.getString("location");
                        String company = jsonObject.getString("company");
                        int repository = jsonObject.getInt("public_repos");
                        int followers = jsonObject.getInt("followers");
                        int following = jsonObject.getInt("following");

                        User user = new User();
                        user.setUsername(username);
                        user.setAvatar(avatar);
                        user.setUrl(url);
                        user.setName(name);
                        user.setLocation(location);
                        user.setCompany(company);
                        user.setRepository(repository);
                        user.setFollower(followers);
                        user.setFollowing(following);

                        list.add(user);
                    userAdapter.setData(list);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbiden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getListUser() {
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient asyncHttpClient1 = new AsyncHttpClient();
        asyncHttpClient1.addHeader("User-Agent", "request");
        asyncHttpClient1.addHeader("Authorization", "token a8d0fa76f1dbc00b8f0227b6bc1c619c9dd2406c");
        asyncHttpClient1.get("https://api.github.com/users", new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);

                String result = new String(responseBody);
                Log.d(TAG, result);

                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String username = item.getString("login");
                        getListUsers(username);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbiden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getSearchListUser(String username) {
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient asyncHttpClient1 = new AsyncHttpClient();
        asyncHttpClient1.addHeader("User-Agent", "request");
        asyncHttpClient1.addHeader("Authorization", "token a8d0fa76f1dbc00b8f0227b6bc1c619c9dd2406c");
        asyncHttpClient1.get("https://api.github.com/search/users?q="+username, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);

                String result = new String(responseBody);
                Log.d(TAG, result);

                try {
                   JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String username = item.getString("login");
                        getListUsers(username);
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbiden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showRecyclerList(){
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(userAdapter);

        userAdapter.setOnItemClickCallback(new UserAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(User data) {
                showSelectedUser(data);
            }
        });
    }

    private void showSelectedUser(User user){
        Intent moveIntent = new Intent(MainActivity.this, DetailUser.class);
        moveIntent.putExtra(DetailUser.EXTRA_ITEM, user);
        startActivity(moveIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    list.clear();
                    getSearchListUser(query);
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        return true;
    }

}

