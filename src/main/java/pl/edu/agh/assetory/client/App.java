package pl.edu.agh.assetory.client;

public class App {
    public static void main(String[] args) {
        DataSendingScheduler dataSendingScheduler = new DataSendingScheduler(args[0]);
        dataSendingScheduler.scheduleDataSending();
    }
}