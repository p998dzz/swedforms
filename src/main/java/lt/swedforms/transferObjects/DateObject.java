package lt.swedforms.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Super on 2/19/2016.
 */
public class DateObject implements Serializable {
    Date date;
    List<String> times;

    public DateObject(Date date)
    {
        this.date = date;
        times = new ArrayList<>();
        for(int i = 8; i < 17; i++)
            times.add(i+":00");
    }

    public void removeTime(String time){
        int indexToDelete = -1;
        for(String existingTime : times)
        {
            if(existingTime == time) {
                indexToDelete = times.indexOf(existingTime);
                break;
            }
        }
        if(indexToDelete != -1)
            times.remove(indexToDelete);
    }

    public Date getDate() {
        return date;
    }
}
