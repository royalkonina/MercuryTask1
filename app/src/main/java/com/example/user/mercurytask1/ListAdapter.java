package com.example.user.mercurytask1;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
  private List<Record> data;

  public ListAdapter(List<Record> data) {
    this.data = data;
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
    holder.circle.setColorFilter(element.getColor());
    if(!element.isShouldBeShown())holder.circle.setVisibility(View.INVISIBLE);
    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        view.setSelected(!view.isSelected());
        if(view.isSelected())Snackbar.make(view, "You clicked on element #" + (position + 1), Snackbar.LENGTH_SHORT).show();
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
