package observers;

import interfaces.Observer;

public class EmailNotificationObserver implements Observer {
    private String email;

    public EmailNotificationObserver(String email){
        this.email = email;
    }
    @Override
    public void update() {
        System.out.println("EmailNotificationObserver:"+ email);
    }
}

