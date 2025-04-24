package device;
import exceptions.*;

public class Thermostat extends SmartDevice {
    private int temperature;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
        this.temperature = 20;
    }

    @Override
    public String setProperty(String property, String value) {
        switch (property.toLowerCase()) {
            case "status" -> {
                if (value.equalsIgnoreCase("on")) turnOn();
                else if (value.equalsIgnoreCase("off")) turnOff();
                else throw new InvalidException("invalid value");
            }
            case "temperature" -> {
                try {
                    int val = Integer.parseInt(value);
                    if (val < 10 || val > 30)
                        throw new InvalidException("invalid value");
                    this.temperature = val;
                } catch (NumberFormatException e) {
                    throw new InvalidException("invalid value");
                }
            }
            default -> throw new InvalidException("invalid property");
        }
        return "device updated successfully";
    }

    @Override
    public String toString() {
        return name + " " + (isOn ? "on" : "off") + " " + temperature + "C " + protocol;
    }
}