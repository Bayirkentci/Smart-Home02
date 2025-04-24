package device;

public class Rule {
    private final String deviceName;
    private final String time; // در فرمت HH:MM
    private final String action; // "on" یا "off"

    public Rule(String deviceName, String time, String action) {
        this.deviceName = deviceName;
        this.time = time;
        this.action = action.toLowerCase();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return deviceName + " " + time + " " + action;
    }
}