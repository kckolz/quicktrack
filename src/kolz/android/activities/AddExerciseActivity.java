package kolz.android.activities;

import kolz.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class AddExerciseActivity extends Activity {
	
	private Intent resultIntent;
	private EditText editText;
	
	private static final String ACTIVITY_TITLE = "Add Exercise";
	
	@Override
	public void onCreate(Bundle savedInstanceState) { 
		
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.exercisepopup);
		
	    editText = (EditText)findViewById(R.id.newexercise);
	    
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,  
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	    
	    setTitle(ACTIVITY_TITLE);
	    
	    final Button saveExerciseButton = (Button) findViewById(R.id.saveexercisebutton);
	    
	    saveExerciseButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String newExercise = editText.getText().toString();
				resultIntent = new Intent();
				resultIntent.putExtra("exercise", newExercise);
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
			}
		});
	}

}
