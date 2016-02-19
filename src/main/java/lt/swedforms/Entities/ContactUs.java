package lt.swedforms.Entities;

import org.springframework.data.annotation.Id;

/**
 * Created by Super on 2/19/2016.
 */
public class ContactUs {
    @Id
    private String id;
    private String topic;
    private User user;
    private String message;
    private String phoneNUmber;
    private String answerType;

    public ContactUs(String topic, User user, String message, String phoneNUmber, String answerType) {
        this.topic = topic;
        this.user = user;
        this.message = message;
        this.phoneNUmber = phoneNUmber;
        this.answerType = answerType;
    }
}
