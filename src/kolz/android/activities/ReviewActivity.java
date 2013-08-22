package kolz.android.activities;

import java.util.List;

import kolz.android.R;
import kolz.android.adapters.ExerciseAdapter;
import kolz.android.adapters.ReviewAdapter;
import kolz.android.dao.DBHelper;
import kolz.android.domain.Exercise;
import kolz.android.domain.Set;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

public class ReviewActivity extends Activity {

	private ReviewAdapter adapter;
	private DBHelper db;
	private Runnable viewExercises;
	private ProgressDialog progressDialog;
	private List<Set> sets;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.reviewlist);

		// Get a new DBHelper to retrieve items from database
		db = new DBHelper(this);

		// Runnable used to retrieve items from DB on new thread
		viewExercises = new Runnable() {
			@Override
			public void run() {
				getSets();
			}
		};

		// Launch thread to retrieve exercises from DB and show a progress
		// dialog
		Thread thread = new Thread(null, viewExercises, "review thread");
		thread.start();
		progressDialog = ProgressDialog.show(ReviewActivity.this,
				"Please wait...", "Retrieving data ...", true);
	}

	private void getSets() {
		try {
			this.sets = db.loadAllSets();
		} catch (Exception e) {
			Log.e("Get Sets", e.toString());
		}
		runOnUiThread(addSetsToAdapter);
	}

	private Runnable addSetsToAdapter = new Runnable() {
		@Override
		public void run() {
			if (sets != null && sets.size() > 0) {
				for (Set set : sets) {
					adapter.addItem(set);
				}
			}
			progressDialog.dismiss();
			adapter.notifyDataSetChanged();
		}
	};
}
