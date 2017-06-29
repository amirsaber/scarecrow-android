package com.geekofwallstreet.scarecrow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import com.google.firebase.database.*;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Orders");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Order> dataSet = new ArrayList<Order>();
                Integer i = 0;
                Iterator<DataSnapshot> dIterator = dataSnapshot.getChildren().iterator();
                while (dIterator.hasNext()) {
                    DataSnapshot orderDataSnapshot = dIterator.next();
                    Order order = orderDataSnapshot.getValue(Order.class);
                    dataSet.add(i, order);
                    i++;
                    Log.d(TAG, "Value is: " + order.toString());
                }
                mAdapter = new MyAdapter(dataSet);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
