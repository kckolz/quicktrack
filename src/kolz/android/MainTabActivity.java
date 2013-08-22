package kolz.android;

import kolz.android.activities.BodyPartActivity;
import kolz.android.activities.ReviewActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Resources res = getResources();  // resource obj. to get drawables
        final TabHost tabHost = getTabHost();  // the activity's tab host
        TabHost.TabSpec spec;  // reusable tab spec for each tab.
        
        
        // Initialize a Tab Spec for each of the 2 tabs and add each to the TabHost.
        // for the BodyPart Tab.
       final Intent bodyPartIntent = new Intent().setClass(this, BodyPartActivity.class);       
       spec = tabHost.newTabSpec("bodypart")
       		.setIndicator("Body Part", res.getDrawable(R.drawable.ic_tab_artists))
       		.setContent(bodyPartIntent);
       tabHost.addTab(spec);
       
       // for the review Tab.
       final Intent reviewIntent = new Intent().setClass(this, ReviewActivity.class);
       spec = tabHost.newTabSpec("review")
      		.setIndicator("Workout Review", res.getDrawable(R.drawable.ic_tab_artists))
      		.setContent(reviewIntent);
       tabHost.addTab(spec);
       
       
       // finally, set the default tab
       tabHost.setCurrentTab(0);
    }

}
