package com.example.user.mercurytask1;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class AddElementDialog extends DialogFragment implements View.OnClickListener {
  private int selectedColor = Color.TRANSPARENT;
  private ArrayList<ImageButton> buttons;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.d_adding, container);
    Button addButton = (Button) v.findViewById(R.id.add_button);
    final EditText editText = (EditText) v.findViewById(R.id.edit_text);
    LinearLayout buttonsLayout = (LinearLayout) v.findViewById(R.id.buttonLayout);
    buttons = new ArrayList<>();
    int countChildren = buttonsLayout.getChildCount();
    for (int i = 0; i < countChildren; i++) {
      ImageButton colorButton = (ImageButton) buttonsLayout.getChildAt(i);
      colorButton.setColorFilter(Model.colorsRainbow[i]);
      colorButton.setTag(i);
      colorButton.setOnClickListener(this);
      buttons.add(colorButton);
    }
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String name = String.valueOf(editText.getText());
        Model model = Model.getInstance();
        boolean canBeAdded = model.needToInsert(name);
        if (canBeAdded) {
          model.addItem(name, selectedColor);
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
    for (ImageButton button : buttons) {
      button.setSelected(false);
    }
    ImageButton buttonClicked = (ImageButton) view;
    buttonClicked.setSelected(true);
    int colorIndex = (int) buttonClicked.getTag();
    selectedColor = Model.colorsRainbow[colorIndex];
  }
}
