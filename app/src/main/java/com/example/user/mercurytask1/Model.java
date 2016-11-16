package com.example.user.mercurytask1;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Model {
  private List<Record> data;
  private List<Integer> selectedItems;
  private Set<String> usedNames;
  private boolean created = false;
  private static Model instance;
  public static final int[] colorsRainbow = {Color.RED, Color.rgb(255, 140, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(75, 0, 130), Color.TRANSPARENT};
  public static final String[] colorNames = {"Red","Orange","Yellow","Green","Blue","Cyan","Violet", "Invisible"};

  private Model() {
    data = new ArrayList<>();
    selectedItems = new ArrayList<>();
    usedNames = new HashSet<>();
  }

  public void setCreated(boolean created) {
    this.created = created;
  }

  public static Model getInstance() {
    if (instance == null) instance = new Model();
    return instance;
  }

  public List<Record> getData() {
    return data;
  }

  public Set<String> getUsedNames() {
    return usedNames;
  }

  public List<Integer> getSelectedItems() {
    return selectedItems;
  }

  public boolean isCreated() {
    return created;
  }

  public void fillData(int countElements) {
    for (int i = 0; i < countElements; i++) {
      data.add(new Record("Element " + (i + 1), colorsRainbow[i % colorsRainbow.length], (i + 1) % 8 != 0));
      usedNames.add("Element " + (i + 1));
    }
  }

}
