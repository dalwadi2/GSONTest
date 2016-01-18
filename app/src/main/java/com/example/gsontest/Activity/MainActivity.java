package com.example.gsontest.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gsontest.CustomAdapter;
import com.example.gsontest.MyApplication;
import com.example.gsontest.MyRecyclerAdapter;
import com.example.gsontest.R;
import com.example.gsontest.RecyclerItemClickListener;
import com.example.gsontest.newResopnse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.squareup.picasso.Picasso;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "harsh";
    ListView ll;
    newResopnse newresponseobj;
    CustomAdapter customAdapter;
    Gson gson;
    AsyncHttpClient client;
    String newurl = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    FloatingActionButton floatingActionButton;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

           /* Allow activity to show indeterminate progressbar */
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getInstance().trackScreenView("Home");
        boolean my = isonline();
        AppRate.with(this)
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
        Toast.makeText(getApplicationContext(),""+ my,Toast.LENGTH_LONG).show();
        floatingActionButton = (FloatingActionButton) findViewById(R.id.view);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
//        setProgressBarIndeterminateVisibility(true);

        //  ll = (ListView) findViewById(R.id.movielist);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Gson Default with @Volley

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(newurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Myresponse : " + response);
//                Toast.makeText(getApplicationContext(),"Responce : "+ response,Toast.LENGTH_LONG).show();
                gson = new Gson();
                try {
                    Log.e(TAG, "Myresponse : " + response);
                    newresponseobj = gson.fromJson(response, newResopnse.class);
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "onSuccess Error: " + e);
                }
                Log.d(TAG, "doInBackground: " + newresponseobj.getActors().toString());
                //    customAdapter = new CustomAdapter(MainActivity.this, newresponseobj.getActors());

                //        ll.setAdapter(customAdapter);
                adapter = new MyRecyclerAdapter(MainActivity.this, newresponseobj.getActors());
                mRecyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Responce : " + error, Toast.LENGTH_LONG).show();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Snackbar.with(getApplicationContext())
                            .type(SnackbarType.MULTI_LINE)
                            .text("Check Internet Connection")
                            .actionLabel("Done")
                            .actionColor(Color.CYAN)
                            .actionListener(new ActionClickListener() {
                                @Override
                                public void onActionClicked(Snackbar snackbar) {
                                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            })
                            .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                            .swipeToDismiss(false)
                            .show(MainActivity.this);
                }
            }
        });
        queue.add(request);

        // Gson Default with @AsyncHttpClient
/*
        client = new AsyncHttpClient();
        client.get(MainActivity.this, newurl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);

                gson = new Gson();
                Log.e(TAG, "onSuccess: " + statusCode);
                try {
                    Log.e(TAG, "Myresponse : " + responseString);
                    newresponseobj = gson.fromJson(responseString, newResopnse.class);
                } catch (JsonSyntaxException e) {
                    Log.e(TAG, "onSuccess Error: " + e);
                }
                Log.d(TAG, "doInBackground: " + newresponseobj.getActors().toString());
                //    customAdapter = new CustomAdapter(MainActivity.this, newresponseobj.getActors());

                //        ll.setAdapter(customAdapter);
                adapter = new MyRecyclerAdapter(MainActivity.this, newresponseobj.getActors());
                mRecyclerView.setAdapter(adapter);
//                setProgressBarIndeterminateVisibility(false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, "onFailure: " + error);
            }
        });
*/

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();
                // ...
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "position : " + position, Toast.LENGTH_SHORT).show();

                // ...
            }
        }));
    }

    protected Boolean isonline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if (nf != null && nf.isConnectedOrConnecting()) {
            return true;
        } else
            return false;
    }
}
