package validators;
import exceptions.InvalidActionException;

public class ActionValidator {
    public void validate(String action) {
        if (!action.equals("on") && !action.equals("off")) {
            throw new InvalidActionException("invalid action");
        }
    }
}