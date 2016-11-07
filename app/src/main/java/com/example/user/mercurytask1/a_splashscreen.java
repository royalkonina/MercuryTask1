package com.example.user.mercurytask1;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class a_splashscreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_splashscreen);
    if (savedInstanceState == null) {
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          Intent intent = new Intent(a_splashscreen.this, a_list.class);
          a_splashscreen.this.startActivity(intent);
          finish();
        }
      }, 2000);
    }
  }
}
