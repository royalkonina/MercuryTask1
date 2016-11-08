package com.example.user.mercurytask1;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
  private List<Record> data;
  private int selected_position;

  public ListAdapter(List<Record> data) {
    this.data = data;
    this.selected_position = -1;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_listitem, parent, false);
    return new ViewHolder(v);
  }


  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    Record element = data.get(position);
    holder.textView.setText(element.getText());
    holder.circle.setBackgroundColor(element.getColor());
    if (position == selected_position) {
      holder.textView.setBackgroundColor(Color.GRAY);
      holder.textView.setTextColor(Color.WHITE);
      System.err.println("Clicked on " + holder.textView.getX() + " " + holder.textView.getY());
    }else{
      holder.textView.setBackgroundColor(Color.WHITE);
      holder.textView.setTextColor(Color.BLACK);
    }
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int prevSelPosition = selected_position;
        selected_position = position;
        System.err.println(position);
        notifyItemChanged(prevSelPosition);
        notifyItemChanged(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return data.size();
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
