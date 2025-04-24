package device;

import java.util.Objects;

public class Rule {
    private String name;
    private String time;
    private String action;

    public Rule(String name, String time, String action) {
        this.name = name;
        this.time = time;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getAction() {
        return action;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rule rule = (Rule) obj;
        return name.equals(rule.name) && time.equals(rule.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time);
    }
}