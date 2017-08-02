package com.kaseka.totek.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kaseka.totek.Draw;
import com.kaseka.totek.MainActivity;
import com.kaseka.totek.R;

import java.util.ArrayList;
import java.util.zip.Inflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class MyListAdapter extends BaseAdapter {

    Button button1 = null;
    Button button2 = null;
    Button button3 = null;
    Button button4 = null;
    Button button5 = null;
    Button button6 = null;
    ListView listView;

    private ArrayList<Draw> drawsArrayList = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    public MyListAdapter(ArrayList<Draw> drawsArrayList, Context context, ListView listView) {
        this.drawsArrayList = drawsArrayList;
        this.context = context;
        this.listView = listView;

        inflater = (LayoutInflater)context.getSystemService
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

        if(convertView == null) {

            convertView = inflater.inflate(R.layout.single_draw_result_layout, null);
            button1 = (Button)convertView.findViewById(R.id.ball1);
            button2 = (Button)convertView.findViewById(R.id.ball2);
            button3 = (Button)convertView.findViewById(R.id.ball3);
            button4 = (Button)convertView.findViewById(R.id.ball4);
            button5 = (Button)convertView.findViewById(R.id.ball5);
            button6 = (Button)convertView.findViewById(R.id.ball6);
        }

        int width = listView.getMeasuredWidth();
        int height = listView.getMeasuredHeight()/9;
        int ballTextSize = listView.getMeasuredHeight()/15;

        convertView.setLayoutParams(new AbsListView.LayoutParams(width, height));

        button1.getLayoutParams().height = height - 4;
        button1.getLayoutParams().width = height - 4;
        button1.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        button2.getLayoutParams().height = height - 4;
        button2.getLayoutParams().width = height - 4;
        button2.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        button3.getLayoutParams().height = height - 4;
        button3.getLayoutParams().width = height - 4;
        button3.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        button4.getLayoutParams().height = height - 4;
        button4.getLayoutParams().width = height - 4;
        button4.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        button5.getLayoutParams().height = height - 4;
        button5.getLayoutParams().width = height - 4;
        button5.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        button6.getLayoutParams().height = height - 4;
        button6.getLayoutParams().width = height - 4;
        button6.setTextSize(TypedValue.COMPLEX_UNIT_PX, ballTextSize);

        Draw draw = (Draw)getItem(position);

        button1.setText(Integer.toString(draw.getDrawResults().get(0)));
        button2.setText(Integer.toString(draw.getDrawResults().get(1)));
        button3.setText(Integer.toString(draw.getDrawResults().get(2)));
        button4.setText(Integer.toString(draw.getDrawResults().get(3)));
        button5.setText(Integer.toString(draw.getDrawResults().get(4)));
        button6.setText(Integer.toString(draw.getDrawResults().get(5)));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_left_in);
        animation.setDuration(400+position*100);
        convertView.startAnimation(animation);

        return convertView;
    }
}
