
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class FlightFinder {

	private Map<String, List<Flight>> allFlights = new HashMap<>();

	public FlightFinder(Map<String, List<Flight>> allFlights) {
		this.allFlights = allFlights;
	}

	public List<NavigableSet<Flight>> findFlights(int dayOfMonth, int month, int year, 
	        int preferredDepartureStartHour, int preferredDepartureEndHour, 
	        String departureCity, String arrivalCity, String finalArrivalCity,
			String departureCityTimeZone, String arrivalCityTimeZone) {
		
		List<NavigableSet<Flight>> result = new ArrayList<>();
        
        // Step 1: Construct ZonedDateTime objects to represent User-specified time interval when flights depart
		LocalDateTime intervalBegin = LocalDateTime.of(year, month, dayOfMonth, preferredDepartureStartHour, 0);
		LocalDateTime intervalEnd = LocalDateTime.of(year, month, dayOfMonth, preferredDepartureEndHour, 0);
        LocalDateTime earliestFlight =  intervalEnd;
		

        // Step 2: Find departing flights at departureCity
        List<Flight> allDepartingFlights = allFlights.get(departureCity);
        
        NavigableSet<Flight> departingflights = new TreeSet<>();
        
                   // Your code
                   // Tip: Methods like isAfter can be used to find flights in the specified user time interval
        for(Flight flight : allDepartingFlights)
        {
        	if ((flight.getDepartureTime().isAfter(intervalBegin) && flight.getDepartureTime().isBefore(intervalEnd)) ||
        			flight.getDepartureTime().isEqual(intervalBegin) || flight.getDepartureTime().isEqual(intervalEnd))
        	{
        		if (flight.getArrivalCity().equals(arrivalCity))
        		{
        			departingflights.add(flight);
        			if (flight.getArrivalTime().isBefore(earliestFlight))
					{
        				earliestFlight = flight.getArrivalTime();
					}
        		}
        	}
        }
        
        // Step 3: Find connecting flights
        //   Constraint 1: Departing at arrivalCity (e.g., Dubai) and arrive at finalArrivalCity (e.g., Mumbai)
        //   Constraint 2: Should start at least two hours after the arrival time of the first flight in the above navigable set

        List<Flight> allConnectingFlights = allFlights.get(arrivalCity);        
        
        NavigableSet<Flight> connectingflights = new TreeSet<>();      
       
        LocalDateTime earliestPossible = earliestFlight.plusHours(2);
        for(Flight flight: allConnectingFlights)
        {
        	if (flight.getDepartureTime().isAfter(earliestPossible) || flight.getDepartureTime().isEqual(earliestPossible))
        	{
        		connectingflights.add(flight);
        	}
        }
        
        result.add(departingflights);
        result.add(connectingflights);
        
        return result;
	}
	
	public static void main(String[] args) {
	    Flight f1 = new Flight(1, "1", "Emirates", "New York", "Dubai",
			LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f2 = new Flight(2, "2", "Delta", "San Francisco", "Paris",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f3 = new Flight(3, "3", "Delta", "San Francisco", "Rome",
				LocalDateTime.of(2017, 12, 20, 9, 0), LocalDateTime.of(2017, 12, 20, 9, 0));
		Flight f4 = new Flight(4, "4", "Emirates", "San Francisco", "Dubai",
				LocalDateTime.of(2017, 12, 20, 8, 0), LocalDateTime.of(2017, 12, 20, 8, 0));
		
		Map<String, List<Flight>> allFlights = new HashMap<>();
		
		allFlights.put("New York", Arrays.asList(f1));
		allFlights.put("San Francisco", Arrays.asList(f2, f3, f4));
		
		List<NavigableSet<Flight>> result = new FlightFinder(allFlights).findFlights(20, 12, 2017, 9, 13, "San Francisco", "Dubai", "Mumbai", "America/Los_Angeles", "Asia/Dubai");
	}

}
