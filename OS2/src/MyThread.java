public class MyThread {
	
	private String description = "Поток ";
	private int maxTime;
	private int currentTime = 0;

	public MyThread(String description, int maxTime) {
		this.description += description;
		this.maxTime = maxTime;
	}

	public int getCurrentTime() {
		return currentTime;
	}


	public int getMaxTime() {
		return maxTime;
	}

	public String getDescription() {
		return description;
	}

	public void makeThread() {
		currentTime++;
		System.out.println(getInfo());
	}

	public boolean needTime() {
		if (maxTime > currentTime) {
			return true;
		} else {
			return false;
		}
	}

	public String getInfo() {
		return description + " maxTime:"
				+ maxTime + " currentTime:" + currentTime;
	}
}
