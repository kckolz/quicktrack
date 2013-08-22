package kolz.android.adapters;

import java.util.List;

import kolz.android.R;
import kolz.android.domain.Exercise;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExerciseAdapter extends ArrayAdapter<Exercise>{
	
	private List<Exercise> exercises;

	public ExerciseAdapter(Context context, int textViewResourceId, List<Exercise> exercises) {
		super(context, textViewResourceId, exercises);
		this.exercises = exercises;
	}
	
	 @Override
     public View getView(int position, View convertView, ViewGroup parent) {
             View view = convertView;
             if (view == null) {
                 LayoutInflater layoutInflator = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 view = layoutInflator.inflate(R.layout.exerciserow, null);
             }
             Exercise exercise = exercises.get(position);
             if (exercise != null) {
                     TextView textView = (TextView) view.findViewById(R.id.exercisename);
                 
                     if (textView != null) {
                           textView.setText(exercise.name);                            
                     }
             }
             return view;
     }
	 
}
