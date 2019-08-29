package com.venkatesh.astegicsecoundapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.venkatesh.astegicsecoundapp.RoomDB.DataRepository;
import com.venkatesh.astegicsecoundapp.RoomDB.JoinDataRecord;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    JoinDataRecord joinDataRecord;
    TextView data;
    String[] users = {"Click Spinner","48f3","3e47","2cac"};
    private DataRepository mDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataRepository = new DataRepository(this);

        data = findViewById(R.id.textview);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        if(!users[position].equalsIgnoreCase("Click Spinner")) {
            joinDataRecord = mDataRepository.getScreenData(users[position]);
            //stagingId, context, status, userId
            data.setText("Staging ID: "+joinDataRecord.getStagingId() + ",\nContext: " + joinDataRecord.getContext() + ",\nStatus: " + joinDataRecord.getStatus() + ",\nUserID: " + joinDataRecord.getUserID());
            Toast.makeText(getApplicationContext(), "Selected User: " + joinDataRecord.getContext().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }


}
