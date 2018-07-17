package billsplitter.vamshi.official.billsplitter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class personcount extends AppCompatActivity {
    EditText personcount;
    Button next;
    String m;
    SharedPreferences mshared;
    SharedPreferences.Editor editor;
    int count=0;
    LinearLayout   l1;
    Set<String> spersondetails;
    EditText tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personcount);
        personcount=findViewById(R.id.personcount);
        mshared= getSharedPreferences("personData",MODE_PRIVATE);

        editor= mshared.edit();

        next=findViewById(R.id.next);
     spersondetails = new HashSet<String>();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
            count=Integer.parseInt(personcount.getText().toString().trim());
              editor.putInt("personcount",count);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(personcount.this);
                alertDialog.setTitle("Enter Person Details");
                alertDialog.setMessage("No of Persons - "+count);
                ScrollView sv = new ScrollView(personcount.this);
               l1 = new LinearLayout(personcount.this);


                l1.setOrientation(LinearLayout.VERTICAL);
                sv.addView(l1);
                alertDialog.setView(sv);
                for(int i=0;i<count;i++)
                {
                    tv1=new EditText(personcount.this); //
                    tv1.setId(i);
            Toast.makeText(personcount.this,String.valueOf(tv1.getId()),Toast.LENGTH_SHORT).show();
                    tv1.setHint("Person " + (i+1));
                   l1.addView(tv1);

                }


                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < count; i++) {
                                    tv1 = l1.findViewById(i);
                                    m= tv1.getText().toString().trim();
                                    Log.e("Error", "onClick: "+m );
                                    spersondetails.add(m);
                                }
                                editor.putStringSet("persondetails",spersondetails);
                                editor.apply();
                                startActivity(new Intent(personcount.this,persondetails.class));
                            }
                        });
                alertDialog.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }

        });
            }



}
