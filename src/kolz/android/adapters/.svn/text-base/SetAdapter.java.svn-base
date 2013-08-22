package kolz.android.adapters;

import java.util.List;

import kolz.android.R;
import kolz.android.domain.Set;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SetAdapter extends ArrayAdapter<Set>{
	
	private List<Set> sets;

	public SetAdapter(Context context, int textViewResourceId, List<Set> sets) {
		super(context, textViewResourceId, sets);
		this.sets = sets;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater layoutInflator = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = layoutInflator.inflate(R.layout.setrow, null);
            }
            Set set = sets.get(position);
            if (set != null) {
                    TextView setNumber = (TextView) view.findViewById(R.id.setnumber); 
                    TextView repNumber = (TextView) view.findViewById(R.id.repnumber);
                    TextView weight = (TextView) view.findViewById(R.id.weightnumber);
                    if (setNumber != null) {
                        setNumber.setText("Set:   "+Integer.toString(position +1));                          
                    }
                    if(repNumber != null) {
                    	repNumber.setText("Reps:   "+Integer.toString(set.reps));
                    }
                    if(weight != null) {
                    	weight.setText("Weight:   "+Double.toString(set.weight));
                    }
            }
            return view;
    }


}
