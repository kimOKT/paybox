
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateManager {
    
    public String getDateActuel(){
        String dateString = "";
        DateFormat dateFormat = new SimpleDateFormat("ddMMYYYYHHmmss");
        Date date = new Date();
        dateString = dateFormat.format(date);
        System.out.println(dateString);
        return dateString;
    }
}
