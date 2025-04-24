package exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() { super("device not found."); }
    public DeviceNotFoundException(String message) {
        super(message);
    }
}
