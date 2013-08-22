package kolz.android.dao;

import java.util.List;

import kolz.android.domain.Exercise;
import kolz.android.domain.Set;
import kolz.android.enums.BodyPart;
import android.content.Context;
import android.util.Log;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class DBHelper {

	// public static long latestId = 0;
	private static ObjectContainer oc = null;
	private Context context;
	private static final String DB_FILE_NAME = "quicktrack_db.db40";


	public DBHelper(Context context) {
		this.context = context;
	}

	private ObjectContainer db() {
		try {
			if (oc == null || oc.ext().isClosed())
				//oc = Db4o.openFile(db4oDBFullPath(context));
				oc = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), db4oDBFullPath(context));
			return oc;
		} catch (Exception e) {
			Log.e(DBHelper.class.getName(), e.toString());
			return null;
		}
	}
	
	private String db4oDBFullPath(Context ctx) {
	      return ctx.getDir("data", 0) + "/" + DB_FILE_NAME;
	}

	/**
	 * Close database connection
	 */
	public void close() {
		if (oc != null) {
			oc.close();
			oc = null;
		}
	}
	
	
	public void saveExercise(Exercise exercise) {
    	db().store(exercise);
	    db().commit();
	}
	
	public void saveSet(Set set) {
    	db().store(set);
	    db().commit();
	}

	public List<Exercise> loadAllExercises() {
		return db().query(Exercise.class);
	}
	
	public void deleteAllExercises() {
		ObjectSet<Exercise> results = db().query(Exercise.class);
		for(Exercise exercise : results) {
			db().delete(exercise);
		}
	}
	
	public List<Set> loadAllSets() {
		return db().query(Set.class);
	}
	
	public void deleteAllSets() {
		ObjectSet<Set> results = db().query(Set.class);
		for(Set set : results) {
			db().delete(set);
		}
	}
	
	public List<Exercise> loadExercisesByBodyPart(final BodyPart bodyPart) {
		List<Exercise> list = db().query(new Predicate<Exercise>() {
			public boolean match(Exercise candidate) {
				if(candidate.bodyPart.equals(bodyPart)) {
					return true;
				}
				return false;
			}
		});
		return list;
	}
	
	
	public void storeDefaultExercises() {
	    Exercise ex1 = new Exercise("crunches", BodyPart.ABS);
	    Exercise ex2 = new Exercise("curls", BodyPart.ARMS);
	    Exercise ex3 = new Exercise("wide-grip pullups", BodyPart.BACK);
	    Exercise ex4 = new Exercise("bench press", BodyPart.CHEST);
	    saveExercise(ex1);
	    saveExercise(ex2);
	    saveExercise(ex3);
	    saveExercise(ex4);
	}
}
