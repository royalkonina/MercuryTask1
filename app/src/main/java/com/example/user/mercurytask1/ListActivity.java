package com.example.user.mercurytask1;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListActivity extends AppCompatActivity implements View.OnLongClickListener {
  private ListAdapter adapter;
  private AddElementDialog dialog;
  private int countSelectedItems = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_list);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ListAdapter(new Handler.Callback() {
      @Override
      public boolean handleMessage(Message message) {
        countSelectedItems = message.arg1;
        invalidateOptionsMenu();
        return false;
      }
    });
    recyclerView.setAdapter(adapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog = new AddElementDialog();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, "dialog");
      }
    });
  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    if (countSelectedItems != 0) {
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
                  adapter.removeItems();
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


  @Override
  public boolean onLongClick(View view) {
    return false;
  }
}
