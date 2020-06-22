import java.util.Comparator;

public class Book implements Comparable {
	private String title;
	private String author;
	private int year;
	public static final Comparator<Book> TITLE_COMPARATOR = new Comparator<Book>(){
	    public int compare(Book o1, Book o2)
	    {
	        return o1.getTitle().compareTo(o2.getTitle());
	    }
	};
	
	public static class PubDateComparators{
	    public static class PubDateAscComparator implements Comparator<Book>{
	        public int compare(Book o1, Book o2)
	        {
	            int yearDiff = o1.year - o2.year;
	            if (yearDiff != 0) return yearDiff;
	            return TITLE_COMPARATOR.compare(o1, o2);
	        }
	    }
	    
	    public static class PubDateDescComparator implements Comparator<Book>{
	        public int compare(Book o1, Book o2)
	        {
	            int yearDiff = o1.year - o2.year;
	            if (yearDiff != 0) return -yearDiff;
	            return TITLE_COMPARATOR.compare(o1, o2);
	        }
	    }
	}
		
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
		
	public int compareTo(Object book) {
		 return getTitle().compareTo(((Book)book).getTitle()); 
	}	
}

