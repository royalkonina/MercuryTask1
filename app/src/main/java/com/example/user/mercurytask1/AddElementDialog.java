package com.example.user.mercurytask1;

import android.app.DialogFragment;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SimpleExpandableListAdapter;

import java.util.Random;


public class AddElementDialog extends DialogFragment implements View.OnClickListener {
  private int selectedColor;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.df_adding, container);
    Button addButton = (Button) v.findViewById(R.id.add_button);
    final EditText editText = (EditText) v.findViewById(R.id.edit_text);

    ImageButton[] buttons = new ImageButton[8];
    buttons[0] = (ImageButton) v.findViewById(R.id.colorButtonRed);
    buttons[1] = (ImageButton) v.findViewById(R.id.colorButtonOrange);
    buttons[2] = (ImageButton) v.findViewById(R.id.colorButtonYellow);
    buttons[3] = (ImageButton) v.findViewById(R.id.colorButtonGreen);
    buttons[4] = (ImageButton) v.findViewById(R.id.colorButtonBlue);
    buttons[5] = (ImageButton) v.findViewById(R.id.colorButtonCyan);
    buttons[6] = (ImageButton) v.findViewById(R.id.colorButtonViolet);
    buttons[7] = (ImageButton) v.findViewById(R.id.colorButtonTransparent);
    for (int i = 0; i < 8; i++) {
      buttons[i].setColorFilter(Model.colorsRainbow[i]);
      buttons[i].setOnClickListener(this);
    }

    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ListActivity parentActivity = (ListActivity) getActivity();
        boolean isAdded = parentActivity.addNewElement(String.valueOf(editText.getText()),  selectedColor);
        if (isAdded) {
          dismiss();
        } else {
          editText.setError("This name is already in use");
        }
      }
    });
    return v;
  }

  @Override
  public void onClick(View view) {
    ImageButton buttonClicked = (ImageButton) view;
    int id = buttonClicked.getId();
    switch (id){
      case R.id.colorButtonRed:
        selectedColor = Model.colorsRainbow[0];
        break;
      case R.id.colorButtonOrange:
        selectedColor = Model.colorsRainbow[1];
        break;
      case R.id.colorButtonYellow:
        selectedColor = Model.colorsRainbow[2];
        break;
      case R.id.colorButtonGreen:
        selectedColor = Model.colorsRainbow[3];
        break;
      case R.id.colorButtonBlue:
        selectedColor = Model.colorsRainbow[4];
        break;
      case R.id.colorButtonCyan:
        selectedColor = Model.colorsRainbow[5];
        break;
      case R.id.colorButtonViolet:
        selectedColor = Model.colorsRainbow[6];
        break;
      case R.id.colorButtonTransparent:
        selectedColor = Model.colorsRainbow[7];
        break;
    }

  }
}
