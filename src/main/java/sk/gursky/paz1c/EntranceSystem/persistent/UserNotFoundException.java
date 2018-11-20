package sk.gursky.paz1c.EntranceSystem.persistent;

public class UserNotFoundException extends RuntimeException {

	private long id;
	
	public UserNotFoundException(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
}
