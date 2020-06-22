import java.util.Comparator;

public class PubDateDescComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Book b1 = (Book)o1;
        Book b2 = (Book)o2;
        if (b1.getYear() < b2.getYear()){
            return 1;
        }else if (b1.getYear() > b2.getYear()){
            return -1;
        }
        return  (b1.getTitle().compareTo(b2.getTitle())); // utilizing String’s compareTo
    }
}
