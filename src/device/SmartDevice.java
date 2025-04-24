package device;

public abstract class SmartDevice {
    protected String name;
    protected Protocol protocol;
    protected boolean isOn;

    public SmartDevice(String name, Protocol protocol) {
        this.name = name;
        this.protocol = protocol;
        this.isOn = false;
    }

    public String getName() {
        return name;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public abstract String setProperty(String property, String value);

    @Override
    public abstract String toString();
}