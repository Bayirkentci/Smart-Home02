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

    public String addRule(String name, String time, String action) {
        if (!devices.containsKey(name))
            return "device not found";

        if (!isValidTimeFormat(time))
            return "invalid time";

        if (!action.equalsIgnoreCase("on") && !action.equalsIgnoreCase("off"))
            return "invalid action";

        rules.putIfAbsent(name, new ArrayList<>());

        for (Rule rule : rules.get(name)) {
            if (rule.getTime().equals(time))
                return "duplicate rule";
        }

        rules.get(name).add(new Rule(name, time, action));
        return "rule added successfully";
    }

    public String checkRules(String time) {
        if (!isValidTimeFormat(time))
            return "invalid time";

        for (String deviceName : rules.keySet()) {
            for (Rule rule : rules.get(deviceName)) {
                if (rule.getTime().equals(time)) {
                    SmartDevice device = devices.get(deviceName);
                    if (device != null) {
                        if (rule.getAction().equals("on"))
                            device.turnOn();
                        else
                            device.turnOff();
                    }
                }
            }
        }
        return "rules checked";
    }

    public String listRules() {
        StringBuilder sb = new StringBuilder();
        for (List<Rule> ruleList : rules.values()) {
            for (Rule rule : ruleList) {
                sb.append(rule.toString()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private boolean isValidTimeFormat(String time) {
        if (!time.matches("\\d{2}:\\d{2}")) return false;
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int min = Integer.parseInt(parts[1]);
        return hour >= 0 && hour <= 23 && min >= 0 && min <= 59;
    }
}