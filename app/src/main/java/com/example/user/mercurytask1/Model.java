package com.example.user.mercurytask1;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Model {
  private List<ColorItem> data;
  private Set<String> usedNames;
  private static Model instance;
  public static final int[] colorsRainbow = {R.color.colorRed, R.color.colorOrange, R.color.colorYellow, R.color.colorGreen, R.color.colorBlue, R.color.colorCyan, R.color.colorViolet, R.color.colorTransparent};


  private Model() {
    data = new ArrayList<>();
    usedNames = new HashSet<>();
  }


  public static Model getInstance(Context context) {
    if (instance == null) {
      instance = new Model();
      instance.fillData(context, 50);
    }
    return instance;
  }

  public ColorItem getItem(int position) {
    return data.get(position);
  }

  public int getItemCount() {
    return data.size();
  }

  private void fillData(Context context, int countElements) {
    for (int i = 0; i < countElements; i++) {
      int curColor = context.getResources().getColor(colorsRainbow[i % colorsRainbow.length]);
      data.add(new ColorItem("Element " + (i + 1), curColor));
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
