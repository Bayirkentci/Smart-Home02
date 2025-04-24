package device;

public class Light extends SmartDevice {
    private int brightness;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
        this.brightness = 50;
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
        } else if (property.equalsIgnoreCase("brightness")) {
            try {
                int b = Integer.parseInt(value);
                if (b < 0 || b > 100) return "invalid value";
                this.brightness = b;
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
        return String.format("%s %s %d%% %s", name, statusStr, brightness, protocol.name());
    }
}