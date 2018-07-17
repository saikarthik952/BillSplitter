package billsplitter.vamshi.official.billsplitter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

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
        setContentView(R.layout.activity_result); mshared = getSharedPreferences("personData", MODE_PRIVATE);
        db = new Database(Result.this);
        count=mshared.getInt("personcount",0);
        persondata=mshared.getStringSet("persondetails",null);
        persond = persondata.toArray(new String[persondata.size()]);
        itemdetails=new ArrayList<>();
        itemdetails=db.hubretreive();

    }
}
