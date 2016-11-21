package com.example.user.mercurytask1;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
  private List<Integer> selectedItems;
  private Handler.Callback callback;


  public ListAdapter(Handler.Callback callback) {
    super();
    selectedItems = new ArrayList<>();
    this.callback = callback;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_listitem, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    Model model = Model.getInstance();
    final ColorItem element = model.getItem(position);
    holder.textView.setText(element.getText());
    holder.circle.setColorFilter(element.getColor());
    holder.itemView.setSelected(selectedItems.contains(position));

    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View view) {
        view.setSelected(!view.isSelected());
        if (view.isSelected()) {
          Snackbar.make(view, "You selected " + element.getText(), Snackbar.LENGTH_SHORT).show();
          selectedItems.add(position);
        } else {
          selectedItems.remove(Integer.valueOf(position));
        }
        Message message = new Message();
        message.arg1 = selectedItems.size();
        callback.handleMessage(message);
        return true;
      }
    });
  }

  public void removeItems() {
    int countDeleted = 0;
    Collections.sort(selectedItems);
    Model model = Model.getInstance();
    for (int position : selectedItems) {
      model.removeItem(position - countDeleted);
      countDeleted++;
    }
    selectedItems.clear();
    Message message = new Message();
    message.arg1 = selectedItems.size();
    callback.handleMessage(message);
  }

  @Override
  public int getItemCount() {
    return Model.getInstance().getItemCount();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView circle;

    public ViewHolder(final View itemView) {
      super(itemView);
      textView = (TextView) itemView.findViewById(R.id.textView);
      circle = (ImageView) itemView.findViewById(R.id.circle);
    }
  }
}
