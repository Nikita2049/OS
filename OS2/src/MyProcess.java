import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class MyProcess implements Comparable<MyProcess> {

	Random rnd = new Random();

	private ArrayList<MyThread> myThreads;
	private String description = "Процесс ";
	private int maxTime;
	private int resultTime = 0;
	public int prior = 1;

	public MyProcess(String description, int maxTime, int prior) {
		this.prior = prior;
		this.description += description;
		this.maxTime = maxTime;
		myThreads = new ArrayList<MyThread>();
		for (int i = 0; i < rnd.nextInt(5) + 1; i++) {
			myThreads.add(new MyThread("" + i, rnd.nextInt(10) + 1));
		}
	}

	public int getCountOfThreads() {
		return myThreads.size();
	}

	public int getPrior() {
		return prior;
	}

	public boolean isEmpty() {
		if (myThreads.size() > 0)
			return false;
		return true;
	}

	public int getResultTime() {
		return resultTime;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public String getDescription() {
		return description;
	}

	public boolean isHaveTime() {
		if (getMaxTime() > getResultTime()) {
			return true;
		} else {
			return false;
		}
	}

	public void makeProcess(int quant) {

		if (!(quant > 0)) {
			System.out.println("Выделенный квант времени меньше 1");
			System.exit(0);
		}
		System.out.println();
		System.out.println(getDescription() + "  Время макс: " + maxTime + " Приор  " + getPrior());
		int sizeThreads = myThreads.size();
		int QsizeThreads = myThreads.size();
		int quant2 = quant;
		for (int i = 0, iq = 0; i < sizeThreads; i++, iq++) {
			MyThread thread = myThreads.get(i);
			int thQuant = quant / QsizeThreads;
			if (iq < quant % QsizeThreads) {
				thQuant++;
			}
			if (!thread.needTime()) {
				System.out.println(thread.getDescription() + " завершен");
				myThreads.remove(i);
				sizeThreads = myThreads.size();
				i--;
				break;
			}
			while (thQuant > 0) {
				if (thread.needTime() & isHaveTime() & quant2 > 0 & thQuant > 0) {
					thread.makeThread();
					thQuant--;
					resultTime++;
					quant2--;
				}
				if (!thread.needTime()) {
					System.out.println(thread.getDescription() + " завершен");
					myThreads.remove(i);
					sizeThreads = myThreads.size();
					i--;
					break;
				}
				if (!isHaveTime()) {
					System.out.println("Максимальное время " + getDescription() + " истекло");
					return;
				}
				if (thQuant <= 0) {
					System.out.println("Квант на " + thread.getDescription() + " истек");
					break;
				}
				if (quant2 <= 0) {
					System.out.println("---------------");
					return;
				}

			}
		}

		System.out.println();

	}

	public int compareTo(MyProcess other) {
        return other.getPrior() - this.getPrior();
    }
}
