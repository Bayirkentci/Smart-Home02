package device;

import java.util.*;

public class SmartHomeSystem {
    private Map<String, SmartDevice> devices = new LinkedHashMap<>();

    public String addDevice(String type, String name, String protocolStr) {
        if (devices.containsKey(name)) {
            return "duplicate device name";
        }

        Protocol protocol = Protocol.fromString(protocolStr);
        if (protocol == null) return "invalid input";

        SmartDevice device = null;
        switch (type.toLowerCase()) {
            case "light":
                device = new Light(name, protocol);
                break;
            case "thermostat":
                device = new Thermostat(name, protocol);
                break;
            default:
                return "invalid input";
        }

        devices.put(name, device);
        return "device added successfully";
    }

    public String setDevice(String name, String property, String value) {
        SmartDevice device = devices.get(name);
        if (device == null) return "device not found";
        return device.setProperty(property, value);
    }

    public String removeDevice(String name) {
        SmartDevice removed = devices.remove(name);
        if (removed == null) return "device not found";
        removeRulesByDevice(name);
        return "device removed successfully";
    }

    public String listDevices() {
        if (devices.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (SmartDevice device : devices.values()) {
            sb.append(device.toString()).append("\n");
        }
        return sb.toString().trim();
    }

    private Map<String, List<Rule>> rules = new LinkedHashMap<>();

    private void removeRulesByDevice(String name) {
        rules.remove(name);
    }
}