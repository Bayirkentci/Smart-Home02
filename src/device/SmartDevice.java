package device;
public abstract class SmartDevice {
    protected String name;
    protected boolean isOn;
    protected Protocol protocol;

    public SmartDevice(String name, Protocol protocol) {
        this.name = name;
        this.protocol = protocol;
        this.isOn = false;
    }

    public abstract String setProperty(String property, String value);

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public boolean isOn() {
        return isOn;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setStatus(String action) {
    }
}