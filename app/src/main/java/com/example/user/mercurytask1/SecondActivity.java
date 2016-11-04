package com.example.user.mercurytask1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class SecondActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_screen);
    ListView listView = (ListView) findViewById(R.id.listViewSecondScreen);
    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_second_screen, new String[]{"34", "43"});
    listView.setAdapter(arrayAdapter);
  }
}
