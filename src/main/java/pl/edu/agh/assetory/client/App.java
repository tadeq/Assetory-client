package pl.edu.agh.assetory.client;

public class App {
    public static void main(String[] args) {
        DataSendingScheduler dataSendingScheduler = new DataSendingScheduler(args[0]);
        String[] time = args[1].split(":");
        if (time.length != 3) {
            throw new IllegalStateException("Wrong time format");
        }
        dataSendingScheduler.scheduleDataSending(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
    }
}