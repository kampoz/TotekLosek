package com.kaseka.totek;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.support.v7.widget.RecyclerView;

import com.kaseka.totek.adapter.MyListAdapter;

import static com.kaseka.totek.R.string.app_name;

public class MainActivity extends AppCompatActivity {

  private Button buttonLosuj;
  private ArrayList<Integer> array49;
  private ArrayList<Draw> drawsArrayList = new ArrayList<>();
  private ListView listView;
  private TextView tvUnusedBalinfo;
  private Integer x;
  private MyListAdapter myListAdapter;
  private Toolbar toolbar;
  private Context context;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.app_bar);
    toolbar.setTitle(R.string.activity_main_toolbar_title);
    setSupportActionBar(toolbar);
    buttonLosuj = (Button) findViewById(R.id.button);
    listView = (ListView) findViewById(R.id.listview);
    tvUnusedBalinfo = (TextView) findViewById(R.id.tvUnusedBallInfo);
    array49 = new ArrayList<>();
    myListAdapter = new MyListAdapter(drawsArrayList, this, listView);
    listView.setAdapter(myListAdapter);

    buttonLosuj.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (!array49.isEmpty()) {
          AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
          builder.setMessage("Losowac jeszcze raz?");
          builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
              drawingNumbers();

            }
          });

          builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
          });
          AlertDialog alert = builder.create();
          alert.show();
        } else {
          drawingNumbers();
        }
      }
    });
  }

  private ArrayList<Integer> getSixRandomNumbers() {
    ArrayList<Integer> results = new ArrayList<>();
    for (int i = 0; i < 6; i++) {

      int x = new Random().nextInt(array49.size());
      Integer random = array49.get(x);
      results.add(random);
      array49.remove(random);
    }
    Collections.sort(results);
    return results;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_systems) {
      android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
          this);
      alertDialogBuilder.setMessage(
          "System 1:\nLosowanie ośmiu zakładów z 49 liczb bez powtórzeń liczb.\n\nSystem 2:\nIn preparing");
      alertDialogBuilder.setPositiveButton("OK",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
              dialog.dismiss();
            }
          });
      android.app.AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
    }
    if (id == R.id.action_setting) {
//            Intent startSettingsActivityIntent = new Intent(this, SettingsActivity.class);
//            this.startActivity(startSettingsActivityIntent);
//            this.finish();
    }
    if (id == R.id.action_about) {
      android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
          this);
      alertDialogBuilder
          .setMessage("Copyright \u00a9 2017\nKamil Poznakowski\nkampoznak@gmail.com");
      alertDialogBuilder.setPositiveButton("OK",
          new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
              dialog.dismiss();
            }
          });
      android.app.AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();
    }
    return super.onOptionsItemSelected(item);
  }

  private void drawingNumbers() {
    array49.clear();
    drawsArrayList.clear();
    for (int i = 0; i < 49; i++) {
      array49.add(i + 1);
    }
    for (int i = 0; i < 8; i++) {
      Draw draw = new Draw(getSixRandomNumbers());
      drawsArrayList.add(draw);
    }
    tvUnusedBalinfo
        .setText(getString(R.string.unused_ball_info) + " " + Integer.toString(array49.get(0)));
    myListAdapter = new MyListAdapter(drawsArrayList, MainActivity.this, listView);
    listView.setAdapter(myListAdapter);
  }
}
