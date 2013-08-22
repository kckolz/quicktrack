package kolz.android.activities;

import java.util.ArrayList;
import java.util.List;

import kolz.android.R;
import kolz.android.adapters.SetAdapter;
import kolz.android.dao.DBHelper;
import kolz.android.domain.Exercise;
import kolz.android.domain.Set;
import kolz.android.wheel.ArrayWheelAdapter;
import kolz.android.wheel.OnWheelChangedListener;
import kolz.android.wheel.OnWheelScrollListener;
import kolz.android.wheel.WheelView;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SetViewActivity extends Activity {

	// TODO: Externalize string-array
//	String wheelMenu1[] = new String[]{"Right Arm", "Left Arm", "R-Abdomen", "L-Abdomen", "Right Thigh", "Left Thigh"};
//	String wheelMenu2[] = new String[]{"Upper", "Middle", "Lower"};
//	String wheelMenu3[] = new String[]{"R", "L"};
	
	//Add string arrays to wheels
	private String wheelMenu1[]; 
	private String wheelMenu2[]; 
	private String wheelMenu3[];

	// Wheel scrolled flag
	private boolean wheelScrolled = false;

	private TextView text;
	private ListView setList;
	private EditText text1;
	private EditText text2;
	//private EditText text3;
	
	private List<Set> sets;
	private Set currentSet;
	private Exercise exercise;
	private ArrayAdapter<Set> setAdapter;
	
	private DBHelper db;

	@Override
	public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.test_wheel_layout);
			
			Resources res = getResources();
			wheelMenu1 = res.getStringArray(R.array.reps);
			wheelMenu2 = res.getStringArray(R.array.weight);
			wheelMenu3 = res.getStringArray(R.array.weight_modifier);

			initWheel1(R.id.p1);
			initWheel2(R.id.p2);
			initWheel3(R.id.p3);

			text1 = (EditText) this.findViewById(R.id.r1);
			text2 = (EditText) this.findViewById(R.id.r2);
			//text3 = (EditText) this.findViewById(R.id.r3);
			//text = (TextView) this.findViewById(R.id.result);
			
			setList = (ListView) findViewById(R.id.setlist);
			sets = new ArrayList<Set>();
			setAdapter = new SetAdapter(this,R.layout.setrow,sets);
			setList.setAdapter(setAdapter);
			
			exercise = (Exercise) getIntent().getExtras().get("exercise");
			
			db = new DBHelper(this);
			
			final Button saveSetButton = (Button) findViewById(R.id.savesetbutton);
			saveSetButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					if(currentSet != null) {
						db.saveSet(currentSet);
						sets.add(currentSet);
						setAdapter.notifyDataSetChanged();
					}
				}
			});
		}

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener()
		{
			public void onScrollStarts(WheelView wheel)
				{
					wheelScrolled = true;
				}

			public void onScrollEnds(WheelView wheel)
				{
					wheelScrolled = false;
					updateStatus();
				}
		};

	// Wheel changed listener
	private final OnWheelChangedListener changedListener = new OnWheelChangedListener()
		{
			public void onChanged(WheelView wheel, int oldValue, int newValue)
				{
					if (!wheelScrolled)
						{
							updateStatus();
						}
				}
		};

	/**
	 * Updates entered PIN status
	 */
	private void updateStatus()
		{
			text1.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()]);
			Double result = Double.parseDouble(wheelMenu2[getWheel(R.id.p2).getCurrentItem()]) + Double.parseDouble(wheelMenu3[getWheel(R.id.p3).getCurrentItem()]);
			text2.setText(result.toString());
			//text3.setText(wheelMenu3[getWheel(R.id.p3).getCurrentItem()]);

			//text.setText(wheelMenu1[getWheel(R.id.p1).getCurrentItem()] + " - " + result.toString());
			
			currentSet = new Set(Integer.parseInt(wheelMenu1[getWheel(R.id.p1).getCurrentItem()]), result.doubleValue(), exercise);
			
		}

	/**
	 * Initializes wheel
	 * 
	 * @param id
	 *          the wheel widget Id
	 */

	private void initWheel1(int id)
		{
			WheelView wheel = (WheelView) findViewById(id);
			wheel.setAdapter(new ArrayWheelAdapter<String>(wheelMenu1));
			wheel.setVisibleItems(4);
			wheel.setCurrentItem(0);
			wheel.addChangingListener(changedListener);
			wheel.addScrollingListener(scrolledListener);
		}

	private void initWheel2(int id)
		{
			WheelView wheel = (WheelView) findViewById(id);
			wheel.setAdapter(new ArrayWheelAdapter<String>(wheelMenu2));
			wheel.setVisibleItems(4);
			wheel.setCurrentItem(0);
			wheel.addChangingListener(changedListener);
			wheel.addScrollingListener(scrolledListener);
		}

	private void initWheel3(int id)
		{
			WheelView wheel = (WheelView) findViewById(id);

			wheel.setAdapter(new ArrayWheelAdapter<String>(wheelMenu3));
			wheel.setVisibleItems(4);
			wheel.setCurrentItem(0);
			wheel.addChangingListener(changedListener);
			wheel.addScrollingListener(scrolledListener);
		}

	/**
	 * Returns wheel by Id
	 * 
	 * @param id
	 *          the wheel Id
	 * @return the wheel with passed Id
	 */
	private WheelView getWheel(int id)
		{
			return (WheelView) findViewById(id);
		}

	/**
	 * Tests wheel value
	 * 
	 * @param id
	 *          the wheel Id
	 * @param value
	 *          the value to test
	 * @return true if wheel value is equal to passed value
	 */
	private int getWheelValue(int id)
		{
			return getWheel(id).getCurrentItem();
		}
}
