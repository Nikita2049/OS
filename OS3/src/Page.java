public class Page {

	private boolean availability;
	private int R;
	private int time;
	private int nurrentTime = 10;
	private int indexRealPage;
	
	public Page (int R, boolean availability, int time) {
		this.R = R;
		this.time = time;
		this.availability = availability;
	}
	
	public void setTime() {
		this.time = nurrentTime; 
	}
	
	public int getTime() {
		return time;
	}
	
	public void setR(int R) {
		this.R = R;
	}
	
	public int getR() {
		return R;
	}
	
	public int getIndexRealPage() {
		return indexRealPage;
	}
	
	public boolean getAvailability() {
		return availability;
	}
	
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
}