package com.example.user.mercurytask1;


public class Record {
  private int color;
  private String text;
  private boolean shouldBeShown;

  public Record(String text, int color, boolean shouldBeShown) {
    this.color = color;
    this.text = text;
    this.shouldBeShown = shouldBeShown;
  }

  public int getColor() {
    return color;
  }

  public String getText() {
    return text;
  }

  public boolean isShouldBeShown() {
    return shouldBeShown;
  }
}
