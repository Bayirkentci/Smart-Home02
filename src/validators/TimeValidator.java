package validators;

import exceptions.InvalidTimeException;

public class TimeValidator extends Validator {

    @Override
    public void validate(String time) {
        String[] parts = time.split(":");
        if (parts.length != 2) {
            throw new InvalidTimeException("invalid time");
        }

        try {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                throw new InvalidTimeException("invalid time");
            }

        } catch (NumberFormatException e) {
            throw new InvalidTimeException("invalid time");
        }
    }
}