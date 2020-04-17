import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {
	
	private static ArrayList<MyProcess> myProcesses = new ArrayList<MyProcess>();
	private static Random rnd = new Random();
	private static int quant = 10;

	public static void main(String[] args) {
		create();
		info();
		start();
	}

	private static void start() {
		if (quant < 5) {
			System.out.println("Квант времени меньше 1");
			return;
		}
		
		Collections.sort(myProcesses); 
	
		while (!myProcesses.isEmpty()) {	
			int sizeProcesses = myProcesses.size();
			for (int i = 0; i < sizeProcesses; i++) {
				if (myProcesses.get(i).isHaveTime()) {
					if (!myProcesses.get(i).isEmpty()) {
						myProcesses.get(i).makeProcess(quant);
					} else {
						System.out.println("Все потоки "
								+ myProcesses.get(i).getDescription()
								+ "  выполнены");
						myProcesses.remove(i);
						sizeProcesses = myProcesses.size();
						i--;
					}
				} else {
					myProcesses.remove(i);
					sizeProcesses = myProcesses.size();
					i--;
				}
			}
		}
		System.out.println("Все процессы выполнены");
	}

	private static void info() {
		for (int i = 0; i < myProcesses.size(); i++) {
			System.out.println(myProcesses.get(i).getDescription()+ " Приор  "+ myProcesses.get(i).getPrior()
					+ " Потоков: " + myProcesses.get(i).getCountOfThreads());
		}
		System.out.println();
	}

	private static void create() {
		for (int i = 0; i < rnd.nextInt(5) + 1; i++) {
			myProcesses.add(new MyProcess("" + i, rnd.nextInt(50) + 1, rnd.nextInt(50)));
		}
	}

}
