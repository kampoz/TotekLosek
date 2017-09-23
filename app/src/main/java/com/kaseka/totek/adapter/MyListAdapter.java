package com.kaseka.totek.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.kaseka.totek.Draw;
import com.kaseka.totek.R;
import java.util.ArrayList;


public class MyListAdapter extends BaseAdapter {

  Button button1 = null;
  Button button2 = null;
  Button button3 = null;
  Button button4 = null;
  Button button5 = null;
  Button button6 = null;
  Button ballInBasket = null;
  ListView listView;
  private final static int VIEW_TYPE_EVEN = 0;
  private final static int VIEW_TYPE_ODD = 1;
  private ArrayList<Draw> drawsArrayList = new ArrayList<>();
  Context context;
  LayoutInflater inflater;
  int height;
  int ballTextSize;


  public MyListAdapter(ArrayList<Draw> drawsArrayList, Context context, ListView listView) {
    this.drawsArrayList = drawsArrayList;
    this.context = context;
    this.listView = listView;

    inflater = (LayoutInflater) context.getSystemService
        (Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return drawsArrayList.size();
  }

  @Override
  public Object getItem(int position) {
    return drawsArrayList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    if (convertView == null) {

      convertView = inflater.inflate(R.layout.single_draw_result_layout, null);
      button1 = (Button) convertView.findViewById(R.id.ball1);
      button2 = (Button) convertView.findViewById(R.id.ball2);
      button3 = (Button) convertView.findViewById(R.id.ball3);
      button4 = (Button) convertView.findViewById(R.id.ball4);
      button5 = (Button) convertView.findViewById(R.id.ball5);
      button6 = (Button) convertView.findViewById(R.id.ball6);
      ballInBasket = (Button) convertView.findViewById(R.id.btn_ball_in_basket);
    }

    int width = listView.getMeasuredWidth();
    height = listView.getMeasuredHeight() / 9;
    ballTextSize = listView.getMeasuredHeight() / 15;

    convertView.setLayoutParams(new AbsListView.LayoutParams(width, height));

    setButtonApperance(button1, height, ballTextSize, position);
    setButtonApperance(button2, height, ballTextSize, position);
    setButtonApperance(button3, height, ballTextSize, position);
    setButtonApperance(button4, height, ballTextSize, position);
    setButtonApperance(button5, height, ballTextSize, position);
    setButtonApperance(button6, height, ballTextSize, position);

    Draw draw = (Draw) getItem(position);

    button1.setText(Integer.toString(draw.getDrawResults().get(0)));
    button2.setText(Integer.toString(draw.getDrawResults().get(1)));
    button3.setText(Integer.toString(draw.getDrawResults().get(2)));
    button4.setText(Integer.toString(draw.getDrawResults().get(3)));
    button5.setText(Integer.toString(draw.getDrawResults().get(4)));
    button6.setText(Integer.toString(draw.getDrawResults().get(5)));

    Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
    animation.setDuration(400 + position * 100);
    convertView.startAnimation(animation);

    return convertView;
  }

  private void setButtonApperance(Button button, int height, int ballTextSize, int position){
    button.getLayoutParams().height = height - 4;
    button.getLayoutParams().width = height - 4;
    button.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

    int type = getItemViewType(position);
    if (type == VIEW_TYPE_EVEN) {
      button.setBackground(ContextCompat.getDrawable(context, R.drawable.light_yellow_ball_shape));
    } else {
      button.setBackground(ContextCompat.getDrawable(context, R.drawable.dark_yellow_ball_shape));
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position % 2 == 0) {
      return VIEW_TYPE_EVEN;
    } else {
      return VIEW_TYPE_ODD;
    }
  }

  public int getHeight() {
    return height;
  }

  public int getBallTextSize() {
    return ballTextSize;
  }
}
