package kolz.android.domain;

import java.io.Serializable;

import kolz.android.enums.BodyPart;

public class Exercise implements Serializable{


	private static final long serialVersionUID = -9052443111624362108L;
	public String name;
	public BodyPart bodyPart;
	public int id;
	public String  image;  // URL?
	
	public Exercise(String name, BodyPart bodyPart) {
		this.name = name;
		this.bodyPart = bodyPart;
	}
	
	public Exercise(String name, BodyPart bodyPart, String imageURL){
		this(name, bodyPart);
		this.image = imageURL;
	}


		
}
