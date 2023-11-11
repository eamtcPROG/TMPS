package services;

public class LoggingService {
    public void logActivity(String serviceName,String objId, String activity) {
        System.out.println("Log-" + serviceName + " - "+objId+ " : " + activity);
    }
}
