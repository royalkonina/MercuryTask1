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

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ListActivity extends AppCompatActivity {
  private List<Record> data;
  private List<Integer> selectedItems;
  private ListAdapter adapter;
  private Set<String> usedNames;
  private Model model;
  private AddElementDialog dialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.a_list);
    this.model = Model.getInstance();
    if (!model.isCreated()) {
      model.fillData(50);
      model.setCreated(true);
    }
    this.data = model.getData();
    this.selectedItems = model.getSelectedItems();
    this.usedNames = model.getUsedNames();

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new ListAdapter(data, selectedItems, this);
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
                    usedNames.remove(data.get(position - countDeleted).getText());
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

  public boolean addNewElement(String name, int color) {
    if (!usedNames.contains(name)) {
      Record element = new Record(name, color, true);
      data.add(element);
      usedNames.add(name);
      adapter.notifyDataSetChanged();
      return true;
    }
    return false;
  }

}
