public class Sector {
	String data = "";
boolean isEmpty=true;
boolean isAct=false;
boolean isChoose=false;
boolean isCatalog=false;
	
	public Sector(String data) {
		this.data = data;
		isEmpty=false;
		isAct=true;
	}
	public Sector(String data, boolean isCat) {
		this.data = data;
		this.isCatalog=isCat;
		isEmpty=false;
		isAct=true;
	}
}
