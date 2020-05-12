public class PhysicalMemory {
	private static int size = 6;
	private static Page[] physMem = new Page[size];

	public int getSize() {
		return size;
	}

	public Page[] getPhisMem() {
		return physMem;
	}

	public Page getPhisPage(int i) {
		if (0 <= i & i < size) {
			return physMem[i];
		}
		return null;
	}

	public boolean setPhisPage(Page p, int i) {
		if (0 <= i & i < size) {
			physMem[i] = p;
			return true;
		}
		return false;
	}

	public static void Out() {
		System.out.print("phis: ");
		for (int i = 0; i < size; i++) {
			if (physMem[i] != null) {
				System.out.print(physMem[i].getData()+"/"+physMem[i].getFrequency()+" ");
			}else{
				System.out.print("__null__");
			}
		}
		System.out.println();
	}
}
