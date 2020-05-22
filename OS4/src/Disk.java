import java.util.HashMap;
import java.util.Map;

public class Disk {
	private static int diskSize;
	private static Sector[] disk;
	private static Map<Integer, Integer> fat;// физ-след

	public Disk(int size) {
		diskSize = size;
		disk = new Sector[diskSize];
		fat = new HashMap<Integer, Integer>();
	}

	public static Sector[] getDisk() {
		return disk;
	}

	public static int getEmpty() {
		for (int i = 0; i < disk.length; i++) {
			if (disk[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public static void nAct() {
		for(int i=0; i<disk.length;i++){
			if(disk[i]!=null){
			disk[i].isAct=false;
			disk[i].isChoose=false;}
		}
	}

	public static int addSector(int i, Sector s) {
		if (s == null) {
			fat.put(i, -1);
		}
		if (disk[i] == null) {
			disk[i] = s;
			fat.put(i, getEmpty());
			return getEmpty();
		}
		return -1;
	}

	public static void choose(int firstSector) {
		disk[firstSector].isChoose=true;
		int next=fat.get(firstSector);
		while(!disk[next].data.equals("-1")){
			disk[next].isChoose=true;
			next=fat.get(next);
		}
		disk[next].isChoose=true;
	}

	public static void delete(int firstSector) {
		// TODO Auto-generated method stub
		disk[firstSector]=null;
		int next=fat.get(firstSector);
		while(!disk[next].data.equals("-1")){
			disk[next]=null;
			next=fat.get(next);
		}
		disk[next]=null;
	}

	
}
