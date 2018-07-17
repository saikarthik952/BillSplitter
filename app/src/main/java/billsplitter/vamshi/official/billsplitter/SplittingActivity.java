package billsplitter.vamshi.official.billsplitter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SplittingActivity extends AppCompatActivity {

    int flag=0;
    Database db;
    Button showbill;
    String result[];
    SharedPreferences mshared;
    Set<String> persondata;
    List<itemdetails> itemdetails;
    //    String[] names = {"Person A", "Person B", "Person C","Person D"};
//    String[] money = {"700", "300", "100", "600","900"};
    int count;
    int counter=0;
    int columnToBeUsed = 0;
    double columnDivision;
    int div=0;
    int total=0;
    Double am;
    CheckBox[] allcheckbx;
    String[] persond;
    int[][] splitmatrix;
    int[][] weights;
    Double[] finalmatrix;
    long profcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitting);
        mshared = getSharedPreferences("personData", MODE_PRIVATE);
        db = new Database(SplittingActivity.this);
        count=mshared.getInt("personcount",0);
        persondata=mshared.getStringSet("persondetails",null);
         persond = persondata.toArray(new String[persondata.size()]);
        itemdetails=new ArrayList<>();
        profcount=db.getProfilesCount();
        itemdetails=db.hubretreive();
        showbill=findViewById(R.id.showbill);
        weights = new int[persond.length][itemdetails.size()];
        init();
    }
    public void init()
    {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        stk.bringToFront();
        stk.setStretchAllColumns(true);


        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Names");
        tv0.setTextColor(Color.BLACK);
        tv0.setTextAppearance(Typeface.BOLD);

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(40, 40, 40, 40);
        tv0.setLayoutParams(layoutParams);
        tbrow0.addView(tv0);
        for(int i=0;i<itemdetails.size();i++) {
            TextView tv1 = new TextView(this);

            tv1.setText(itemdetails.get(i).getItemcost());
            tv1.setTextColor(Color.BLACK);
            tv1.setTextAppearance(Typeface.BOLD);

            TableRow.LayoutParams l1 = new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
            tv1.setLayoutParams(layoutParams);
            l1.setMargins(40, 40, 40, 40);

            tbrow0.addView(tv1);
        }
        stk.addView(tbrow0);
        for (int i = 0; i < persond.length; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(persond[i]);
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            t1v.setTextAppearance(Typeface.BOLD);

            TableRow.LayoutParams l2 = new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            l2.setMargins(40, 40, 40, 40);
            t1v.setLayoutParams(l2);
            tbrow.addView(t1v);
            for(int y=0;y<itemdetails.size();y++)
            {
                CheckBox t2v = new CheckBox(this);

                t2v.setId(counter);
                counter++;
                Log.e("ChechIDS", "init: "+counter );

                t2v.setGravity(Gravity.CENTER);
                TableRow.LayoutParams l3 = new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                l3.setMargins(40, 40, 40, 40);
                t2v.setLayoutParams(l3);
                tbrow.addView(t2v);
            }


            stk.addView(tbrow);
        }
        allcheckbx= new CheckBox[counter];
        splitmatrix= new int[persond.length][itemdetails.size()];
        for(int u=0;u<counter;u++)
        {
            allcheckbx[u]=findViewById(u);
        }
        showbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                for(int k=0;k<persond.length;k++)
                {
                    for(int r=0;r<itemdetails.size();r++)
                    {
                        if(allcheckbx[flag].isChecked())
                        {
                         splitmatrix[k][r]=1;
                        } else {
                            splitmatrix[k][r] = 0;
                        }
                        if (flag != counter) {
                            flag++;
                        }
                        else if(flag==counter)
                        {
                            continue;
                        }
                    }


                }
                for(int p=0;p<persond.length;p++)
                    for (int o=0;o<itemdetails.size();o++)
                        weights[p][o]=0;
                Log.e("Matrix", "onClick: "+ Arrays.deepToString(splitmatrix));
                int h;

                //TODO Check there is BUG in this LOOP !!!!!
                for(int k=0;k<persond.length;k++)
                {
                    div=0;
                    for(int r=0;r<itemdetails.size();r++)
                    {
                        div=0;
                        for(h=0;h<persond.length;h++)
                        {
                                if(splitmatrix[h][r]==0)
                                {
                                    Log.e("Div", "onClick: "+div);
                                    break;
                                }
                              else  if(splitmatrix[h][r]==1&& splitmatrix[k][r]!=0)
                                {
                                    Log.e("Div", "onClick: "+div);
                                    div++;
                                    weights[k][r]=div;
                                }

//TODO Calculate the Share and store in Double[] finalmatrix variable

//TODO ADD Intent For Result Activity
                        }




                        }

                    Log.e("am", "onClick: "+ Arrays.deepToString(weights));
                }


            }
        });
    }
}


