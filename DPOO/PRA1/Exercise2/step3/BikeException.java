package Exercise2.Step3;

import Exercise2.Step3.*;

public class BikeException extends Exception {

	public static final String COMPLEMENT_ALREADY_EXISTS =
		"Complement Already Exists";

	
				
	public BikeException(String m) {
		super(m);
	}

	public BikeException(String m, Throwable cause) {
		super(m,cause);
	}

	public String toString() {
		return this.getMessage();
	}

}
