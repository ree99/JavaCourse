import java.util.ArrayDeque;
import java.util.ArrayList; 
import java.util.Deque; 
import java.util.Iterator; 
import java.util.List;

public class TicketReservation {

    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;

    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();

    // This getter is used only by the junit test case. 
    public List<Passenger> getConfirmedList() {
        return confirmedList;     
    }

    /**      
     * Returns true if passenger could be added into either confirmedList or      
     * waitingList. Passenger will be added to waitingList only if confirmedList      
     * is full.      
     *      
     * Return false if both passengerList & waitingList are full      
     */     
     public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass,             String confirmationNumber) {       
        Passenger passenger = new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber);
    	if (confirmedList.size() < CONFIRMEDLIST_LIMIT)
        {
    		return confirmedList.add(passenger);
        }
    	else if (waitingList.size() < WAITINGLIST_LIMIT)
    	{
    		return waitingList.offer(passenger);
    	}
    	else
    	{
    	    return false;
    	}
    }

    /**      
     * Removes passenger with the given confirmationNumber. Passenger could be      
     * in either confirmedList or waitingList. The implementation to remove the      
     * passenger should go in removePassenger() method and removePassenger method      
     * will be tested separately by the uploaded test scripts.      
     
     * If passenger is in confirmedList, then after removing that passenger, the      
     * passenger at the front of waitingList (if not empty) must be moved into      
     * passengerList. Use poll() method (not remove()) to extract the passenger      
     * from waitingList.      
     */     
    public boolean cancel(String confirmationNumber) {
    	int confirmedPassenger = -1;
    	
    	for(int i = 0; i < confirmedList.size(); i++)
    	{
    		Passenger p = confirmedList.get(i);
    		if (p.getConfirmationNumber().equals(confirmationNumber))
    		{
    			confirmedPassenger = i;
    		}
    	}
    	
    	if (confirmedPassenger != -1)
    	{
    		Iterator<Passenger> it = confirmedList.iterator();
    		removePassenger(it, confirmationNumber);
    		if (!waitingList.isEmpty())
    		{
    			Passenger nextPassenger = waitingList.poll();
    			confirmedList.add(nextPassenger);
    		}
    		return true;
    	}
    	else
    	{
    		Iterator<Passenger> it = waitingList.iterator();
    		return removePassenger(it, confirmationNumber);
    	}
    }

    /**      
     * Removes passenger with the given confirmation number.      
     * Returns true only if passenger was present and removed. Otherwise, return false.      
     */     
    public boolean removePassenger(Iterator<Passenger> it, String confirmationNumber) {         
        while(it.hasNext())
		{
			Passenger p = it.next();
			if (p.getConfirmationNumber().equals(confirmationNumber))
			{
				it.remove();
				return true;
			}
		}
		return false;
    }

}
