package device;

public enum Protocol {
    WIFI,
    BLUETOOTH;

    public static Protocol fromString(String s) {
        try {
            return Protocol.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}