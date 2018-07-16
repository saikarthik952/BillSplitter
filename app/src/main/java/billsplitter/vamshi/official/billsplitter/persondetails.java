package billsplitter.vamshi.official.billsplitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class persondetails extends AppCompatActivity {
    EditText itemname,itemcost;
    Button additem,next;
    ListView itemslist;
    SharedPreferences mshared;

Database db;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persondetails);
        additem=findViewById(R.id.additem);
        next=findViewById(R.id.next);
        mshared= getSharedPreferences("personData",MODE_PRIVATE);
        itemname=findViewById(R.id.itemname);
        itemcost=findViewById(R.id.itemcost);
        final RelativeLayout rootView = (RelativeLayout) findViewById(R.id.rootview);
        db= new Database(persondetails.this);
        Log.e("Persondetails", "onCreate: "+mshared.getAll() );
        final ArrayList<itemdetails> itemslist = new ArrayList<itemdetails>();
// Create the adapter to convert the array to views
        final ListAdapter adapter = new ListAdapter(this, itemslist);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        db.removeall();
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemdetails x=new itemdetails(itemname.getText().toString(),itemcost.getText().toString());
                itemslist.add(x);
                db.hubinsert(itemname.getText().toString(),Integer.parseInt(itemcost.getText().toString()));
                Toast.makeText(persondetails.this,"Item Added",Toast.LENGTH_SHORT).show();
                itemname.setText("");
                itemcost.setText("");
                adapter.notifyDataSetChanged();


            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(persondetails.this,SplittingActivity.class));
                finish();
            }
        });
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();

                if (heightDiff > 100) {
                 next.setVisibility(View.VISIBLE);
                } else {
                    next.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
