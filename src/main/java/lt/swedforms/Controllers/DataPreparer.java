package lt.swedforms.Controllers;

import java.util.*;

/**
 * Created by Super on 2/17/2016.
 */
public class DataPreparer {
    public static List<List<String>> prepareForContacting()
    {
        List<List<String>> packet = new ArrayList<List<String>>();
        packet.add(prepareContactType());
        packet.add(prepareTopics());
        return packet;
    }

    private static List<String> prepareTopics(){
        List<String> topics = new ArrayList<String>();
        topics.add("topic1");
        topics.add("topic2");
        topics.add("topic3");
        return topics;
    }

    private static List<String> prepareContactType(){
        List<String> topics = new ArrayList<String>();
        topics.add("type1");
        topics.add("type2");
        topics.add("type3");
        return topics;
    }

    public static List<List<String>> prepareForRegistration() {
        List<List<String>> packet = new ArrayList<List<String>>();
        packet.add(prepareBankSections());
        packet.add(prepareTopics());
        return packet;
    }

    private static List<String> prepareBankSections() {
        List<String> sections = new ArrayList<String>();
        sections.add("section1");
        sections.add("section1");
        sections.add("section1");
        return sections;
    }
}
