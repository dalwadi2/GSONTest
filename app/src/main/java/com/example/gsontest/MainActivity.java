package com.example.gsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "harsh";
    ListView ll;
    newResopnse newresponseobj;
    CustomAdapter customAdapter;
    Gson gson;
    AsyncHttpClient client;
    String newurl = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";

    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

           /* Allow activity to show indeterminate progressbar */
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Toast.makeText(getApplicationContext(), "Responce : " + error, Toast.LENGTH_LONG).show();
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
}
