public class Page {
    private boolean availability;
    private int indexRealPage;    
    private boolean r;

    public Page(boolean availability){
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getIndexRealPage() {
        return indexRealPage;
    }

    public void setIndexRealPage(int indexRealPage) {
        this.indexRealPage = indexRealPage;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    public void setR(boolean r) {
        this.r = r;
    }
    
    public boolean isR() {
        return r;
    }
}