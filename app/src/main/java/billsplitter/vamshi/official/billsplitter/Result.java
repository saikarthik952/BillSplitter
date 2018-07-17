package billsplitter.vamshi.official.billsplitter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Result extends AppCompatActivity {
    Database db;

    SharedPreferences mshared;
    Set<String> persondata;
    List<itemdetails> itemdetails;
    int count;
    String[] persond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mshared = getSharedPreferences("personData", MODE_PRIVATE);
        db = new Database(Result.this);
        count = mshared.getInt("personcount", 0);
        persondata = mshared.getStringSet("persondetails", null);
        persond = persondata.toArray(new String[persondata.size()]);
        itemdetails = new ArrayList<>();
        itemdetails = db.hubretreive();
        init();
    }

    void init()
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
        TextView tv1 = new TextView(this);

        tv1.setText("Share");
        tv1.setTextColor(Color.BLACK);
        tv1.setTextAppearance(Typeface.BOLD);

        tbrow0.addView(tv0);
        tbrow0.addView(tv1);



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

                TextView t2v = new TextView(this);
            t2v.setText(persond[i]);
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
//TODO Display Shared Amount HERE!


                t2v.setGravity(Gravity.CENTER);
                TableRow.LayoutParams l3 = new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

                l3.setMargins(40, 40, 40, 40);
                t2v.setLayoutParams(l3);
                tbrow.addView(t2v);



            stk.addView(tbrow);
        }
    }
}
