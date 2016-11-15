package com.example.user.mercurytask1;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;


public class AddElementDialog extends DialogFragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.df_adding, container);
    Button addButton = (Button) v.findViewById(R.id.add_button);
    final EditText editText = (EditText) v.findViewById(R.id.edit_text);
    addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ListActivity parentActivity = (ListActivity) getActivity();
        boolean isAdded = parentActivity.addNewElement(String.valueOf(editText.getText()), new Random().nextInt());
        if (isAdded) {
          dismiss();
        } else {
          editText.setError("This name is already in use");
        }
      }
    });
    return v;
  }
}
