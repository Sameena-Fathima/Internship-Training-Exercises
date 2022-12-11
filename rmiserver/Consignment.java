package rmiserver;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

public class Consignment {
	public String getDeliveryInfo(int distance,int speed,String invdate) {
		LocalDate startdate = LocalDate.parse(invdate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		double totaltime=distance/speed;
		int days=(int) Math.ceil(totaltime/24);
		int count=0;
		LocalDate enddate=startdate.plusDays(days);
		LocalDate d=startdate.plusDays(1);

		while (d.isBefore(enddate)) {
			if(isWeekend(d)) {
				count++;
			}
			d=d.plusDays(1);
		}
		
				
		enddate=enddate.plusDays(count);
		if(isWeekend(enddate)) {
			if(enddate.getDayOfWeek()==DayOfWeek.SATURDAY)
				enddate=enddate.plusDays(2);
			
			else
				enddate=enddate.plusDays(1);
		};
		
		String arrivaldate=formatter.format(enddate);
		return arrivaldate;
	}
	
	public boolean isWeekend(LocalDate date) {
		DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
		boolean flag=false;
		switch(day) {
		case SATURDAY:
        case SUNDAY:
        	flag=true;
        	break;
		}
		return flag;
	}
}
