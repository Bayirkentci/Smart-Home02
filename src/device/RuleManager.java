package device;

import exceptions.*;
import validators.*;

import java.util.*;

public class RuleManager {
    private Map<String, SmartDevice> devices = new HashMap<>();
    private List<Rule> rules = new ArrayList<>();

    public String addRule(String name, String time, String action) {
        if (!devices.containsKey(name)) {
            throw new DeviceNotFoundException("device not found.");
        }

        new TimeValidator().validate(time);
        new ActionValidator().validate(action);

        Rule newRule = new Rule(name, time, action);
        if (rules.contains(newRule)) {
            throw new DuplicateElementException("duplicate rule");
        }

        rules.add(newRule);
        return "rule added successfully";
    }

    public String checkRules(String currentTime) {
        new TimeValidator().validate(currentTime);

        boolean ruleApplied = false;
        for (Rule rule : rules) {
            if (rule.getTime().equals(currentTime)) {
                applyAction(rule);
            }
        }

        return "rules checked";
    }

    private void applyAction(Rule rule) {
        SmartDevice device = devices.get(rule.getName());
        if (device != null) {
            device.setStatus(rule.getAction());
        }
    }

    public String listRules() {
        if (rules.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (Rule rule : rules) {
            result.append(rule.getName())
                    .append(" ")
                    .append(rule.getTime())
                    .append(" ")
                    .append(rule.getAction())
                    .append("\n");
        }
        return result.toString().trim();
    }

    public void addDevice(SmartDevice device) {
        devices.put(device.getName(), device);
    }
}