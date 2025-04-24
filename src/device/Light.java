package device;
import exceptions.*;

public class Light extends SmartDevice {
    private int brightness;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    @Override
    public String setProperty(String property, String value) {
        switch (property.toLowerCase()) {
            case "status" -> {
                if (value.equalsIgnoreCase("on")) turnOn();
                else if (value.equalsIgnoreCase("off")) turnOff();
                else throw new InvalidException("invalid value");
            }
            case "brightness" -> {
                try {
                    int val = Integer.parseInt(value);
                    if (val < 0 || val > 100)
                        throw new InvalidException("invalid value");
                    this.brightness = val;
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
        return name + " " + (isOn ? "on" : "off") + " " + brightness + "% " + protocol;
    }
}