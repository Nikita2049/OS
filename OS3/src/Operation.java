import java.util.ArrayList;

public class Operation {
	//������� ������� ����������� ������
    private TablePages tableVirtualMemory;
    //������� ���������� ������ ���������� ������
    private ArrayList<Page> tableRealMemory;
    // ����������� ���������� ������
    private int maxFrames;
    private ArrayList<Integer> loaning = new ArrayList<>();    

    public Operation (int sizeOfRAM, int sizeOfPage){
    	// �������� ����������� ������
        tableVirtualMemory = new TablePages();
        //������� ���������� ������ ���������� ������
        tableRealMemory = new ArrayList<Page>();
        // ����������� ���������� ������
        maxFrames = sizeOfRAM / sizeOfPage;
        // ������� ����������� ������� � 2 ���� ������ ���������� ������
        for(int i = 0; i < (sizeOfRAM * 2) / sizeOfPage; i++){
        	// ������ �����
        	Page page = new Page(false);
            page.setIndexRealPage(-43424);
            tableVirtualMemory.add(page);
        }
    }

    @SuppressWarnings("unchecked")
	public void insertIntoPhysicalMemory(int pageIndex){
    	Object[] resultObjects;
    	LeastRecentlyUsed algorithm = new LeastRecentlyUsed(tableVirtualMemory, tableRealMemory, loaning);
    	//��������� ��������� � ������������ ��������� ������������ �������� � ������� pageIndex
    	Page page = tableVirtualMemory.get(pageIndex);
        if(!page.isAvailability()){
        	// ���� ���� ����� � ���������� ������
        	if(tableRealMemory.size() < maxFrames){
        		// ��������, ��� ������������ � ���������� ������
        		page.setAvailability(true);
        		// ��������� �������� � ����� ���������� ������
        		tableRealMemory.add(page);
        		// ���������� ������ � ���������� ������
        		int indexOfPageFrames = tableRealMemory.indexOf(page);
                page.setIndexRealPage(indexOfPageFrames);
                // ��� ���������� ��������� ������ ������������ ������, ���� ��������
                loaning.add(pageIndex);
            // ���� ����������� ����� � ���������� ������
        	} else if(tableRealMemory.size() == maxFrames){
                resultObjects = algorithm.leastRecentlyUsed(page);
                loaning.add(pageIndex);
                tableRealMemory = (ArrayList<Page>)resultObjects[0];
                tableVirtualMemory.setPagesRecords((ArrayList<Page>)resultObjects[1]);
                loaning = (ArrayList<Integer>)resultObjects[2];
            }
        }
        printFrames();
        printVirtualPages();
    }   

    public int getTableVirtualPagesSize(){
        return tableVirtualMemory.size();
    }

    public void printFrames(){
        int i = 0;
        System.out.println("���������� ������");
        for (Page frame : tableRealMemory) {
            System.out.println("i = " + i + "; �������� ����������� " + frame.getIndexRealPage());
            i++;
        }
        System.out.println();
    }

    private void printVirtualPages(){
    	int i = 0;
        System.out.println("���������� ������");
        for (Page page : tableVirtualMemory.getPagesRecords()) {
        	System.out.println("i = " + i + "; �������� ����������� = "
                    + page.getIndexRealPage() + "; IndexFrame = " + page.getIndexRealPage());
        	i++;
		}
        System.out.println();
    }
}