package com.example.user.mercurytask1;

/**
 * Created by User on 11/8/2016.
 */

public class Record {
  private int color;
  private String text;

  public Record(String text, int color) {
    this.color = color;
    this.text = text;
  }

  public int getColor() {
    return color;
  }

  public String getText() {
    return text;
  }
}
