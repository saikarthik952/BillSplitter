package billsplitter.vamshi.official.billsplitter;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karthik on 1/20/2018.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="ITC_WOW";
    private static final int DATABASE_VERSION = 1;
    private static final String STUDENT_TABLE = "Visit";
    private static final String STU_TABLE = "create table "+"itemdata" +"(itemname TEXT,itemcost TEXT)";
Context context;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS propagation");
        db.execSQL("DROP TABLE  IF EXISTS hubcollection");
        // Create tables again
        onCreate(db);
    }





    void hubinsert(String itemname,int itemcost)
    {   SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();


        values.put("itemname", itemname);
        values.put("itemcost",itemcost);








        db.insert("itemdata", null, values);
        // 4. close
        db.close();
        //Toast.makeText(context, "values inserted", Toast.LENGTH_LONG).show();
        Log.i("insert into DB", "After insert");

    }
    List<itemdetails> hubretreive()
    {
        List<itemdetails> modelList = new ArrayList<itemdetails>();
        String query = "select * from itemdata";

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query,null);
        Log.e("Error", String.valueOf(cursor));
        if (cursor.moveToFirst()){
            do {
                itemdetails model = new itemdetails();

                model.setItemname(cursor.getString(0));
                model.setItemcost(cursor.getString(1));

                Log.e("itemdata", DatabaseUtils.dumpCursorToString(cursor));
                modelList.add(model);
            }while (cursor.moveToNext());
        }





        return modelList;
    }
    public long getProfilesCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "itemdata");
        db.close();
        return count;
    }
    void removeall()
    {
        SQLiteDatabase db =getWritableDatabase();
        db.delete("itemdata",null,null);
    }

}
