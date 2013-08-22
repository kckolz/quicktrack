package kolz.android.activities;



import java.util.ArrayList;
import java.util.List;

import kolz.android.R;
import kolz.android.adapters.ExerciseAdapter;
import kolz.android.dao.DBHelper;
import kolz.android.domain.Exercise;
import kolz.android.enums.BodyPart;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ExerciseViewActivity extends ListActivity{
	
	private ExerciseAdapter adapter;
	private DBHelper db;
	private Runnable viewExercises;
	private ProgressDialog progressDialog;
	private List<Exercise> exercises;
	private BodyPart bodyPart;
	private LayoutInflater inflater;
	private String newExerciseName;
	private Exercise newExercise;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.exerciselist);

	    //Get a new DBHelper to retrieve items from database
	    db = new DBHelper(this);
	    
	    // the intent passed in from main activity.
	    final Intent intent = getIntent();
	    final Bundle bundle = intent.getExtras();
	    final String bodyPartVal = (String) bundle.get("bodypart"); //Get a bodyPart passed in the intent
	    bodyPart = BodyPart.valueOf(bodyPartVal.toUpperCase());
	    
	    //inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    final Button addExerciseButton = (Button) findViewById(R.id.addexercisebutton);
	    
	    /*View popupView = inflater.inflate(R.layout.exercisepopup,(ViewGroup)findViewById(R.layout.exerciselist));
	    final Button saveExerciseButton = (Button) popupView.findViewById(R.id.saveexercisebutton);*/
	    
		addExerciseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {		
				Intent i = new Intent(ExerciseViewActivity.this,AddExerciseActivity.class);    
				startActivityForResult(i,0);
				
			    /*PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.exercisepopup, null, false), 100,  100, true);
			    pw.setFocusable(true);
			    pw.setAnimationStyle(android.R.style.Animation_Dialog);
			    pw.showAtLocation(ExerciseViewActivity.this.findViewById(R.id.exerciselist), Gravity.CENTER, 0, 0);*/ 
			}
		});
		
	
	
		
		/*saveExerciseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				newExerciseName = (EditText) ExerciseViewActivity.this.findViewById(R.id.newexercise);
				newExercise = new Exercise(newExerciseName.getText().toString(), bodyPart);
				db.saveExercise(newExercise);	
			}
		});*/
	    
	    //TODO Remove this..for testing purposes only
	    //db.storeDefaultExercises(); 

	    //New up a list to pass to the adapter
		exercises = new ArrayList<Exercise>();
		
	    // new exercise adaptor gets list of the exercises it needs to display.
	    adapter = new ExerciseAdapter(this, R.layout.exerciserow, exercises);

		this.setListAdapter(adapter);

	    //Runnable used to retrieve items from DB on new thread
	    viewExercises = new Runnable() {
			@Override
			public void run() {
				getExercises();
			}
		};
	    
		//Launch thread to retrieve exercises from DB and show a progress dialog
		Thread thread = new Thread(null, viewExercises, "exerciseview thread");
		thread.start();
		progressDialog = ProgressDialog.show(ExerciseViewActivity.this,
				"Please wait...", "Retrieving data ...", true);
	}
	
	private void getExercises() {
		try {
			
			//TODO Remove this temporary logic.
			db.deleteAllExercises(); //temporary
			db.storeDefaultExercises(); //temporary
			this.exercises = db.loadExercisesByBodyPart(bodyPart);		
		} catch (Exception e) {
			Log.e("Get Exercises", e.toString());
		}
		runOnUiThread(addExercisesToAdapter);
	}
	

	private Runnable addExercisesToAdapter = new Runnable() {
		@Override
		public void run() {
			if (exercises != null && exercises.size() > 0) {
				for(Exercise exercise : exercises) {
					adapter.add(exercise);
				}
			}
			progressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {     
	  super.onActivityResult(requestCode, resultCode, data); 
	  switch(requestCode) { 
	    case (0) : { 
	      if (resultCode == Activity.RESULT_OK) { 
	    	  newExerciseName = data.getStringExtra("exercise");
	    	  if(newExerciseName != null) {
	    		  newExercise = new Exercise(newExerciseName, bodyPart);
	    		  db.saveExercise(newExercise);
	    	  }
	      } 
	      break; 
	    } 
	  } 
	}
	
	
	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		Exercise exercise = (Exercise) getListView().getItemAtPosition(position);
		Intent intent = new Intent(this, SetViewActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("exercise", exercise);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
}
