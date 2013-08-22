package kolz.android.domain;

import java.util.Calendar;
import java.util.Date;

public class Set {
	
	public int reps;
	public double weight;
	public Exercise exercise;
	public Date timeStamp;

	public Set(int reps, double weight, Exercise exercise) {
		this.reps = reps;
		this.weight = weight;
		this.exercise = exercise;
		this.timeStamp = Calendar.getInstance().getTime();
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

}
