package com.example.user.mercurytask1;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
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
    Model.getInstance(this);
    setContentView(R.layout.a_list);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ListAdapter(new OnItemCheckListener() {
      @Override
      public void onItemSelected(int countSelected) {
        countSelectedItems = countSelected;
        invalidateOptionsMenu();
      }
    });
    recyclerView.setAdapter(adapter);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog = new AddElementDialog();
        dialog.setSelectedColor(getResources().getColor(R.color.colorTransparent));
        dialog.setOnItemAddedListener(new OnItemAddedListener() {
          @Override
          public void onItemAdded() {
            adapter.notifyDataSetChanged();
          }
        });
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        dialog.show(ft, AddElementDialog.TAG);
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
    if (item.getItemId() == R.id.remove_item) {
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
