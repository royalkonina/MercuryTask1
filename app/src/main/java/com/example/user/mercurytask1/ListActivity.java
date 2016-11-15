package com.example.user.mercurytask1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListActivity extends AppCompatActivity {
  private List<Record> data;
  private List<Integer> selectedItems;
  private static int[] colorsRainbow = {Color.RED, Color.rgb(255, 140, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(75, 0, 130), Color.TRANSPARENT};
  private ListAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_list);
    if (data == null) {
      data = new ArrayList<>();
      selectedItems = new ArrayList<>();
      fillData(50);
    }
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ListAdapter(data, selectedItems, this);
    recyclerView.setAdapter(adapter);

  }


  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (selectedItems != null && selectedItems.size() != 0) {
      getMenuInflater().inflate(R.menu.menu, menu);
    }
    return super.onPrepareOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.removeItem) {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.confirmationQuestion)
              .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                  int countDeleted = 0;
                  Collections.sort(selectedItems);
                  for (int position : selectedItems) {
                    data.remove(position - countDeleted++);
                  }
                  selectedItems.clear();
                  adapter.notifyDataSetChanged();
                  invalidateOptionsMenu();
                }
              })
              .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
              });
      builder.create().show();
    }
    return super.onOptionsItemSelected(item);
  }

  private void fillData(int countElements) {
    for (int i = 0; i < countElements; i++) {
      data.add(new Record("Element " + (i + 1), colorsRainbow[i % colorsRainbow.length], (i + 1) % 8 != 0));
    }
  }
}
