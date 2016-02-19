package lt.swedforms.Entities;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Super on 2/19/2016.
 */
public class Registration {
    @Id
    private String id;

    private User user;
    private Date date;
    private String banksection;
    private String topic;
    private String phoneNumber;
    private String comments;
    private String name;
    private String surname;

    public Registration() {}

    public Registration(User user, Date date, String banksection, String topic, String phoneNumber, String comments, String name, String surname) {
        this.user = user;
        this.date = date;
        this.banksection = banksection;
        this.topic = topic;
        this.phoneNumber = phoneNumber;
        this.comments = comments;
        this.name = name;
        this.surname = surname;
    }
}
