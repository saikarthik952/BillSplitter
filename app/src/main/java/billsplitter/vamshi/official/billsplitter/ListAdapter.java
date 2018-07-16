package billsplitter.vamshi.official.billsplitter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<itemdetails> {
    public ListAdapter(Context context, ArrayList<itemdetails> users) {
        super(context, 0, users);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        itemdetails user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_data, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.itemn);
        TextView tvHome = (TextView) convertView.findViewById(R.id.itemc);
        // Populate the data into the template view using the data object
        tvName.setText(user.itemname);
        tvHome.setText(""+user.itemcost);
        // Return the completed view to render on screen
        return convertView;
    }
}