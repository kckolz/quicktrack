package kolz.android.activities;


import java.util.ArrayList;
import java.util.List;

import kolz.android.R;
import kolz.android.domain.Exercise;
import kolz.android.enums.BodyPart;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class BodyPartActivity extends ListActivity {
	
	ImageView mainImage;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodypartlist);  
        

        final List<String> items = new ArrayList<String>();
        for(final BodyPart part: BodyPart.values()){
        	items.add(part.getName());
        }
        
        final ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.bodypartrow, items);
        this.setListAdapter(adapter);
     
    }
    
    @Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		String bodyPart = (String) getListView().getItemAtPosition(position);
		Intent intent = new Intent(this, ExerciseViewActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("bodypart", bodyPart);
		intent.putExtras(bundle);
		startActivity(intent);
	}
    
}