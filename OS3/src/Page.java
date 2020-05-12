public class Page {
	private String data = "";
	private int frequency=0;

	public Page(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public int getFrequency() {
		return frequency;
	}

	public void use() {
		frequency++;
	}
}