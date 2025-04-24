import device.SmartDevice;

public class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    @Override
    public String setProperty(String property, String value) {
        if (property.equalsIgnoreCase("status")) {
            if (value.equalsIgnoreCase("on")) {
                turnOn();
                return "device updated successfully";
            } else if (value.equalsIgnoreCase("off")) {
                turnOff();
                return "device updated successfully";
            } else {
                return "invalid value";
            }
        } else if (property.equalsIgnoreCase("temperature")) {
            try {
                int t = Integer.parseInt(value);
                if (t < 10 || t > 30) return "invalid value";
                this.temperature = t;
                return "device updated successfully";
            } catch (NumberFormatException e) {
                return "invalid value";
            }
        } else {
            return "invalid property";
        }
    }

    @Override
    public String toString() {
        String statusStr = isOn ? "on" : "off";
        return String.format("%s %s %dC %s", name, statusStr, temperature, protocol.name());
    }
}