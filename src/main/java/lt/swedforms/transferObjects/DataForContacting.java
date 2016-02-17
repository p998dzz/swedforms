package lt.swedforms.transferObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Super on 2/17/2016.
 */
public class DataForContacting {
    List<String> topics;
    List<String> contactTypes;

    public DataForContacting() {
        topics = new ArrayList<String>();
        contactTypes = new ArrayList<String>();
    }

    public void addTopic(String value){
        topics.add(value);
    }

    public void addContactType(String value){
        contactTypes.add(value);
    }
}
