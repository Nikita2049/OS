import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	int size = 40;
	//int k = 400 / size;
	
	Color isEmpty=new Color(200,200,200); //if null
	Color isAct=new Color(0,0,255);
	Color isChoose=new Color(255,0,0);
	Color isExist=new Color(0,255,0);
	Color isCatalog=new Color(255,0,255);
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int curx=0;
		int cury=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<10;j++){
				g.setColor(chooseColor(i*10+j));
				//g.fillRect(curx, cury,curx+size , cury+size);
				g.fillRect(curx, cury,size , size);
				g.setColor(new Color(0,0,0));
				//g.drawRect(curx, cury, curx+size, cury+size);
				g.drawRect(curx, cury,size , size);
				curx+=size;
			}
			curx=0;
			cury+=size;
		}
	}

	private Color chooseColor(int n) {
		Sector [] d=Disk.getDisk();
		if(Disk.getDisk()[n]==null){
			return isEmpty;
		}
		
		if(Disk.getDisk()[n].isChoose){
			return isChoose;
		}
		if(Disk.getDisk()[n].isAct){
			return isAct;
		}
		if(Disk.getDisk()[n].isCatalog){
			return isCatalog;
		}
		return isExist;
	}

}
