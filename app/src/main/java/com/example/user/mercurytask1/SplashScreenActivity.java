package com.example.user.mercurytask1;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_splashscreen);
    if (savedInstanceState == null) {
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          Intent intent = new Intent(SplashScreenActivity.this, ListActivity.class);
          SplashScreenActivity.this.startActivity(intent);
          finish();
        }
      }, 1000);
    }
  }
}
