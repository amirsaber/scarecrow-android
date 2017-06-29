package com.geekofwallstreet.scarecrow;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.*;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView orderTextView = (TextView) findViewById(R.id.textView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Orders");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orderTextView.setText("");
                Iterator<DataSnapshot> dIterator = dataSnapshot.getChildren().iterator();
                while (dIterator.hasNext()) {
                    DataSnapshot orderDataSnapshot = dIterator.next();
                    Order order = orderDataSnapshot.getValue(Order.class);
                    orderTextView.append(order.toString() + "\n");
                    orderTextView.append("============\n");
                    Log.d(TAG, "Value is: " + order.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}
