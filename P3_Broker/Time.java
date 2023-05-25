import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public interface Time extends Remote {
    // Metodos de la interface
    default LocalDate get_date() throws RemoteException{
        return java.time.LocalDate.now();
    }
    default int get_hour() throws RemoteException {
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        return calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
    }

    default int get_month() throws RemoteException{
        Date date = new Date(); // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date); // assigns calendar to given date
        return calendar.get(Calendar.MONTH); // gets month number
    }

    default int get_year() throws RemoteException {
        Date date = new Date(); // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date); // assigns calendar to given date
        return calendar.get(Calendar.YEAR); // gets year
    }

    default int get_day() throws RemoteException {
        Date date = new Date(); // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date); // assigns calendar to given date
        return calendar.get(Calendar.DAY_OF_YEAR);
    }
}
