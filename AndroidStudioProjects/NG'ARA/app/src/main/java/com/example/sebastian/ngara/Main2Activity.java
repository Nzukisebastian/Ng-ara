package com.example.sebastian.ngara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    RequestQueue mRequest;
    List<ModelList> mListitems;

    private final String url="http://10.0.2.2/imageupload/getallimages.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mRecyclerview=(RecyclerView)findViewById(R.id.recyclerTemp);
        mRequest= Volley.newRequestQueue(getApplicationContext());
        mListitems=new ArrayList<>();
        request();
        mManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter=new AdapterList(mListitems,Main2Activity.this);
        mRecyclerview.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        RelativeLayout main_view=(RelativeLayout)findViewById(R.id.main_view);
        switch(item.getItemId()){
            case R.id.menu_green:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Phonecall.class));
                // main_view.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.menu_yellow:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                startActivity(new Intent(this,Msg.class));
                //main_view.setBackgroundColor(Color.YELLOW);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void request(){
        JsonArrayRequest requestimage=new JsonArrayRequest(Request.Method.POST, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("JSONResponse",response.toString());

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject data=response.getJSONObject(i);
                        ModelList model=new ModelList();
                        model.setId(data.getString("id"));
                        model.setTitle(data.getString("title"));
                        model.setKaterangan(data.getString("kitengerani"));
                        model.setImg(data.getString("image"));
                        mListitems.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERRORRequest","Error:"+error.getMessage());

                    }
                });
        mRequest.add(requestimage);
    }
    public void image(View view){

        startActivity(new Intent(this, Main2Activity.class));
    }
    public void info(View view){

       startActivity(new Intent(this, Uploadimage.class));
    }
}

