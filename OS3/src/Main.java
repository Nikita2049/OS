public class Main {
	 public static void main(String[] args) {   
		DispatcherOfMemory dispatcher = new DispatcherOfMemory(4096, 1024);
		int pages[] = {0, 1, 2, 3, 4, 5};
		for(int i = 0; i < pages.length; i++){
		      if (pages[i] >= 0 && pages[i] < dispatcher.getVirtualMemorySize()){
		    	  dispatcher.insert(pages[i]);
		      }
	     }
	 }
	
}