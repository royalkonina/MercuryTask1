package com.example.user.mercurytask1;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Model {
  private List<ColorItem> data;
  private Set<String> usedNames;
  private static Model instance;
  public static final int[] colorsRainbow = {Color.RED, Color.rgb(255, 140, 0), Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.rgb(75, 0, 130), Color.TRANSPARENT};
  /*public static final String[] colorNames = {"Red","Orange","Yellow","Green","Blue","Cyan","Violet", "Invisible"};*/

  private Model() {
    data = new ArrayList<>();
    usedNames = new HashSet<>();
  }


  public static Model getInstance() {
    if (instance == null) {
      instance = new Model();
      instance.fillData(50);
    }
    return instance;
  }

  public ColorItem getItem(int position) {
    return data.get(position);
  }

  public int getItemCount() {
    return data.size();
  }

  private void fillData(int countElements) {
    for (int i = 0; i < countElements; i++) {
      data.add(new ColorItem("Element " + (i + 1), colorsRainbow[i % colorsRainbow.length]));
      usedNames.add("Element " + (i + 1));
    }
  }

  public boolean needToInsert(String name) {
    return name.length() > 0 && !usedNames.contains(name);
  }

  public void addItem(String name, int color) {
    data.add(new ColorItem(name, color));
    usedNames.add(name);
  }


  public void removeItem(int position) {
    usedNames.remove(data.get(position).getText());
    data.remove(position);
  }
}
