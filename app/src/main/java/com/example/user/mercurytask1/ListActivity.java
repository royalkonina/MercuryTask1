package com.example.user.mercurytask1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends Activity {
  private List<Record> data;
  private static int[] colorsRainbow = {Color.RED, Color.BLACK, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.TRANSPARENT};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_list);
    if (data == null) {
      data = new ArrayList<>();
      fillData(50);
    }
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    ListAdapter adapter = new ListAdapter(data);
    recyclerView.setAdapter(adapter);
  }

  private void fillData(int countElements) {
    for (int i = 0; i < countElements; i++) {
      data.add(new Record("Element " + (i + 1), colorsRainbow[i%colorsRainbow.length]));
    }
  }
}
