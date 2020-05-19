import java.util.LinkedList;
import java.util.Queue;

public class Operation {

	private TablePages virtualMemory;
	private Queue<Page> pageQueue;
	
	public Operation (TablePages virtualMemory,Queue<Page> pageQueue) {
		this.virtualMemory = virtualMemory;
		this.pageQueue = pageQueue;
	}
	public Object[] SecondChance(Page page) {
		Object[] result = new Object[3];
		if (page.getR() == 0) {
			pageQueue.remove();
		}
		else {
			pageQueue.remove();
			pageQueue.add(page);
			page.setTime();
			page.setR(0);
			}
		 page.setAvailability(true);
	     result[0] = pageQueue;
         result[1] = virtualMemory.getPagesRecords();
		return result;
	}

}